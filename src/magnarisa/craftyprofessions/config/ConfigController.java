package magnarisa.craftyprofessions.config;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * This class is in charge of the main configuration files
 * and whether or not they have been created and parsed yet.
 * Most if not all config setup and parsing for the main
 * config file will happen through this class.
 */
public class ConfigController
{
    private JavaPlugin mPlugin = null;

    /**
     * The default constructor for the Configuration Controller.
     *
     * @param plugin - The CraftyProfessions Plugin to receive all the information from
     *                  in order to build and manage config files.
     */
    public ConfigController (JavaPlugin plugin)
    {
            mPlugin = plugin;
    }

    /**
     *  This method will create the necessary file specified by the name given as the
     *  parameter. If this file is already created then print to the console that it
     *  has been found and return.
     */
    public void createConfig (String fileName)
    {
        try
        {
            if (!mPlugin.getDataFolder ().exists ())
            {
                if (mPlugin.getDataFolder ().mkdir ())
                {
                    mPlugin.getLogger().info ("CraftyProfessions Data Folder has been created");
                }
            }

            File file = new File (mPlugin.getDataFolder (), fileName);

            if (!file.exists ())
            {
                mPlugin.getLogger ().info (fileName + " Not Found, Creating File and Loading Defaults!");
                mPlugin.saveDefaultConfig ();
            }
            else
            {
                mPlugin.getLogger ().info (fileName + " Found, Loading File!");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }
    }
}
