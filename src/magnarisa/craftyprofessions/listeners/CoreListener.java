package magnarisa.craftyprofessions.listeners;

import magnarisa.craftyprofessions.CraftyProfessions;
import magnarisa.craftyprofessions.database.Database;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static magnarisa.craftyprofessions.CraftyProfessions.getEconomy;

/**
 * Author:  CreedTheFreak
 * Date:    4/21/2017
 *
 * This is the Core Listener for the CraftyProfessions Plugin
 */
public class CoreListener implements Listener
{
    private CraftyProfessions mProfessions;
    private Database mdb;
    private Economy mEconomy;

    public CoreListener (CraftyProfessions professions)
    {
        mProfessions = professions;
    }


    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event)
    {
       /* Player eventPlayer = event.getPlayer ();

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

        *//*
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
        Player player = event.getPlayer ();

        player.sendMessage (event.getPlayer ().getDisplayName () + " Broke " + event.getBlock ().toString ());

        if (mEconomy != null)
        {
           player.sendMessage (Double.toString (mEconomy.getBalance (player)));
        }
    }

    @EventHandler
    public void onBlockPlaced (BlockPlaceEvent event)
    {
        Player player = event.getPlayer ();

        player.sendMessage (player.getDisplayName() + " Placed " + event.getBlockPlaced ().toString ());

        if (mEconomy != null)
        {
            mEconomy.depositPlayer (player, 100D);
            mEconomy.getBalance (player);
        }
    }

    /**
     * WILL PROBABLY HAVE TO REMOVE THIS AT SOME POINT
     * I DONT LIKE SETTERS.
     *
     * This method will place the economy into the lister so that
     * we can obtain the correct economy after the construction of this
     * object.
     * @param econ
     */
    public void setEconomyHook (Economy econ)
    {
        mEconomy = econ;
    }
}
