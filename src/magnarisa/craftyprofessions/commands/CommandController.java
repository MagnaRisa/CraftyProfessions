package magnarisa.craftyprofessions.commands;

import magnarisa.craftyprofessions.CraftyProfessions;
import magnarisa.craftyprofessions.commands.craftyprofessions.CPCommands;

/**
 * The Command Controller is the central hub for all of the command
 * initialization and the disabling of commands. Note that some commands
 * will be unable to be disabled but in terms of feature commands there will
 * be disabling of those commands.
 */
public class CommandController
{
    CraftyProfessions mCraftyProfessions;
    /**
     * Default constructor
     */
    public CommandController (/* CraftyProfessions craftyProf */)
    {
        // mCraftyProfessions = craftyProf;
    }

    /**
     * This method will setup all of the commands for the plugin and
     * register them with spigot.
     */
    public static void initializeCommands (CraftyProfessions craftyProf)
    {
        craftyProf.getCommand ("shower").setExecutor (new CommandShower ());
        craftyProf.getCommand ("prof").setExecutor (new CPCommands (craftyProf.getCPDatabase ()));
    }
}
