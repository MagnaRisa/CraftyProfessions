package com.magnarisa;

import java.util.logging.Logger;

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
     * Returns the logger of CraftyProfessions regardless
     * of what server implementation is used.
     *
     * @return The Logger of the plugin
     */
    Logger cpGetLogger ();

    /**
     * This method outlines the economy hook setup
     */
    void cpSetupEconomy ();

    /**
     * Get the Configuration of the Plugin
     */
    void cpGetPluginConfiguration ();

    /**
     * Register the plugins listeners.
     */
    void cpSetupListeners ();
}
