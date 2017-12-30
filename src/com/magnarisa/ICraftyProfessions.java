package com.magnarisa;

import com.magnarisa.spigot_craftyprofessions.database.Database;

import java.io.InputStream;
import java.util.logging.Level;

/**
 * This class is the main Abstraction layer for CraftyProfessions
 * in which we can use multiple plugin architecture. For example
 * there is going to be a Spigot Version of CraftyProfessions and
 * a Sponge Version of CraftyProfessions. This will allow for
 * very minimal changes within the code when dealing with
 * different servers.
 *
 * Planned Server Implementations: Spigot and Sponge
 *
 * This interface outlines all of the common functionality
 * between both Spigot and Sponge implementations that
 * CraftyProfessions will need to use throughout the
 * project.
 */
public interface ICraftyProfessions
{
    /**
     * Performs a log to the console window.
     */
    void Log (Level level, String message);

    /**
     * This method outlines the economy hook setup
     */
    boolean cpSetupEconomy ();

    /**
     * Get the Configuration of the Plugin
     */
    AbsConfigController cpGetConfigController ();

    AbsCmdController cpGetCmdController ();

    /**
     * Register the plugins listeners.
     */
    void cpSetupListeners ();

    /**
     * Grab the database
     *
     * @return
     */
    Database cpGetDatabase ();

    void cpSetupDatabase ();

    InputStream cpGetResource (String resource);
}
