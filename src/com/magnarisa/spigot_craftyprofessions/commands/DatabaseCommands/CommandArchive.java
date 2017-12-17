package com.magnarisa.spigot_craftyprofessions.commands.DatabaseCommands;

import com.magnarisa.spigot_craftyprofessions.commands.DatabaseCommand;
import com.magnarisa.spigot_craftyprofessions.container.CommandData;
import com.magnarisa.spigot_craftyprofessions.container.CraftyPlayer;
import com.magnarisa.spigot_craftyprofessions.database.Database;

/**
 * This command will archive a player to the archive database or multiple players based
 * on the command arguments
 */
public class CommandArchive extends DatabaseCommand
{
    public CommandArchive (Database db)
    {
        super (db, new CommandData (
            "archive",
            "This command will send the specified player to be backed up within the Archive.",
            "/prof archive [PlayerName]",
            "spigot_craftyprofessions.admin.archive"
        ));
    }

    @Override
    public boolean execute (CraftyPlayer sender, String... args)
    {
        sender.sendMessage ("You have activated the /prof archive command");

        return true;
    }
}
