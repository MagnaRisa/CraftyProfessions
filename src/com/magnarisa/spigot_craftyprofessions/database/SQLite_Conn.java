package com.magnarisa.spigot_craftyprofessions.database;

import com.magnarisa.ICraftyProfessions;
import com.magnarisa.spigot_craftyprofessions.CraftyProfessions;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;

public class SQLite_Conn extends Database
{
    public final String SQLITE_DB_NAME = "craftyProfSQLite.db";

    private Connection mConnection;

    /**************************************************************************
     * Constructor: SQLite_Conn
     *
     * Description: The primary constructor for an SQLite Connection
     *
     * Parameters:
     * @param plugin - The plugin in order to grab the Logger
     *
     * Return: None
     *************************************************************************/
    public SQLite_Conn (ICraftyProfessions plugin)
    {
        super (plugin);
    }

    /**************************************************************************
     * Method: db_connect
     *
     * Description: This method will create and return a MySQL database
     *              connection.
     *
     * Parameters: None
     *
     * Return:
     * @return The connection to the database
     *************************************************************************/
    public Connection db_connect ()
    {
        CraftyProfessions plugin = (CraftyProfessions) mPlugin;
        File dataFolder = new File (plugin.getDataFolder (), SQLITE_DB_NAME);

        if (mConnection != null)
        {
            return mConnection;
        }

        if (!dataFolder.exists ())
        {
            try
            {
                if (dataFolder.createNewFile ())
                {
                    plugin.getLogger ().info ("Database not created, "
                        + "Creating it now...");
                }
            }
            catch (IOException e)
            {
                plugin.getLogger ().log(Level.SEVERE, "File write Error: "
                    + SQLITE_DB_NAME);
            }
        }

        try
        {
            if (mConnection != null && !mConnection.isClosed ())
            {
                return mConnection;
            }

            mConnection = DriverManager.getConnection ("jdbc:sqlite:"
                + dataFolder);

            return mConnection;
        }
        catch (SQLException e)
        {
            plugin.getLogger ().log(Level.SEVERE,
                "SQLite Exception on Initialization" , e);
        }

        return null;
    }

    /**************************************************************************
     * Method: db_close
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
    @Override
    public void db_close (PreparedStatement stmt, ResultSet set)
    {
        super.db_close (stmt, set);

        try
        {
            if (mConnection != null)
            {
                mConnection.close();
            }
        }
        catch (SQLException exception)
        {
            mPlugin.cpGetLogger ().log (Level.WARNING,
                "Could not close Connection: " + exception);
        }

    }
}
