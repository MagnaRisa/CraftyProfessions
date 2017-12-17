package com.magnarisa.spigot_craftyprofessions.commands.ProfessionCommands;

import com.magnarisa.spigot_craftyprofessions.commands.ProfessionCommand;
import com.magnarisa.spigot_craftyprofessions.container.CommandData;
import com.magnarisa.spigot_craftyprofessions.container.CraftyPlayer;

/**
 * This command will list all of the available jobs that a player can join.
 */
public class CommandList extends ProfessionCommand
{
    public CommandList ()
    {
        super (new CommandData (
            "list",
            "Lists the professions available for a user to choose from",
            "/prof list",
            "spigot_craftyprofessions.use.list"));
    }

    @Override
    public boolean execute (CraftyPlayer sender, String... args)
    {
        sender.sendMessage ("You have just executed /prof list");

        return true;
    }
}
