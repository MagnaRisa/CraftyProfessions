package magnarisa.commonwealth.listeners;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static magnarisa.commonwealth.CommonWealth.getEconomy;

/**
 * Author:  CreedTheFreak
 * Date:    4/21/2017
 *
 * This is the Core Listener for the CommonWealth Plugin
 */
public class CoreListener implements Listener
{
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event)
    {
        Player eventPlayer;

        if (event.getPlayer () != null)
        {
            eventPlayer = event.getPlayer ();

            eventPlayer.sendMessage (eventPlayer.getDisplayName () + " Welcome!");
        }
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
