package com.magnarisa.craftyprofessions.commands.DatabaseCommands;

import com.magnarisa.craftyprofessions.commands.DatabaseCommand;
import com.magnarisa.craftyprofessions.container.CommandData;
import com.magnarisa.craftyprofessions.container.CraftyPlayer;
import com.magnarisa.craftyprofessions.database.Database;

/**
 * Created by CreedFreak54 on 7/19/2017.
 */
public class CommandLookup extends DatabaseCommand
{
    public CommandLookup (Database db)
    {
        super (db, new CommandData (
            "lookup",
            "This command looks up a player's stats based on the given arguments. Leave the statistic blank to obtain all the player's stats.",
            "/prof lookup [PlayerName] [Statistic]",
            "craftyprofessions.admin.lookup"
        ));
    }

    @Override
    public boolean execute (CraftyPlayer sender, String... args)
    {
        sender.sendMessage ("You have activated the command /prof lookup");

        return true;
    }
}
