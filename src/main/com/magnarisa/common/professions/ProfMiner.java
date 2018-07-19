package com.magnarisa.common.professions;

import com.magnarisa.spigot_craftyprofessions.container.IWageTable;
import com.magnarisa.spigot_craftyprofessions.container.MinerWage;

import java.util.List;

public class ProfMiner implements IProfession
{
    public static final String PROF_NAME = "Miner";

    // This is the Users current Miner Status
    private String mMinerStatus;
    // The reference to the Concurrent Wage Table
    private MinerWage mValueTable;

    // These are the Augments a user has for this profession.
    // Note that these are not used to calculate the modifiers
    // after this list is initialized.
    private List<IAugment> mAugments;

    // These are the bonus values modified by the Augments of the professions.
    private Double mExpierenceBonus;
    private Double mIncomeBonus;
    private Double mTokenBonus;

    public ProfMiner (IWageTable wageTable, String status)
    {
        // Check the wage table before passing it into this constructor
        mValueTable = (MinerWage) wageTable;

        // Set the users Profession Status
        if (status.equalsIgnoreCase ("default"))
        {
            mMinerStatus = MinerWage.MINER_PAYOUT;
        }
        else
        {
            mMinerStatus = status;
        }
    }

    /**
     * This method is used to return the Name of this Profession
     *
     * @return The Name of the Profession [Miner]
     */
    public String getName ()
    {
        return PROF_NAME;
    }

    public void upgradeStatus (String newStatus)
    {
        mMinerStatus = newStatus;
    }

}
