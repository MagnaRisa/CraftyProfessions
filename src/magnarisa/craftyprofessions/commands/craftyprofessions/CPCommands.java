package magnarisa.craftyprofessions.commands.craftyprofessions;

import magnarisa.craftyprofessions.database.Database;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * This command allows the user to join a profession if given the
 * correct arguments.
 */
public class CPCommands implements CommandExecutor
{
    private Database mCPPlayers;

    public CPCommands (Database db)
    {
        mCPPlayers = db;
    }

    @Override
    public boolean onCommand (CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player && (args.length == 1))
        {
            if (mCPPlayers.hasPlayer (((Player) sender).getUniqueId ()))
            {
                /*mCPPlayers.getPlayerInfo (((Player) sender).getUniqueId ()).getUUID ().equals (((Player) sender).getUniqueId ())*/
                // Check to see if they can add another Job, if they can add it
                // else send back a message that they cannot add a job
                sender.sendMessage ("You are already in the Database!");
            }
            else
            {
                HashMap<String, Double> playerProfessions = new HashMap<> ();
                // If the player has the permission then add a job if they are allowed to have one

                // Testing with args that were passed in
                playerProfessions.put (args[0], 0.01D);

                // Test the database without this clause to see if the hasPlayer method will work
                mCPPlayers.insertPlayerProfessions ((Player) sender, playerProfessions);
            }
        }
        else
        {
            sender.sendMessage (ChatColor.DARK_RED + "Error Not enough arguments");
        }

        return false;
    }
}
