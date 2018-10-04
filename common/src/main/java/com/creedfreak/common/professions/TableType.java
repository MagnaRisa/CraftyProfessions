package com.creedfreak.common.professions;

import java.util.HashMap;

/**
 * This enum is used to primarily be a consolidation
 * of the wage table names so the only point of access
 * to the files names should be by the use of this enum.
 * This will cut down on misspelling of the wage table
 * names and overall a cleaner look within the code.
 */
public enum TableType
{
    Miner("miner_wage.yml", "Miner_Payout", "Ore_Affinity", "Stone_Affinity"),
    Alchemy("alchemy_wage.yml", "Alchemy_Payout"),
    Angler("angler_wage.yml", "Angler_Payout"),
    Architect("architect_wage.yml", "Architect_Payout"),
    Crafter("crafter_wage.yml", "Crafter_Payout"),
    Enchanter("enchanter_wage.yml", "Enchanter_Payout"),
    Farmer("farmer_wage.yml", "Farmer_Payout"),
    Knight("knight_wage.yml", "Knight_Payout"),
    Lumberjack("lumberjack_wage.yml", "Lumberjack_Payout"),
    Merchant("merchant_wage.yml", "Merchant_Payout");

    private String mFileName;
    private String mDefaultTier;
    private HashMap<String, String> mTierMap = new HashMap<> ();

    /**
     * The Default constructor for a TableType enumerated object.
     * Assigns the name of the file to read and write to, defines
     * the default tier for the wage table, and assigns all other
     * non default tiers to the mTierMap to be used at a later date.
     *
     * @param fileName - The name of the file to read and write from
     * @param defaultTier - The default tier to use for payouts
     * @param wageTiers - Any other tier that is not Default.
     */
    TableType (String fileName, String defaultTier, String ... wageTiers)
    {
        mFileName = fileName;
        mDefaultTier = defaultTier;

        if (0 != wageTiers.length)
        {
            for (String key : wageTiers)
            {
                mTierMap.put (key, key);
            }
        }
    }

    /**
     * Returns the file name of the wage table.
     *
     * @return mFileName
     */
    public String getFileName ()
    {
        return mFileName;
    }

    /**
     * Grabs the Map of tiers for the TableType.
     *
     * @return mTierMap
     */
    public HashMap<String, String> getTableTiers ()
    {
        return mTierMap;
    }

    /**
     * Return the default tier of the TableType
     *
     * @return mDefaultTier
     */
    public String getDefaultTier ()
    {
        return mDefaultTier;
    }
}


