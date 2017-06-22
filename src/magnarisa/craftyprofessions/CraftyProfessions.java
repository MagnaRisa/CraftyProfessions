package magnarisa.craftyprofessions;

import magnarisa.craftyprofessions.commands.CommandController;
import magnarisa.craftyprofessions.config.ConfigController;
import magnarisa.craftyprofessions.container.PlayerManager;
import magnarisa.craftyprofessions.database.Database;
import magnarisa.craftyprofessions.database.SQLiteDatabase;
import magnarisa.craftyprofessions.listeners.CoreListener;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Authors: CreedTheFreak
 * Date:   4/8/2017
 *
 * This is the entry point class for the CraftyProfessions plugin.
 */
public class CraftyProfessions extends JavaPlugin
{
    private final Logger mLog = this.getLogger ();
    private static Economy mEconomy = null;
    private static Permission mPermissions = null;
    private static Chat mChat = null;

    // Config Details
    private static Configuration mConfig = null;
    private ConfigController mConfigController = null;

    // Command Controller
    private CommandController mCmdControl;

    // Database Information
    private Database mCPDatabase;

    /**
     * This method will do all of the plugins initialization of
     * various objects like commands, events, etc...
     */
    @Override
    public void onEnable ()
    {
        // Setup the Main Configuration class and likewise
        // any other needed config files.
        mConfigController = new ConfigController (this);
        mConfigController.createConfig ("config.yml");

        // MAKE SURE TO REMOVE THIS, TEMPORARY UNTIL WE GET THE PLAYER MANAGER/CONTROLLER
        // Setup the database and initialize the table unless it's already created.
        mCPDatabase = new SQLiteDatabase (this);
        mCPDatabase.load ();

        // Register the Core Listener of the Plugin
        getServer().getPluginManager().registerEvents (new CoreListener (mCPDatabase), this);

        if (!setupEconomy ())
        {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions ();
        setupChat();


        // Setup any command needs here through the controller
        // mCmdControl = new CommandController ();

        CommandController.initializeCommands (this);

        // This will initialize the PlayerManager Singleton
        PlayerManager.Instance ().initializePlayerManager (this, mCPDatabase);

        // This will notify the end of initialization
        this.getLogger().log(Level.INFO, "Initializaion of CraftyProfessions Completed!");
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
            mLog.severe ("Vault is not Installed! Disabling Plugin");
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager ().getRegistration (Economy.class);

        if (null == rsp)
        {
            mLog.severe ("No Economy Plugin Found! Disabling Plugin");
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

    /**
     * Returns the Database we are using
     */
    public Database getCPDatabase ()
    {
        return mCPDatabase;
    }

}
