package com.magnarisa.craftyprofessions.database;

import java.sql.*;

public abstract class Database
{
    protected

    public abstract Connection db_connect ();

    public void db_close (PreparedStatement stmt, ResultSet set, Connection conn)
    {
        try
        {
            if (stmt != null)
            {
                stmt.close ();
            }
            if (set != null)
            {
                set.close ();
            }
            if (conn != null)
            {
                conn.close ();
            }
        }
        catch (SQLException exception)
        {
            mCraftyProfessions.getLogger ().log (Level.SEVERE, "Failed to close SQL Connection", e);
        }
    }
//    {
//        try
//        {
//            mConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/DB?user="
//                                                        + mDBUser + "&password=" + mDBIdentifier);
//        }
//        catch (SQLException exception)
//        {
//            Bukkit.getLogger ().log (Level.SEVERE, exception.getMessage ().concat (Integer.toString (exception.getErrorCode ())));
//        }
//
//        return mConnection;
//    }
}
