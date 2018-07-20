package com.creedfreak.common.professions;

import java.math.BigDecimal;

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
     * [MAY NOT NEED THIS]
     *
     * This method will hash the given item
     *
     *
     */
    // void generateRevenue();

    /**
     * This method is used to update the professions
     * status. For example from the "Miner_Payout" to
     * the "Stone_Affinity" profession status.
     *
     * Likewise calling this method will also set the
     * Miner Wage payout table to the corresponding
     * upgraded version. Miner_Payout -> Stone_Affinity
     */
    void upgradeStatus (String newStatus);

    // Some way to pay a player or hold the money in a central location
    // Some way to generate the revenue
    // Some way to add and or remove XP gained for Professions
    // Some sort of Modifier method to track and update Modifiers
}
