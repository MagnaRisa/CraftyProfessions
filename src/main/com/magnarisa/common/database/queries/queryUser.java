package com.magnarisa.common.database.queries;

import com.google.common.primitives.UnsignedLong;
import com.magnarisa.common.database.databaseConn.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;

public final class queryUser
{
    /**
     * <p> Checks to see if the specified userID has careers or not</p>
     *
     * @param userID The row id of the crafty player
     *
     * @param db The database to query
     *
     * @return True or False: If the specific user has careers or not
     */
    public boolean checkCareers (UnsignedLong userID, Database db)
    {
        PreparedStatement prepStmt;
        ResultSet resultSet;

        try
        {
            prepStmt = db.dbConnect ().prepareStatement (queryLib.queryUserData);
            resultSet = prepStmt.executeQuery ();

            return resultSet.next ();
        }
        catch (SQLException exception)
        {
            db.logDBMessage (Level.SEVERE, exception.getMessage ()
                + ": Unhandled exception while checking Careers!");
            return false;
        }
    }

    /**
     * <p> Checks to see if the specified userID has hobbies or not</p>
     *
     * @param userID The row id of the crafty player
     *
     * @param db The database to query
     *
     * @return True or False: If the specific user has hobbies or not
     */
    public boolean checkHobbies (UnsignedLong userID, Database db)
    {
        PreparedStatement prepStmt;
        ResultSet resultSet;

        try
        {
            prepStmt = db.dbConnect ().prepareStatement (queryLib.queryUserData);
            resultSet = prepStmt.executeQuery ();

            return resultSet.next ();
        }
        catch (SQLException exception)
        {

            db.logDBMessage (Level.SEVERE, exception.getMessage ()
                + ": Unhandled exception while checking Hobbies!");
            return false;
        }
    }

    /**
     * Retrieve the player from the database based on their UUID
     *
     * @param uuid The uuid of the player to retrieve
     * @param db The database to query
     *
     * @return The result set returned by the query
     */
    public ResultSet retrievePlayer (UUID uuid, Database db)
    {
        PreparedStatement prepStmt;
        ResultSet resultSet;

        try
        {
            prepStmt = db.dbConnect ().prepareStatement ();
            resultSet = prepStmt.executeQuery ();
        }
        catch (SQLException exception)
        {

        }

        return resultSet;
    }

}
