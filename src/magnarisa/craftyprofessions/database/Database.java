package magnarisa.craftyprofessions.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import magnarisa.craftyprofessions.container.CraftyPlayer;
import magnarisa.craftyprofessions.CraftyProfessions;
import magnarisa.craftyprofessions.utility.UuidUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;

/**
 * This is the main interface for reading and writing to a Database.
 * This interface will outline the major commonalities between databases
 * that need to be implemented for Crafty Professions to work properly.
 */
public abstract class Database
{
    protected CraftyProfessions mCraftyProfessions;
    protected String player_table = "player_table";
    protected Connection mCPConnection;

    public Database (CraftyProfessions instance)
    {
        mCraftyProfessions = instance;
    }

    public abstract Connection getSQLConnection ();

    public abstract void load ();

    public void initialize ()
    {
        String sqlSelectFrom = "SELECT * FROM " + player_table + " WHERE uuid = ?";
        mCPConnection = getSQLConnection ();

        try
        {
            PreparedStatement prepStatement = mCPConnection.prepareStatement (sqlSelectFrom);
            prepStatement.setString (1, "null");
            ResultSet resultSet = prepStatement.executeQuery ();
            close (prepStatement, resultSet);
        }
        catch (SQLException e)
        {
            mCraftyProfessions.getLogger ().log (Level.SEVERE, "Unable to retrieve connection", e);
        }
    }

    /**
     * This method will close the passed in PreparedStatement and ResultSet
     */
    public void close (PreparedStatement prepStatement, ResultSet resultSet)
    {
        try
        {
            if (prepStatement != null)
            {
                prepStatement.close ();
            }
            if (resultSet != null)
            {
                resultSet.close ();
            }
        }
        catch (SQLException e)
        {
            mCraftyProfessions.getLogger ().log (Level.SEVERE, "Failed to close SQL Connection", e);
        }
    }

    // Data Retrieval methods below here!

    /**
     * This method will insert the players Profession Data into the CPPlayer Table,
     * being the jobsList which is all the jobs a player has and modifierList which
     * are the modifiers in accompanying order to the jobsList that those Jobs have.
     *
     * @param player    - This player to insert into the table.
     * @param jobsData  - The List of Professions that a Player has in string form and the
     *                     modifiers that are apart of those Professions
     *
     * TODO: We may want to send in a data holder of Profession Data that we attach to a player
     */
    public void insertPlayerProfessions (Player player, HashMap<String, Double> jobsData)
    {
        String sqlReplaceInto = "REPLACE INTO " + player_table + " (uuid,player,data) VALUES(?,?,?)";

        String jobsStorage = new Gson ().toJson (jobsData);

        Connection connection = null;
        PreparedStatement prepStatement = null;

        int pramIndex = 1;

        try
        {
            connection = getSQLConnection ();
            prepStatement = connection.prepareStatement (sqlReplaceInto);

            // Stores the UUID as Bytes
            prepStatement.setBytes (pramIndex++, UuidUtil.toBytes (player.getUniqueId ()));

            prepStatement.setString (pramIndex++, player.getName ().toLowerCase ());

            prepStatement.setString (pramIndex, jobsStorage);

            prepStatement.executeUpdate ();

        }
        catch (SQLException e)
        {
            mCraftyProfessions.getLogger ().log (Level.SEVERE, "Couldn't execute MySQL statement: ", e);
        }
        finally
        {
            try
            {
                if (prepStatement != null)
                {
                    prepStatement.close ();
                }
                if (connection != null)
                {
                    connection.close ();
                }
            }
            catch (SQLException e)
            {
                mCraftyProfessions.getLogger ().log (Level.SEVERE, "Failed to close MySQL connection: ", e);
            }
        }
    }

    /**
     * This method will query the Database with the given player UUID and if the player is found
     * it will return the player data from within the Database else it will return null.
     *
     * @param playerUuid - The players UUID in which to find the Database info for that player.
     *
     * @return A CraftyPlayer Object instantiated with the players Database Information, null if
     * the players UUID is not found within the Database.
     */
    public CraftyPlayer getPlayerInfo (UUID playerUuid)
    {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        ResultSet resultSet;

        UUID playerUniqueID;
        String name;

        // The info for a Profession
        HashMap<String, Double> jobsData;

        try
        {
            String byteUuidString = new String (UuidUtil.toBytes (playerUuid));
            String sqlString = "SELECT * FROM " + player_table + " WHERE uuid = ?";

            connection = getSQLConnection ();
            prepStatement = connection.prepareStatement (sqlString);
            prepStatement.setString (1, byteUuidString);

            resultSet = prepStatement.executeQuery ();

            while (resultSet.next ())
            {
                if (Arrays.equals (resultSet.getBytes ("uuid"), (UuidUtil.toBytes (playerUuid))))
                {
                    playerUniqueID = UuidUtil.fromBytes (resultSet.getBytes ("uuid"));
                    name = resultSet.getString ("player");

                    jobsData = new Gson ().fromJson (resultSet.getString ("data"), new TypeToken<HashMap<String, Double>> (){}.getType ());

                    // Check if all the obtained info from the Database exists
                    if (playerUniqueID.equals (playerUuid) && name != null && jobsData != null)
                    {
                        return new CraftyPlayer (Bukkit.getPlayer (playerUniqueID), jobsData);
                    }
                }
            }
        }
        catch (SQLException e)
        {
           mCraftyProfessions.getLogger ().log(Level.SEVERE, "Was unable to execute SQL Statement: ", e);
        }
        finally
        {
            try
            {
                if (prepStatement != null)
                {
                    prepStatement.close ();
                }
                if (connection != null)
                {
                    connection.close ();
                }
            }
            catch (SQLException e)
            {
                mCraftyProfessions.getLogger ().log(Level.SEVERE, "Failed to close SQL Connection: ", e);
            }
        }

        return null;
    }

    /** [MAY NOT NEED THIS IF I CAN TEST IF THEY ARENT WITHIN THE DATABASE THEN JUST ADD THEM ON THE SPOT ABOVE]
     * This method will Query the Database and check to test if the given UUID Passed
     * into the method is within the database.
     *
     * @param playerUuid - The UUID to look for
     *
     * @return True - If the player UUID exists within the table
     *         False - If the player UUID does not exists within the table
     */
    public boolean hasPlayer (UUID playerUuid)
    {
        Connection connection;
        PreparedStatement prepStatement;

        String uuidString = new String (UuidUtil.toBytes (playerUuid));
        String sqlSearch = "SELECT uuid FROM " + player_table + " WHERE uuid = ?";

        try
        {
            connection = getSQLConnection ();
            prepStatement = connection.prepareStatement (sqlSearch);
            prepStatement.setString (1, uuidString);


            ResultSet resultSet = prepStatement.executeQuery ();

            return resultSet.next ();
        }
        catch (SQLException e)
        {
            mCraftyProfessions.getLogger ().log(Level.SEVERE, "Was unable to execute SQL PrepStatement: ", e);
        }

        return false;
    }
}
