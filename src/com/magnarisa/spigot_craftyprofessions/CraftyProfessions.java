package com.magnarisa.spigot_craftyprofessions;

import com.magnarisa.AbsCmdController;
import com.magnarisa.AbsConfigController;
import com.magnarisa.ICraftyProfessions;
import com.magnarisa.spigot_craftyprofessions.commands.CommandController;
import com.magnarisa.spigot_craftyprofessions.config.ConfigController;
import com.magnarisa.spigot_craftyprofessions.container.IWageTable;
import com.magnarisa.spigot_craftyprofessions.container.MinerWage;
import com.magnarisa.spigot_craftyprofessions.container.PlayerManager;
import com.magnarisa.spigot_craftyprofessions.database.Database;
import com.magnarisa.spigot_craftyprofessions.database.MySQL_Conn;
import com.magnarisa.spigot_craftyprofessions.listeners.CoreListener;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Authors: CreedTheFreak
 *
 * This is the entry point class for the CraftyProfessions plugin.
 */
public class CraftyProfessions extends JavaPlugin implements ICraftyProfessions
{
    private final Logger mLog = this.getLogger ();
    private static Economy mEconomy = null;
    private static Permission mPermissions = null;
    private static Chat mChat = null;
    private CoreListener mCoreListener;

    // Config Details
    private static Configuration mConfig = null;
    private ConfigController mConfigController = null;

    // Command Controller
    private CommandController mCommandController;

    // Database Information
    Database mDatabase;

    // WageTables
    private HashMap<String, IWageTable> mWageTables;

    /**
     * This method will do all of the plugins initialization of
     * various objects like commands, events, etc...
     */
    @Override
    public void onEnable ()
    {
        // Setup the Main Configuration class and likewise
        // any other needed config files, notably the WageTables.
        mConfigController = new ConfigController (this);
        mConfigController.createDefaultConfig ();
        mConfigController.registerConfigFiles ();

        // Temp Database access : Use the Database Factory here based on the Config file
        mDatabase = new MySQL_Conn (this,"localhost:3306", "creed_test", "creed", "test");

        // We need to register the listeners for the Plugin
        this.registerListeners ();

        if (!setupEconomy ())
        {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions ();
        setupChat();

        mCoreListener.setEconomyHook (mEconomy);

        /*The registering of the wage tables are below*/
        mWageTables = new HashMap<> ();
        registerWageTables ();


        // This will initialize the PlayerManager Singleton
        PlayerManager.Instance ().initializePlayerManager (this, mDatabase);

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
        /*Saving The configs will happen once the commenting yaml parser is implemented,
        right now we will only be reading from the configs although we will eventually
        be writing to the config files at a later date.*/

        // this.saveConfig ();
        // mConfigController.saveConfigs ();
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
    public Database cpGetDatabase ()
    {
        return mDatabase;
    }

    /**
     * This method registers all of the needed listeners for the Plugin
     */
    private void registerListeners ()
    {
        PluginManager pluginManager = this.getServer ().getPluginManager ();
        mCommandController = new CommandController (this);
        mCoreListener = new CoreListener (this);

        // Register the CraftyProfession Command Entry Point
        getCommand ("prof").setExecutor (mCommandController);

        // Register the Core Listener of CraftyProfessions
        pluginManager.registerEvents (mCoreListener, this);
    }

    private void registerWageTables ()
    {
        mWageTables.put ("Miner_Wage", new MinerWage());



        for (Map.Entry<String, IWageTable> table : mWageTables.entrySet ())
        {
            table.getValue ().readTable (mConfigController);
        }

    }

    public IWageTable getWageTable (String tableName)
    {
        return mWageTables.get (tableName);
    }

    /** NEED TO DELETE THIS LATER DEBUGGING ONLY
     * This method is Purely for the Use of obtaining a list of Materials to access later
     */
    private void writeMaterials ()
    {
        final String FILE_NAME = "C:\\Users\\Logan Cookman\\Desktop\\materials.txt";

        BufferedWriter bw = null;
        FileWriter fw = null;

        try
        {
            fw = new FileWriter (FILE_NAME);
            bw = new BufferedWriter (fw);

            for (Material material : Material.values ())
            {
                bw.write (material.toString () + "\n");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }
        finally
        {
            mLog.log (Level.INFO, "Writing Materials is DONE!");
            try
            {
                if (bw != null)
                {
                    bw.close ();
                }
                if (fw != null)
                {
                    fw.close ();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace ();
            }
        }
    }

    /**
     * Returns the Logger of the Spigot CP
     */
    public Logger cpGetLogger ()
    {
        return this.getLogger ();
    }

    /**
     * This method outlines the economy hook setup
     */
    public boolean cpSetupEconomy ()
    {
        return false;
    }

    /**
     * Get the Configuration Object of the Plugin
     */
    public AbsConfigController cpGetConfigController ()
    {
        return null;
    }

    /**
     * Get the Command Controller of the Plugin
     */
    public AbsCmdController cpGetCmdController ()
    {
        return null;
    }
    /**
     * Register the plugins listeners.
     */
    public void cpSetupListeners ()
    {

    }

    /**
     * Sets up the Database using the Spigot Config File
     */
    public void cpSetupDatabase ()
    {
        // Grab the Database Type from the config file
        String dbType = mConfigController.getDefaultString ("DatabaseType");

        // Construct the Connection Object from Factory

        // Give the Constructed connection type to mDatabase

        // If the type is MySQL send in the information into the factory.
    }
}
