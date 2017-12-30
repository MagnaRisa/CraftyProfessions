package com.magnarisa.spigot_craftyprofessions.database;

import com.magnarisa.ICraftyProfessions;
import com.magnarisa.spigot_craftyprofessions.CraftyProfessions;
import com.magnarisa.spigot_craftyprofessions.config.ConfigController;

import java.util.logging.Level;

public class DatabaseFactory
{
    public static Database buildDatabase (ICraftyProfessions plugin,
        ConfigController config)
    {
        String dbType = config.getString ("DatabaseType");
        Database db = null;

        if (dbType.equalsIgnoreCase ("sqlite"))
        {
            CraftyProfessions.LogMessage (Level.INFO, Database.DATABASE_PREFIX, "Constructing SQLite Connection.");
            db = new SQLite_Conn (plugin, config);
        }
        else if (dbType.equalsIgnoreCase ("mysql"))
        {
            String host = config.getString ("MySQL.host");
            String database = config.getString ("MySQL.db_name");
            String user = config.getString ("MySQL.user");
            String identifier = config.getString ("MySQL.password");

            CraftyProfessions.LogMessage (Level.INFO,  Database.DATABASE_PREFIX,"Constructing MySQL Connection.");
            db = new MySQL_Conn (plugin, config, host, database, user,
                identifier);
        }

        return db;
    }
}
