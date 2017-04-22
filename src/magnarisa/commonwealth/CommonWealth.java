package magnarisa.commonwealth;

import magnarisa.commonwealth.commands.CommandShower;
import magnarisa.commonwealth.listeners.CoreListener;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Authors: Logan Cookman
 * Date:   4/8/2017
 *
 * This is the entry point class for the CommonWealth plugin.
 */
public class CommonWealth extends JavaPlugin
{
    private final Logger Log = this.getLogger ();
    private static Economy mEconomy = null;
    private static Permission mPermissions = null;
    private static Chat mChat = null;

    /**
     * This method will do all of the plugins initialization of
     * various objects like commands, events, etc...
     */
    @Override
    public void onEnable ()
    {
        // Register the Core Listener of the Plugin
        getServer().getPluginManager().registerEvents (new CoreListener (), this);

        if (!setupEconomy ())
        {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions ();
        setupChat();

        // Redo commands? This way seems a bit weird.
        // Registers the commands
        this.getCommand ("shower").setExecutor (new CommandShower());

    }

    /**
     * This method will call anything that needs to be disabled when
     * the plugin gets disabled via command or on a server shutdown.
     */
    @Override
    public void onDisable ()
    {

    }

    /**
     * This method will test if Vault is installed and if there is a
     * registered Economy Plugin. This method will only return true
     * if there is an economy plugin that is registered and if Vault
     * is installed and enabled.
     */
    private boolean setupEconomy ()
    {
        if (null == getServer().getPluginManager ().getPlugin ("Vault"))
        {
            Log.severe ("Vault is not Installed! Disabling Plugin");
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager ().getRegistration (Economy.class);

        if (null == rsp)
        {
            Log.severe ("No Economy Plugin Found! Disabling Plugin");
            return false;
        }

        mEconomy = rsp.getProvider ();
        return mEconomy != null;
    }

    /**
     * This method will return whether or not the chat provider
     * has been setup or not.
     */
    private boolean setupChat ()
    {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager ().getRegistration (Chat.class);

        if (null != rsp)
        {
            mChat = rsp.getProvider ();
        }

        return mChat != null;
    }

    /**
     * This method will return whether or not the Permissions provider
     * has been setup or not.
     */
    private boolean setupPermissions ()
    {
        RegisteredServiceProvider<Permission> rsp = getServer ().getServicesManager ().getRegistration (Permission.class);

        if (null != rsp)
        {
            mPermissions = rsp.getProvider ();
        }

        return mPermissions != null;
    }

    /**
     * Returns the Economy
     */
    public static Economy getEconomy ()
    {
        return mEconomy;
    }

    /**
     * Returns the Permissions
     */
    public static Permission getPermissions ()
    {
        return mPermissions;
    }

    /**
     * Returns the Chat
     */
    public static Chat getChat ()
    {
        return mChat;
    }

}
