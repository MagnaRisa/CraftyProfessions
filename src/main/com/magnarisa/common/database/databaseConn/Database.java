package com.magnarisa.common.database.databaseConn;

import com.magnarisa.common.AbsConfigController;
import com.magnarisa.common.ICraftyProfessions;
import com.magnarisa.spigot_craftyprofessions.CraftyProfessions;
import com.magnarisa.spigot_craftyprofessions.container.CraftyPlayer;
import com.magnarisa.common.utility.LevelEquation;
import com.magnarisa.common.utility.SQLReader;
import com.magnarisa.common.utility.TimeUtil;

import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.logging.Level;

public abstract class Database
{
    public static final String DATABASE_PREFIX = "Database";
    private static final String SQL_INSERT_STATEMENTS = "com/magnarisa/resources/sql_files/insert_stmts.sql";
    private static final String SQL_CREATE_STMT = "CREATE TABLE IF NOT EXISTS";
    private static final String SQL_INSERT_STMT = "INSERT INTO";

    protected Connection mConnection;
    protected ICraftyProfessions mPlugin;
    protected AbsConfigController mConfiguration;

    private int mNumTables;
    private int mNumInsertsRan;
    private Double mTotalTimeElapsed;

    /**************************************************************************
     * Constructor: Database
     *
     * Description: The primary constructor for a Database connection
     *
     * Parameters:
     * @param plugin - The plugin the database interfaces with.
     *
     * Return:      None
     *************************************************************************/
    public Database (ICraftyProfessions plugin, AbsConfigController config)
    {
        mPlugin = plugin;
        mConfiguration = config;

        mNumTables = 0;
        mNumInsertsRan = 0;
        mTotalTimeElapsed = 0D;
    }

    /**************************************************************************
     * Method:      dbConnect
     *
     * Description: The common method that all extensions of this Database
     *              abstract object will need to implement.
     *
     * Parameters:  None
     *
     * Return:      The Specific connection of the implemented database.
     *************************************************************************/
    public abstract Connection dbConnect ();

    /**************************************************************************
     * Method:      getCreateTableStmts
     *
     * Description: Retrieves the implementation specific create tables
     *              file name
     *
     * Parameters: None
     *
     * Return:
     * @return The connection specific create table statement file name.
     *************************************************************************/
    protected abstract String getCreateTableStmts ();

    /**************************************************************************
     * Method:      dbClose
     *
     * Description: The method will close the prep statement and result set
     *              passed into the method. If either of these are already
     *              null, then they will not be closed.
     *
     * Parameters:  None
     *
     * Return:      None
     *************************************************************************/
    public void dbClose ()
    {
        try
        {
            if (mConnection != null)
            {
                mConnection.close();
            }
        }
        catch (SQLException exception)
        {
            mPlugin.Log (Level.WARNING,
                "Could not close Database Connection: " + exception);
        }
    }

    /**************************************************************************
     * Method: dbCloseResources
     *
     * Description: The method will close the prep statement and result set
     *              passed into the method. If either of these are already
     *              null, then they will not be closed.
     *
     * Parameters:
     * @param stmt - The statement to close
     * @param set - The result set to close
     *
     * Return: None
     *************************************************************************/
    public void dbCloseResources (PreparedStatement stmt, ResultSet set)
    {
        try
        {
            if (stmt != null)
            {
                stmt.close ();
            }
            if (set != null)
            {
                set.close ();
            }
        }
        catch (SQLException exception)
        {
            mPlugin.Log(Level.SEVERE, "Failed to close SQL Connection" + exception);
        }
    }

