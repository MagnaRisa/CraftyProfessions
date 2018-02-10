package com.magnarisa.common.professions;

/**
 * This is the Abstract Class for a Profession. This will be
 * the class in which all Professions are inherited from and
 * thus all of the shared data and responsibilities will be
 * encapsulated here.
 */
public interface IProfession
{
    /**
     * This method is used to return the Name of the Profession
     * to use in other places.
     *
     * @return The Name of the Profession
     */
    String getName ();

    /**
     * This method will hash the given item
     *
     *
     */
    void generateRevenue();


    // Some way to pay a player or hold the money in a central location
    // Some way to generate the revenue
    // Some way to add and or remove XP gained for Professions
    // Some sort of Modifier method to track and update Modifiers
}
