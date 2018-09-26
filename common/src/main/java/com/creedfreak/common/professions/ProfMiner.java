package com.creedfreak.common.professions;

import com.creedfreak.common.professions.IWageTable;
import com.creedfreak.common.professions.MinerWage;

import java.util.List;

public class ProfMiner implements IProfession
{
    public static final String PROF_NAME = "Miner";

    // This is the Users current Miner Status
    private String mMinerStatus;

    // These are the Augments a user has for this profession.
    // Note that these are not used to calculate the modifiers
    // after this list is initialized.
    private List<IAugment> mAugments;

    // These are the bonus values modified by the Augments of the professions.
    private Double mExpierenceBonus;
    private Double mIncomeBonus;
    private Double mTokenBonus;

    public ProfMiner (String status)
    {
        // What wage table will the player use?
        // This value is stored in the database until retrieved.
        mMinerStatus = status;
    }

    public String getName ()
    {
        return PROF_NAME;
    }

    /**
     * This method is called when the user upgrades their Miner profession status.
     * This will upgrade the users current states to their new status which will
     * point to a different wage table to be used for payment.
     *
     * @param newStatus - The new Miner status. i.e. Ore_Affinity or Stone_Affinity
     */
    public void upgradeStatus (String newStatus)
    {
        mMinerStatus = newStatus;
    }

}
