package magnarisa.craftyprofessions.commands.Cmd_Professions;

import magnarisa.craftyprofessions.commands.ICommand;
import magnarisa.craftyprofessions.container.CommandData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This is the Join command for a Profession. If a player issues this command
 * it will join them into a Profession.
 */
public class CommandJoin implements ICommand
{
    private CommandData mCommandInfo;

    /**
     * The Default Constructor is used to setup the information
     * surrounding the Profession Join Command
     */
    public CommandJoin ()
    {
       mCommandInfo = new CommandData (
            "join",
            "Joins the user to the specified Profession",
            "/prof join [ProfessionName]",
            "craftyprofessions.use.join");
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
            sender.sendMessage ("You are a player, you have just executed /prof join");
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
