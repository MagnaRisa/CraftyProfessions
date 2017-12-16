package com.magnarisa.spigot_craftyprofessions.commands;

import com.magnarisa.spigot_craftyprofessions.container.CommandData;
import com.magnarisa.spigot_craftyprofessions.container.CraftyPlayer;
import com.magnarisa.spigot_craftyprofessions.database.Database;

/**
 * The abstract command that controls the Database operations
 */
public abstract class DatabaseCommand implements ICommand
{
    protected Database mDataBase;
    private CommandData mCommandData;

    protected DatabaseCommand (Database db, CommandData data)
    {
        mDataBase = db;
        mCommandData = data;
    }

    @Override
    public String cmdName ()
    {
        return mCommandData.getCommandArg ();
    }

    /**
     * Checks a users permissions
     */
    public boolean checkPermission (CraftyPlayer player)
    {
        return mCommandData.hasPerms (player);
    }

    /**
     * Grabs the description of the command
     */
    public String getDescription ()
    {
        return mCommandData.getDescription ();
    }

    /**
     * Grabs the usage of the command
     */
    public String getUsage ()
    {
        return mCommandData.getUsage ();
    }
}
