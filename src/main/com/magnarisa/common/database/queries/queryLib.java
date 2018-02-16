package com.magnarisa.common.database.queries;

/**
 * This class houses the static queries that will need to be called to
 * retrieve data from the database. These static strings will be used
 * whenever we need to interface into the database. This should prevent
 * any type of SQL Injection while keeping all of the queries in one
 * easy to read and modify class.
 */
public class queryLib
{
    public static final String selectUserData = "SELECT * FROM Users WHERE UUID = ?";
    public static final String selectCareers = "SELECT Username "
                                            + "FROM Users, Careers, Professions"
                                            + "WHERE Users.UserID = Careers.UserID"
                                            + "? = Users.UserID";
    public static final String selectHobbies = "SELECT * FROM Hobbies";


}
