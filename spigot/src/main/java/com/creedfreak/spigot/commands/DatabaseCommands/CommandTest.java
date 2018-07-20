package com.creedfreak.spigot.commands.DatabaseCommands;

import com.creedfreak.spigot.commands.DatabaseCommand;
import com.creedfreak.spigot.container.CommandData;
import com.creedfreak.spigot.container.CraftyPlayer;
import com.creedfreak.common.database.databaseConn.Database;
import com.creedfreak.common.database.databaseConn.MySQL_Conn;
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
        mDatabase.dbConnect ();
        List<Pair<Integer, String>> array = conn.testDBconnection ();

        for (Pair<Integer, String> pair : array)
        {
            sender.sendMessage (pair.getKey ().toString () + " | " + pair.getValue ());
        }

        return true;
    }
}
