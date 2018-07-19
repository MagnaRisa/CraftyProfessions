package com.magnarisa.common.database.DAOs;

import com.google.common.primitives.UnsignedLong;
import com.magnarisa.common.container.IPlayer;
import com.magnarisa.spigot_craftyprofessions.container.CraftyPlayer;
import com.magnarisa.common.database.databaseConn.Database;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AbsUsersDAO implements IDaoBase<CraftyPlayer>
{
    protected Database mDatabase;
    protected AugmentsDAO mAugmentDAO;
    protected ProfessionsDAO mProfessionsDAO;

    /**
     * <p>This is the constructor for the Users database access object.</p>
     *
     * @param database The database in which to access.
     */
    public AbsUsersDAO (Database database)
    {
        mDatabase = database;
        mAugmentDAO = new AugmentsDAO (database);
        mProfessionsDAO = new ProfessionsDAO (database);
    }

    /**
     * <p>Saves a players information into the database.</p>
     *
     * @param playerData The player to store within the database.
     */
    public void save (CraftyPlayer playerData)
    {
        // Save the player and all of their information to the DB

    }

    /**
     * <p>Deletes a player from the Database. This includes all of the relationships that the player
     * could potentially be a part of. Note: That the player can only get deleted if the proper password
     * has been input into the command.</p>
     *
     * @param id The primary identifier within the database for the current player.
     */
    public void delete (UnsignedLong id)
    {
        // Before we delete the user, add their current data to the archive so it is retrievable.

        // Delete the row whose rid == id
    }

    public CraftyPlayer update (CraftyPlayer row)
    {
        return row;
    }

    public List<CraftyPlayer> loadAll ()
    {
        return new ArrayList<> ();
    }

    public CraftyPlayer load (UUID userID)
    {
        IPlayer retPlayer = null;

        // Query the database using the current players UUID

        // From the Result Set build the player from separate Queries.

        // Construct the new Player here then return
        // retPlayer = buildPlayer ();

        return null;
    }

    public CraftyPlayer restoreUser (UUID userID)
    {
        return null;
    }
}
