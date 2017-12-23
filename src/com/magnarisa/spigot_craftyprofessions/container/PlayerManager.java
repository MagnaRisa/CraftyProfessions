package com.magnarisa.spigot_craftyprofessions.container;

import com.magnarisa.ICraftyProfessions;
import com.magnarisa.spigot_craftyprofessions.database.Database;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The Player manager manages the Players associated with the Players who
 * are currently online. This is the portal to which all of the Profession
 * interactions will take place. This utilizes the Singleton pattern because
 * we should only have one of these in existence at any given time, this will
 * also allow us access to the players of the Manager throughout the plugin.
 */
public final class PlayerManager
{
    private ICraftyProfessions mPlugin;
    private Database mDatabase;

    private static PlayerManager mPlayerManager;

    // Change the UUID to the internal database ID
    private ConcurrentHashMap<UUID, CraftyPlayer> mPlayerList;

    private PlayerManager () {}

    /**
     * This is the central access point for the PlayerManager.
     *
     * @return The sole instance of the PlayerManager
     */
    public static PlayerManager Instance ()
    {
        if (mPlayerManager == null)
        {
            mPlayerManager = new PlayerManager ();
        }
        return mPlayerManager;
    }

    /**
     * This method is intended to initialize the Singleton after it's instantiation in order to
     * have the necessary data available to the PlayerManager being the Database in which
     * to access the Player Information and the Instance to the plugin so we cant have access
     * to things like the Logger of the plugin and other useful resources.
     *
     * @param plugin - The plugin in which the PlayerManager is apart of.
     * @param playerStorage - A database which the PlayerManager needs to interface with in order
     *                      to get the data from players so we can track their profession
     *                      information.
     */
    public void initializePlayerManager (ICraftyProfessions plugin, Database playerStorage)
    {
        mPlugin = plugin;
        mDatabase = playerStorage;

        mPlayerList = new ConcurrentHashMap<> ();

        mPlugin.cpGetLogger ().info ("Initialization of the PlayerManager is completed!");
    }
    /**
     * This method will save all of the players to the database
     */
    public void saveAllPlayer ()
    {
        for (CraftyPlayer player : mPlayerList.values ())
        {
            /* Within this for each loop we shall iterate over
               the player manager in order to save all of the
               current players within the Map into the database. In
               order for this to work properly we will need to
               refine the Database in order to update a player
               rather than override that player. This also means
               we will need to update a lot more of the database.*/

            // NEEDS A BETTER IMPLEMENTATION
            // mPlayerDatabse.insertPlayerProfessions (player);

        }
    }

    /**
     * This method will remove the player with the specified UUID from the
     * PlayerManager.
     *
     * @param playerUUID - The Player to remove from the Manager.
     */
    public void removePlayer (UUID playerUUID)
    {
        mPlayerList.remove (playerUUID);
    }

    public void savePlayer (CraftyPlayer player)
    {
        mPlayerList.put (player.getUUID (), player);
    }

    /**
     * This method will update all of the players info into the Database without
     * removing them from the PlayerManager. This method could be called from a
     * command to update players or it may happen in a separate thread every
     * once in a while.
     */
    public void updatePlayers ()
    {

    }

    /**
     * This method will load a player from the Database into the PlayerManager's internal
     * ConcurrentHashMap.
     *
     * @param playerUUID - The Player in which to load from the database
     */
    public void loadPlayer (UUID playerUUID)
    {
//        mPlayerList.put (playerUUID, mDatabase.getPlayerInfo (playerUUID));
    }

    /**
     * This method will hash the given UUID to the Hash Map in order to find and return
     * the specified player that is within the PlayerManager. We will also not increment
     * the size here since we shall only be retrieving the CraftyPlayer in order to use
     * that CraftyPlayer elsewhere either to get data from it or to have that player
     * doWork for their job.
     *
     * @param playerUUID - The player to retrieve from the Hash Map
     *
     * @return The player specified by their UUID
     */
    public CraftyPlayer retrievePlayer (UUID playerUUID)
    {
        return mPlayerList.get (playerUUID);
    }
}
