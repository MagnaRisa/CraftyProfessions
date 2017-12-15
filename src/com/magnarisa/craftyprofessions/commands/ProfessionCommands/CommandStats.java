package com.magnarisa.craftyprofessions.commands.ProfessionCommands;

import com.magnarisa.craftyprofessions.commands.ProfessionCommand;
import com.magnarisa.craftyprofessions.container.CommandData;
import com.magnarisa.craftyprofessions.container.CraftyPlayer;

/**
 * This command when executed will display the users stats
 * to the chat. Note that this is the basic version and will
 * not display some information whereas its counterpart
 * command of CommandProfile will give a book with your
 * full statistics within.
 */
public class CommandStats extends ProfessionCommand
{
    private CommandData mCommandData;

    public CommandStats ()
    {
        super (new CommandData (
            "stats",
            "This command will display a simple version of the players stats",
            "/prof stats",
            "craftyprofessions.use.stats"
        ));
    }

    public boolean execute (CraftyPlayer sender, String... args)
    {
        sender.sendMessage ("You have issued the command /prof stats");

        return true;
    }


}
