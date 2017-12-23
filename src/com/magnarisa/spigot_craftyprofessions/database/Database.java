package com.magnarisa.spigot_craftyprofessions.database;

import com.magnarisa.ICraftyProfessions;

import java.sql.*;
import java.util.logging.Level;

public abstract class Database
{
    protected ICraftyProfessions mPlugin;

    /**************************************************************************
     * Constructor: Database
     *
     * Description: The primary constructor for a Database connection
     *
     * Parameters:
     * @param plugin - The plugin the database interfaces with.
     *
     * Return: None
     *************************************************************************/
    public Database (ICraftyProfessions plugin)
    {
        mPlugin = plugin;
    }

    /**************************************************************************
     * Method: db_connect
     *
     * Description: The common method that all extensions of this Database
     *              abstract object will need to implement.
     *
     * Parameters: None
     *
     * Return: The Specific connection of the implemented database.
     *************************************************************************/
    public abstract Connection db_connect ();

    /**************************************************************************
     * Method: db_close
     *
     * Description: This method outlines the commonalities that the close
     *              method needs to implement for each type of database.
     *
     * Parameters:
     * @param stmt - The statement to close
     * @param set - The result set to close
     *
     * Return: None
     *************************************************************************/
    public void db_close (PreparedStatement stmt, ResultSet set)
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
            mPlugin.cpGetLogger ().log (Level.SEVERE,
                "Failed to close SQL Connection", exception);
        }
    }

    /**************************************************************************
     * Method: createTables
     *
     * Description: This method will read the CreateTables.sql file one
     *              SQL statement at a time and then execute that statement
     *              until the Crafty Professions Database is setup.
     *
     * Parameters: None
     *
     * Return: None
     *************************************************************************/
    public void createTables ()
    {
        Connection connection = db_connect ();

        if (!checkDBExists ())
        {
            while ()
        }
    }

    /**************************************************************************
     * Method: checkTableCreation
     *
     * Description: This method will check to see if the Crafty professions
     *              tables are already setup.
     *
     * Parameters: None
     *
     * Return: True -  If the tables were created
     *         False - If the tables were already initialized.
     *************************************************************************/
    public boolean checkDBExists ()
    {
        return false;
    }

    /**************************************************************************
     * Method: checkTableCreation
     *
     * Description: This method will check to see if the Crafty professions
     *              tables are already setup.
     *
     * Parameters:
     * @param sqlStatement - An SQL statement that will be executed.
     * @param conn - The connection to the database.
     *
     * Return: None
     *************************************************************************/
    private void executeStatement (String sqlStatement, Connection conn)
    {
        try
        {
            PreparedStatement statement = conn.prepareStatement (sqlStatement);

            ResultSet resultSet = statement.executeQuery ();
        }
        catch (SQLException exception)
        {
            mPlugin.cpGetLogger ().log (Level.SEVERE,
                "Could not execute create table statement: " + exception);
        }
    }

}
