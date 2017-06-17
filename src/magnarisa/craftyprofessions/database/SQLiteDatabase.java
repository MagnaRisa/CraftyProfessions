package magnarisa.craftyprofessions.database;

import magnarisa.craftyprofessions.CraftyProfessions;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.UUID;
import java.util.logging.Level;

/**
 * This Class will implement
 */
public class SQLiteDatabase extends Database
{
    private String mDBName;

    /**
     * This is the constructor for the Database that Crafty Professions uses.
     *
     * @param instance The plugin the database is a part of.
     */
    public SQLiteDatabase (CraftyProfessions instance)
    {
        super (instance);
        mDBName = mCraftyProfessions.getConfig ().getString("SQLite.Filename", "player_table");
    }

    // String for a Table, Maybe store this somewhere else
    // public String SQLiteCreatePlayerTable
    private String SQLiteCreatePlayerTable = "CREATE TABLE IF NOT EXISTS " + player_table + " (" +
        "uuid BLOB, " +
        "player TEXT, " +
        "data TEXT)";

    /**
     * Returns the connection to the database. If the data folder does not exist then we
     * will need to create the data folder. Then we will need to see if we have a connection
     * and if that connection is open. We will only return the connection if
     * the database connection is open and the connection is not null. Otherwise we will
     * return the connection as null or throw exceptions if we get a fatal error.
     *
     * @return The connection to the database
     */
    public Connection getSQLConnection ()
    {
        File dataFolder = new File (mCraftyProfessions.getDataFolder (), mDBName + ".db");

        if (!dataFolder.exists ())
        {
            try
            {
                if (dataFolder.createNewFile ())
                {
                    mCraftyProfessions.getLogger ().info ("Database not created, Creating it now...");
                }
            }
            catch (IOException e)
            {
                mCraftyProfessions.getLogger ().log(Level.SEVERE, "File write Error: " + mDBName + ".db");
            }
        }

        try
        {
            if (mCPConnection != null && !mCPConnection.isClosed ())
            {
                return mCPConnection;
            }
            Class.forName ("org.sqlite.JDBC");
            mCPConnection = DriverManager.getConnection ("jdbc:sqlite:" + dataFolder);
            return mCPConnection;
        }
        catch (SQLException e)
        {
            mCraftyProfessions.getLogger ().log(Level.SEVERE, "SQLite Exception on Initialization" , e);
        }
        catch (ClassNotFoundException e)
        {
            mCraftyProfessions.getLogger ().log(Level.SEVERE, "You need the SQLite JDBC Library." + mDBName + ".db");
        }
        return null;
    }

    /**
     *
     */
    public void load ()
    {
        mCPConnection = getSQLConnection ();

        try
        {
            Statement statement = mCPConnection.createStatement ();
            statement.executeUpdate (SQLiteCreatePlayerTable);
            statement.close ();
        }
        catch (SQLException e)
        {
            e.printStackTrace ();
        }
        initialize ();
    }

    public boolean deleteUser (String sPlayerName)
    {
        return false;
    }

    public boolean savePlayer ()
    {
        return false;
    }

    public boolean addPlayer (/*Player Name Here*/ UUID uuid)
    {
        return false;
    }
}
