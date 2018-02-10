package com.magnarisa.common.database.DAOs;

import com.google.common.primitives.UnsignedLong;
import com.magnarisa.spigot_craftyprofessions.container.CraftyPlayer;
import com.magnarisa.common.database.databaseConn.Database;

import java.util.ArrayList;
import java.util.List;

public class UsersDAO implements IDaoBase<CraftyPlayer>
{
    private Database mDatabase;
    private AugmentsDAO mAugmentDAO;
    private ProfessionsDAO mProfessionsDAO;

    /**
     * <p>This is the constructor for the Users database access object.</p>
     *
     * @param database The database in which to access.
     */
    public UsersDAO (Database database)
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
     * @param id
     */
    public void delete (UnsignedLong id)
    {
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
}
