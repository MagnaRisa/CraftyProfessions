package magnarisa.craftyprofessions.commands.DatabaseCommands;

import magnarisa.craftyprofessions.commands.DatabaseCommand;
import magnarisa.craftyprofessions.container.CommandData;
import magnarisa.craftyprofessions.container.CraftyPlayer;
import magnarisa.craftyprofessions.database.Database;

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
            "craftyprofessions.admin.archive"
        ));
    }

    @Override
    public boolean execute (CraftyPlayer sender, String... args)
    {
        sender.sendMessage ("You have activated the /prof archive command");

        return true;
    }
}