    /**************************************************************************
     * Method:      initializeDatabase
     *
     * Description: This method will setup the entire Database if it has
     *              already not been setup. If any errors occur while
     *              executing this method we will return false to notify
     *              the main plugin class that we need to disable the plugin.
     *
     * Parameters:  None
     *
     * Return:
     * @return True  - If the Database gets initialized successfully.
     *         False - If the Database initialization fails at some point.
     *************************************************************************/
    public boolean initializeDatabase ()
    {
        boolean success;
        long initialTime = System.nanoTime ();
        DecimalFormat timeFormat = new DecimalFormat ("#0.00");

        success = createTables ();

        if (!checkDBExists () && success)
        {
            CraftyProfessions.LogMessage (Level.INFO, DATABASE_PREFIX,
                "Total number of Create Table statements ran: " + mNumTables);

            success = insertIntoTables ();

            if (success)
            {
                CraftyProfessions.LogMessage (Level.INFO, DATABASE_PREFIX,
                    "Database has been created and the required data has been inserted!");
            }

            mTotalTimeElapsed = TimeUtil.toSeconds (System.nanoTime () - initialTime);
            CraftyProfessions.LogMessage (Level.INFO, DATABASE_PREFIX,
                "Total time elapsed for database Construction: "
                    + timeFormat.format (mTotalTimeElapsed) + "sec");
        }
        else
        {
            CraftyProfessions.LogMessage (Level.INFO, DATABASE_PREFIX, "Database found! Setup not necessary");
        }

        return success;
    }

    /**************************************************************************
     * Method:      createTables
     *
     * Description: This method will read the CreateTables.sql file one
     *              SQL statement at a time and then execute that statement
     *              until the Crafty Professions Database is setup.
     *
     * Parameters:  None
     *
     * Return:      None
     *************************************************************************/
    protected boolean createTables ()
    {
        Connection connection = dbConnect ();
        SQLReader reader = new SQLReader ();
        String sqlStmt;

        try
        {
            reader.openReader (mPlugin.cpGetResource (this.getCreateTableStmts ()));

            sqlStmt = reader.readStatement ();

            while (!sqlStmt.equals (SQLReader.EOF))
            {
                executeStatement (sqlStmt, connection);
                if (sqlStmt.contains (SQL_CREATE_STMT))
                {
                    mNumTables++;
                }
                sqlStmt = reader.readStatement ();
            }
        }
        catch (IOException | SQLException exception)
        {
            CraftyProfessions.LogMessage (Level.SEVERE, DATABASE_PREFIX,
                "Could not Create Database Tables: " + exception);
            return false;
        }
        catch (Exception exception)
        {
            CraftyProfessions.LogMessage (Level.SEVERE, DATABASE_PREFIX,"Unhandled Exception: " + exception);
            return false;
        }
        finally
        {
            dbClose ();
            reader.closeReader ();
        }
        return true;
    }

    /**************************************************************************
     * Method:      insertIntoTables
     *
     * Description: This method will insert the initialization data into the
     *              database. This will allow the plugin to start handling
     *              users when they log into the server.
     *
     * Parameters:  None
     *
     * Return:
     * @return True  - If the table insertions succeed
     *         False - If the table insertions fail
     *************************************************************************/
    private boolean insertIntoTables ()
    {
        Double baseEXPValue = mConfiguration.getDouble ("ProfessionLevels.BaseExp");

        Connection connection = dbConnect ();
        SQLReader reader = new SQLReader ();
        String sqlStmt;

        try
        {
            reader.openReader (mPlugin.cpGetResource (SQL_INSERT_STATEMENTS));

            sqlStmt = reader.readStatement ();

            while (!sqlStmt.equals (SQLReader.EOF))
            {
                if (sqlStmt.contains (SQL_INSERT_STMT))
                {
                    mNumInsertsRan++;
                }
                executeStatement (sqlStmt, connection);
                sqlStmt = reader.readStatement ();
            }
            insertLevels (baseEXPValue, connection);
        }
        catch (IOException | SQLException exception)
        {
            CraftyProfessions.LogMessage (Level.SEVERE, DATABASE_PREFIX,
                "Could Not Insert Initialization Data Into Database: "
                + exception);
            return false;
        }
        finally
        {
            dbClose ();
            reader.closeReader ();
            CraftyProfessions.LogMessage (Level.INFO, DATABASE_PREFIX,
                "Total number of Insertion statements ran: " + mNumInsertsRan);
        }
        return true;
    }

