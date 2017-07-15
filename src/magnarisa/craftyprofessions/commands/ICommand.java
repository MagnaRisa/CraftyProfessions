package magnarisa.craftyprofessions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface ICommand
{
    /**
     * This is the primary method for executing a command.
     */
    boolean execute (CommandSender sender, Command cmd, String label, String... args);

    /**
     * This method returns the Name of the command.
     */
    String cmdName ();
}
