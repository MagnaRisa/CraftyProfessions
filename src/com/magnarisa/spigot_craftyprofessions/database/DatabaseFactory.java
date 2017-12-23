package com.magnarisa.spigot_craftyprofessions.database;

import com.magnarisa.ICraftyProfessions;
import com.magnarisa.spigot_craftyprofessions.config.ConfigController;

public class DatabaseFactory
{
    public static Database buildDatabase (ICraftyProfessions plugin, ConfigController config, String dbType)
    {
        Database db = null;
        String host = config.getDefaultString ("MySQL.host");
        String database = config.getDefaultString ("MySQL.db_name");
        String user = config.getDefaultString ("MySQL.user");
        String identifier = config.getDefaultString ("MySQL.password");

        if (dbType.equalsIgnoreCase ("sqlite"))
        {
            db = new SQLite_Conn (plugin);
        }
        else if (dbType.equalsIgnoreCase ("mysql"))
        {
            db = new MySQL_Conn (plugin, host, database, user, identifier);
        }

        return db;
    }
}
