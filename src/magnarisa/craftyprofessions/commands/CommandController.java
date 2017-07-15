package magnarisa.craftyprofessions.commands;

import magnarisa.craftyprofessions.CraftyProfessions;
import magnarisa.craftyprofessions.commands.Cmd_Professions.CommandInfo;
import magnarisa.craftyprofessions.commands.Cmd_Professions.CommandJoin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;


/**
 * The Command Controller is the central hub for all of the command
 * initialization and the disabling of commands. Note that some commands
 * will be unable to be disabled but in terms of feature commands there will
 * be disabling of those commands.
 */
public class CommandController implements CommandExecutor
{
    private CraftyProfessions mPlugin;
    private HashMap<String, ICommand> mCommandMap;

    /**
     * Default constructor
     */
    public CommandController (CraftyProfessions craftyProf)
    {
        mPlugin = craftyProf;
        mCommandMap = new HashMap<> ();
    }

    /**
     * Processes
     *
     * @param sender
     * @param cmd
     * @param label
     * @param args
     * @return
     */
    @Override
    public boolean onCommand (CommandSender sender, Command cmd, String label, String... args)
    {
        if (sender instanceof Player)
        {
            sender.sendMessage ("Profession command issued!");
            return true;
        }
        else
        {
            sender.sendMessage ("Not a player, Issuing Profession Command");
            return true;
        }
    }

    /**
     * This method will setup all of the commands for the plugin and
     * register them with spigot.
     */
    public void initializeCommands ()
    {
        registerCommand (new CommandJoin ());
        registerCommand (new CommandInfo ());
    }

    private void registerCommand (ICommand cmd)
    {
        mCommandMap.put (cmd.cmdName (), cmd);
    }
}
