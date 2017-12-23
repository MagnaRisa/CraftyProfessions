package com.magnarisa.spigot_craftyprofessions.database;

import com.magnarisa.ICraftyProfessions;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class MySQL_Conn extends Database
{
    private Connection mConnection;
    private String mHost;
    private String mDatabase;
    private String mDBUser;
    private String mDBIdentifier;

    /**************************************************************************
     * Constructor: MySQL_Conn
     *
     * Description: The primary constructor for a MySQL Connection
     *
     * Parameters:
     * @param plugin - The plugin in order to grab the Logger
     * @param hostName - The hostname of the Database you would like to
     *                      connect to
     * @param db - The database name to use from the host
     * @param user - The user in which to login with in order to read/write
     *             from the database
     * @param identifier - The database user's password
     *
     * Return: None
     *************************************************************************/
    public MySQL_Conn (ICraftyProfessions plugin, String hostName, String db,
                        String user, String identifier)
    {
        super (plugin);
        mHost = hostName;
        mDatabase = db;
        mDBUser = user;
        mDBIdentifier = identifier;
    }

    /**************************************************************************
     * Method: db_connect
     *
     * Description: Creates a MySQL connection to the constructed MySQL_Conn
     *              Object. This method uses the information from the
     *              constructed object to make the connection to the database.
     *
     * @return The connection to the Database
     *************************************************************************/
    public Connection db_connect ()
    {
        if (mConnection != null)
        {
            return mConnection;
        }

        try
        {
          mConnection = DriverManager.getConnection ("jdbc:mysql://"
              + mHost + "/" + mDatabase, mDBUser, mDBIdentifier);
        }
        catch (SQLException exception)
        {
            mPlugin.cpGetLogger ().log (Level.SEVERE,
                "Database Connection Error: ", exception);
        }

        return mConnection;
    }

    /**************************************************************************
     * Method: testDBconnection
     *
     * Creates a MySQL connection to the constructed MySQL_Conn Object.
     * This method uses the information from the constructed object to make the
     * connection to the database.
     *
     * @return The connection to the Database
     *************************************************************************/
    public List<Pair<Integer, String>> testDBconnection ()
    {
        List<Pair<Integer, String>> array = new ArrayList<> ();

        try
        {
            Statement statement = mConnection.createStatement ();
            ResultSet rSet;

            rSet = statement.executeQuery ("SELECT * FROM Test");

            while (rSet.next ())
            {
                array.add (new Pair<>(rSet.getInt ("TestID"),
                    rSet.getString ("TestingName")));
            }
        }
        catch (SQLException exception)
        {
            mPlugin.cpGetLogger ().log (Level.SEVERE,
                "Database Query Error: ", exception);
        }

        return array;
    }

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
