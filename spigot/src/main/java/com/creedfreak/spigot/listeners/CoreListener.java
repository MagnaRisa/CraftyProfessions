package com.creedfreak.spigot.listeners;

import com.creedfreak.common.ICraftyProfessions;
import com.creedfreak.spigot.CraftyProfessions;
import com.creedfreak.common.professions.IWageTable;
import com.creedfreak.common.container.PlayerManager;
import com.creedfreak.common.database.databaseConn.Database;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.math.BigDecimal;

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

    public CoreListener (ICraftyProfessions professions)
    {
        mProfessions = (CraftyProfessions) professions;
    }


    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event)
    {
        Player eventPlayer = event.getPlayer ();


        eventPlayer.sendMessage ("Player Logged in, Attempting to save to PlayerManager");

        // PlayerManager.Instance ().savePlayer (new CraftyPlayer (eventPlayer, null));
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
    public void onPlayerLogout (PlayerQuitEvent event)
    {
        Player eventPlayer = event.getPlayer ();

        PlayerManager.Instance ().removePlayer (eventPlayer.getUniqueId ());
    }

    @EventHandler
    public void onBlockBreak (BlockBreakEvent event)
    {
        Player player = event.getPlayer ();

        /*
        player.sendMessage (event.getPlayer ().getDisplayName () + ChatColor.RED + " Broke " + event.getBlock ().getType () + ":" + event.getBlock ().getData ());
        player.sendMessage (event.getPlayer ().getDisplayName () + ChatColor.GOLD + " Broke " + event.getBlock ().getType ().toString ());

        if (mEconomy != null)
        {
           player.sendMessage (Double.toString (mEconomy.getBalance (player)));
        }
        */

        IWageTable wageTable = mProfessions.getWageTable ("Miner_Wage");
        if (wageTable == null)
        {
            player.sendMessage ("Wage Table is NULL");
            return;
        }

        BigDecimal value = wageTable.mapItem (event.getBlock (), "Miner_Payout");
        if (value == null)
        {
            player.sendMessage ("Block does not map to Miner_Wage Table value returned null");
            return;
        }

        player.sendMessage (event.getBlock().getType () + " is Worth " + value.toString ());
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
     * THIS METHOD IS FOR TESTING ONLY AND WILL NOT BE USED IN THE FINAL PRODUCTION
     */
    @EventHandler
    public void onEntityPickup (EntityPickupItemEvent event)
    {
        Entity player = event.getEntity ();

        if (player instanceof Player)
        {
            player.sendMessage (event.getItem ().getName ());
            // TODO: Use the below way to retrieve the Material Type from an Item.
            player.sendMessage (event.getItem ().getItemStack ().getType ().toString ());
            player.sendMessage (event.getItem ().getItemStack ().getData ().toString ());
            player.sendMessage (event.getItem ().getItemStack ().getItemMeta ().toString ());
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
