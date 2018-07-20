package com.creedfreak.sponge;

import com.creedfreak.common.AbsCmdController;
import com.creedfreak.common.AbsConfigController;
import com.creedfreak.common.ICraftyProfessions;
import com.creedfreak.common.database.databaseConn.Database;
import java.io.*;
import java.util.logging.Level;


/**
 * Authors: CreedTheFreak
 *
 * This is the entry point class for the CraftyProfessions plugin.
 */
public class Sponge_CraftyProfessions implements ICraftyProfessions
{
   public Sponge_CraftyProfessions ()
   {

   }

   public void LogMessage (Level level, String message)
   {

   }

   public void LogMessage (Level level, String subSystemPrefix, String message)
   {

   }

   /**
    * Performs a log to the console window.
    */
   public void Log (Level level, String message)
   {

   }

   /**
    * This method outlines the economy hook setup
    */
   public boolean cpSetupEconomy ()
   {
      return false;
   }

   /**
    * Get the Configuration of the Plugin
    */
   public AbsConfigController cpGetConfigController ()
   {
      return null;
   }

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
    * Grab the database
    */
   public Database cpGetDatabase ()
   {
      return null;
   }

   public void cpSetupDatabase ()
   {

   }

   public InputStream cpGetResource (String resource)
   {
      return null;
   }

   public File cpGetResourceFile ()
   {
      return null;
   }
}
