package com.magnarisa.spigot_craftyprofessions.commands.DatabaseCommands;

import com.magnarisa.spigot_craftyprofessions.commands.DatabaseCommand;
import com.magnarisa.spigot_craftyprofessions.container.CommandData;
import com.magnarisa.spigot_craftyprofessions.container.CraftyPlayer;
import com.magnarisa.spigot_craftyprofessions.database.Database;
import com.magnarisa.spigot_craftyprofessions.database.MySQL_Conn;
import javafx.util.Pair;

import java.util.List;

public class CommandTest extends DatabaseCommand
{
    public CommandTest (Database db)
    {
        super (db, new CommandData (
            "dbtest",
            "This command will test the connection of the database and query the database",
            "/prof dbtest",
            "spigot_craftyprofessions.admin.debug"
        ));
    }

    @Override
    public boolean execute (CraftyPlayer sender, String... args)
    {
        MySQL_Conn conn = (MySQL_Conn) mDatabase;
        mDatabase.db_connect ();
        List<Pair<Integer, String>> array = conn.testDBconnection ();

        for (Pair<Integer, String> pair : array)
        {
            sender.sendMessage (pair.getKey ().toString () + " | " + pair.getValue ());
        }

        return true;
    }
}
