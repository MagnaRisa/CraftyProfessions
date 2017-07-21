package magnarisa.craftyprofessions.commands.DatabaseCommands;

import magnarisa.craftyprofessions.commands.DatabaseCommand;
import magnarisa.craftyprofessions.container.CommandData;
import magnarisa.craftyprofessions.container.CraftyPlayer;
import magnarisa.craftyprofessions.database.Database;

/**
 * This command will handle the backing up of players in one database to another database,
 * or if given the correct arguments, it will only backup the players within the PlayerManager.
 */
public class CommandArchiveAll extends DatabaseCommand
{
    public CommandArchiveAll (Database db)
    {
        super (db, new CommandData (
            "archiveall",
            "This command will archive all of the users within the database.",
            "/prof archiveall",
            "craftyprofessions.admin.archiveall"
        ));
    }

    @Override
    public boolean execute (CraftyPlayer sender, String... args)
    {
        sender.sendMessage ("You have issued the archiveall command");

        return true;
    }
}