    /**************************************************************************
     * Method:      checkDBExists
     *
     * Description: This method will check to see if the Crafty professions
     *              tables are already setup. If they are then we will not
     *              rerun the Database setup Queries.
     *
     * Parameters:  None
     *
     * Return:
     * @return True -  If the tables were created.
     *         False - If the tables were already initialized.
     *************************************************************************/
    private boolean checkDBExists ()
    {
        boolean bTablesExist;
        Connection conn = dbConnect ();

        try
        {
            String sqlQuery = "SELECT `Level` FROM Levels";
            PreparedStatement prepStmt = conn.prepareStatement (sqlQuery);
            ResultSet rSet = prepStmt.executeQuery ();

            bTablesExist = rSet.next ();

            dbCloseResources (prepStmt, rSet);
        }
        catch (SQLException exception)
        {
            CraftyProfessions.LogMessage (Level.SEVERE, DATABASE_PREFIX,
                "Could not locate tables within checkDBExists! Defaulting return type to false");
            return false;
        }
        finally
        {
            dbClose ();
        }
        return bTablesExist;
    }

    /**************************************************************************
     * Method:      executeStatement
     *
     * Description: This method executes sql statements that do not have a
     *              return value.
     *
     * Exceptions:
     * @throws SQLException - If an error occurs here we are throwing the
     *                        exception to be handled where this method
     *                        is called.
     *
     * Parameters:
     * @param sqlStatement - An SQL statement that will be executed.
     * @param conn - The connection to the database.
     *
     * Return: None
     *************************************************************************/
    private void executeStatement (String sqlStatement, Connection conn)
        throws SQLException
    {
        PreparedStatement statement = conn.prepareStatement (sqlStatement);
        statement.execute();
    }

    /**************************************************************************
     * Method:      insertLevels
     *
     * Description: This method will insert the varying Levels of the plugin
     *                  into the Database based off the Level Equation
     *
     * Exceptions:
     * @throws SQLException - If an error occurs here we are throwing the
     *                        exception to be handled where this method
     *                        is called.
     *
     * Parameters:
     * @param baseValue - The Base Exp value the Levels will generate with
     * @param conn - The connection to the database
     *
     * Return:      None
     *************************************************************************/
    private void insertLevels (Double baseValue, Connection conn)
        throws SQLException
    {
        Double prevLevelExp = 0D;
        Double currentLevelExp;
        String insertLevel = "INSERT INTO Levels (`Level`, ExpAmount) "
                            + "VALUES (?, ?)";

        for (int i = 1; i <= 50; i++)
        {
            PreparedStatement prepStmt = conn.prepareStatement (insertLevel);

            currentLevelExp = LevelEquation.calculateLevel (baseValue, i, prevLevelExp);

            prepStmt.setInt (1, i);
            prepStmt.setDouble (2, currentLevelExp);

            prevLevelExp = currentLevelExp;

            prepStmt.execute ();
        }
    }

    /**
     * <p>This method is used to log Database errors to the plugin. This was created so that the plugin
     * reference won't have to be passed to all of the query methods.</p>
     *
     * @param message The error message to be logged to the plugins logger method.
     */
    public void logDBMessage (Level level, String message)
    {
        mPlugin.Log (level, message);
    }

    /**************************************************************************
     * Method:      testReader [DELETE THIS METHOD EVENTUALLY]
     *
     * Description: This method tests the readers ability and will also test
     *              the internal spacing of the SQL Statements. This way I can
     *              vet the file reading ability without crashing all the time.
     *
     * Parameters:
     * @param player - The player to send the messages to.
     *
     * Return: None
     *************************************************************************/
    public void testReader (CraftyPlayer player)
    {
        player.sendMessage ("This Command is Empty!");
    }
}
