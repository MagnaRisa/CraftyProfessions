package magnarisa.craftyprofessions.config;

import javafx.util.Pair;
import magnarisa.craftyprofessions.exceptions.ConfigNotFoundException;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * This class is in charge of the main configuration files
 * and whether or not they have been created and parsed yet.
 * Most if not all config setup and parsing for the main
 * config file will happen through this class.
 */
public class ConfigController
{
    private final String[] WAGE_NAMES = {"miner_wage.yml"};

    private JavaPlugin mPlugin = null;

    private File mConfigFile;
    private FileConfiguration mConfig;

    private Map<String, File> mWageFiles;
    private Map<String, FileConfiguration> mWageConfigs;

    /**
     * The default constructor for the Configuration Controller.
     *
     * @param plugin - The CraftyProfessions Plugin to receive all the information from
     *                  in order to build and manage config files.
     */
    public ConfigController (JavaPlugin plugin)
    {
        mPlugin = plugin;
        mWageFiles = new HashMap<> ();
        mWageConfigs = new HashMap<> ();
    }

    /**
     * This method will initialize all of the configuration files
     */
    public void initilizeConfigFiles ()
    {
        createDefaultConfig ();
        registerConfigFiles ();
        saveConfigs ();
    }

    /**
     *  This method will create the default configuration file of the plugin CraftyProfessions.
     *  The name of the file should always be config.yml
     */
    private void createDefaultConfig ()
    {
        try
        {
            if (!mPlugin.getDataFolder ().exists ())
            {
                mPlugin.getDataFolder ().mkdirs ();
                mPlugin.getLogger().info ("CraftyProfessions Data Folder has been created");
            }

            mConfigFile = new File (mPlugin.getDataFolder (), "config.yml");

            if (!mConfigFile.exists ())
            {
                mPlugin.getLogger ().info ("config.yml Not Found, Creating File and Loading Defaults!");
                mPlugin.saveDefaultConfig ();
            }
            else
            {
                mPlugin.getLogger ().info ("config.yml Found, Loading File!");
            }
            mPlugin.getConfig ();
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }
    }

    /**
     * This method will allow the saving of a single config file based on the file name
     *
     * @param fileName The file to find and save
     */
    public void saveConfig (String fileName) throws IOException, ConfigNotFoundException
    {
        FileConfiguration configuration = mWageConfigs.get (fileName);
        File file = mWageFiles.get (fileName);

        if (configuration == null || file == null)
        {
            throw new ConfigNotFoundException ("Could not find config labeled " + fileName);
        }

        configuration.save (file);
    }

    /**
     * This method will save all of the config files.
     */
    public void saveConfigs ()
    {
        try
        {
            mPlugin.saveDefaultConfig ();

            for (String fileName : WAGE_NAMES)
            {
                saveConfig (fileName);
            }
        }
        catch (IOException | ConfigNotFoundException e)
        {
            mPlugin.getLogger ().log (Level.SEVERE, "Exception caught while saving configs: ", e.getMessage ());
        }

    }

    /**
     * This method will register all of the default config files based on the names
     * within the WAGE_NAMES final variable. To be honest it seems quite crude to do
     * it this way, but it should be fine until I get further into the production of
     * the plugin. This means that all of the wage table files that are necessary
     * for running the plugin are created and registered here.
     *
     * TODO:  Create the ability to have custom files created and default files revoked [Future]
     */
    private void registerConfigFiles ()
    {
        for (String fileName : WAGE_NAMES)
        {
            File pushFile;
            FileConfiguration pushConfig;

            pushFile = checkFile (fileName);
            pushConfig = checkConfiguration (pushFile);

            mWageFiles.put (fileName, pushFile);
            mWageConfigs.put (fileName, pushConfig);
        }
    }

    /**
     * This method will check to see if the file name passed in as the parameter
     * is already created or not, and if not it will save the file resource inside
     * of the plugin directory, otherwise it will return the file that was created
     * without putting it within the plugin dir.
     *
     * @param fileName The name of the Configuration File to generate
     *
     * @return The created file based on the fileName parameter
     */
    private File checkFile (String fileName)
    {
        File configFile = new File (mPlugin.getDataFolder (), fileName);

        if (!configFile.exists ())
        {
            mPlugin.saveResource (configFile.getName (), false);
        }

        return configFile;
    }

    /**
     * This method will create a FileConfiguration out of the File passed in as the
     * parameter. We will then load the config based on the file passed in, once it's
     * loaded we will return the loaded config file.
     *
     * @param file The file to create the FileConfiguration for.
     *
     * @return The File Config created based off the file passed into the method.
     */
    private FileConfiguration checkConfiguration (File file)
    {
        FileConfiguration fileConfig = new YamlConfiguration ();

        try
        {
            fileConfig.load (file);
        }
        catch (IOException | InvalidConfigurationException e)
        {
            mPlugin.getLogger().log (Level.SEVERE, e.getMessage ());
            e.printStackTrace ();
        }

        return fileConfig;
    }
}
