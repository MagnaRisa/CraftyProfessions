package com.magnarisa.spigot_craftyprofessions.commands.DatabaseCommands;

import com.magnarisa.spigot_craftyprofessions.commands.DatabaseCommand;
import com.magnarisa.spigot_craftyprofessions.container.CommandData;
import com.magnarisa.spigot_craftyprofessions.container.CraftyPlayer;
import com.magnarisa.spigot_craftyprofessions.database.Database;

public class CommandReaderTest extends DatabaseCommand
{
    public CommandReaderTest (Database db)
    {
        super (db, new CommandData (
            "dbreadertest",
            "This command will test the SQLReader for SQL Files.",
            "/prof dbreadertest",
            "spigot_craftyprofessions.admin.debug"
        ));
    }

    @Override
    public boolean execute (CraftyPlayer sender, String... args)
    {
        mDatabase.dbConnect ();

        mDatabase.testReader (sender);

        return true;
    }
}