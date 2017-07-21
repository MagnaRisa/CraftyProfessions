package magnarisa.craftyprofessions.commands.DatabaseCommands;

import magnarisa.craftyprofessions.commands.DatabaseCommand;
import magnarisa.craftyprofessions.container.CommandData;
import magnarisa.craftyprofessions.container.CraftyPlayer;
import magnarisa.craftyprofessions.database.Database;

/**
 * This command will reset a players CraftyPlayer and corresponding database object to whatever the given arguments
 * are. You can specify a statistic within the argument to reset that specific stat.
 */
public class CommandResetProfile extends DatabaseCommand
{
    public CommandResetProfile (Database db)
    {
        super (db, new CommandData (
            "resetplayer",
            "This command will wipe a players full professions stats or you can choose which stat you would like to erase",
            "/prof resetplayer [Statistic]",
            "craftyprofessions.admin.resetplayer"
        ));
    }

    @Override
    public boolean execute (CraftyPlayer sender, String... args)
    {
        sender.sendMessage ("You have issued the ResetPlayer command");

        return true;
    }
}
