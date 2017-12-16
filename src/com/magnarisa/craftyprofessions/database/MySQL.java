package com.magnarisa.craftyprofessions.database;

import java.sql.*;

public class MySQL extends Database
{
    private Connection mConnection;
    private String mDatabaseURL;
    private String mDBUser;
    private String mDBIdentifier;

    public MySQL (String URL, String user, String identifier)
    {
        mDatabaseURL = URL;
        mDBUser = user;
        mDBIdentifier = identifier;
    }

    public Connection db_connection ()
    {

    }
}
