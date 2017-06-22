package magnarisa.craftyprofessions.listeners;

import magnarisa.craftyprofessions.database.Database;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.xml.crypto.Data;

import static magnarisa.craftyprofessions.CraftyProfessions.getEconomy;

/**
 * Author:  CreedTheFreak
 * Date:    4/21/2017
 *
 * This is the Core Listener for the CraftyProfessions Plugin
 */
public class CoreListener implements Listener
{
    private Database mdb;

    public CoreListener (Database db)
    {
        mdb = db;
    }


    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event)
    {
        Player eventPlayer = event.getPlayer ();

        if (eventPlayer != null)
        {
            eventPlayer.sendMessage (eventPlayer.getDisplayName () + " Welcome!");

            if (mdb.hasPlayer (event.getPlayer ().getUniqueId ()))
            {
                eventPlayer.sendMessage (eventPlayer.getDisplayName () + " Is already in the Database");
            }
            else
            {
                eventPlayer.sendMessage (eventPlayer.getDisplayName () + "Is Not in the Database");
            }
        }

        /*
         * What needs to happen here is once the player joins, we will see if their name
         * is in the database, If it is, then we will retrieve that information and store it
         * in a new CraftyPlayer object. If the player is not in the database, then we will
         * wait until that player issues the /cp join [profession-name] which will then
         * add that player to the cp database to avoid making the database cluttered with
         * players that have no interest in sticking around.
         */
    }

    @EventHandler
    public void onBlockBreak (BlockBreakEvent event)
    {
        OfflinePlayer player = event.getPlayer ();

        event.getPlayer ().sendMessage (event.getPlayer ().getDisplayName () + " Broke " + event.getBlock ().toString ());

        if (getEconomy () != null)
        {
            event.getPlayer ().sendMessage (Double.toString (getEconomy ().getBalance (player)));
        }
    }
}
