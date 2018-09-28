package com.creedfreak.common.professions;

import com.creedfreak.common.container.WageTableHandler;

import java.util.HashMap;

/**
 * This is the Abstract Class for a Profession. This will be
 * the class in which all Professions are inherited from and
 * thus all of the shared data and responsibilities will be
 * encapsulated here.
 */
public abstract class Profession
{
    private IWageTable mWageTable;

    protected Profession ()
    {
        mWageTable = WageTableHandler.getInstance ().GetWageTable (TableType.Miner);

        if (null == mWageTable)
        {
            throw new NullPointerException (this.getName () + " wage table is null cannot construct Profession.");
        }
    }


    /**
     * This method is used to return the Name of the Profession
     * to use in other places.
     *
     * @return The Name of the Profession
     */
    abstract public String getName ();

    /**
     * This method will essentialy do "work" of the profession. If an event is
     * passed to this method the hope is that the corresponding block is hashed
     * and the monetary value of that block or item is returned to the player.
     *
     * Here when I mention "event" it's the literal event that the Minecraft/ServerAPI
     * is generating and thus is getting past along to this method.
     *
     * @param element - The block, item, or action that the event generates.
     * @param profStatus - The current status of the players profession. What table
     *                   does the element get hashed into.
     *
     * @return - The value of the element found or null if nothing is found. The
     *              return of null in this case is used to indicate that we have
     *              the wrong wage table.
     */
    public double work (String element, String profStatus)
    {
        return mWageTable.mapItem (element, profStatus);
    }

    /**
     * This method is used to update the professions
     * status. For example from the "Miner_Payout" to
     * the "Stone_Affinity" profession status.
     *
     * Likewise calling this method will also set the
     * Miner Wage payout table to the corresponding
     * upgraded version. Miner_Payout -> Stone_Affinity
     */
    abstract void upgradeStatus (String newStatus);

    // Some way to pay a player or hold the money in a central location
    // Some way to generate the revenue
    // Some way to add and or remove XP gained for Professions
    // Some sort of Modifier method to track and update Modifiers
}
