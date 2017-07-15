package magnarisa.craftyprofessions.commands.Cmd_Professions;

import magnarisa.craftyprofessions.commands.ICommand;
import magnarisa.craftyprofessions.container.CommandData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The CommandInfo class used to display information to the
 * sender of the command to display information about the
 * profession sent in as the argument.
 */
public class CommandInfo implements ICommand
{
    private CommandData mCommandInfo;

    /**
     * The Default Constructor is used to setup the information
     * surrounding the Profession Join Command
     */
    public CommandInfo ()
    {
        mCommandInfo = new CommandData (
            "info",
            "Displays the information of a specific Profession",
            "/prof info [ProfessionName]",
            "craftyprofessions.use.info");
    }

    /**
     * This method executes the command for the given class.
     *
     * @param sender The Sender of the command
     *
     * @param command The command that is to being executed.
     *
     * @param label The label of the command
     *
     * @param args The arguments of the command.
     *
     * @return True  - If the command succeeds in any way including having not enough arguments or
     *                   if some exception was thrown.
     *         False - If the command fails all checks
     */
    public boolean execute (CommandSender sender, Command command, String label, String... args)
    {
        if (sender instanceof Player)
        {
            sender.sendMessage ("You are a player, you have just executed /prof info");
        }

        return false;
    }

    /**
     * This method returns the name of the command
     *
     * @return The name of the command after the prefix /prof
     */
    public String cmdName ()
    {
        return mCommandInfo.getCommandArg ();
    }
}
