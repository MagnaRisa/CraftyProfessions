package com.magnarisa.craftyprofessions.container;

/**
 * This enum is used to primarily be a consolidation
 * of the wage table names so the only point of access
 * to the files names should be by the use of this enum.
 * This will cut down on misspelling of the wage table
 * names and overall a cleaner look within the code.
 */
public enum TableName
{
    Miner("miner_wage.yml"),
    Alchemy("alchemy_wage.yml"),
    Angler("angler_wage.yml"),
    Architect("architect_wage.yml"),
    Crafter("crafter_wage.yml"),
    Enchanter("enchanter_wage.yml"),
    Farmer("farmer_wage.yml"),
    Knight("knight_wage.yml"),
    Lumberjack("lumberjack_wage.yml"),
    Merchant("merchant_wage.yml");

    private String mFileName;

    private TableName (String fileName)
    {
        mFileName = fileName;
    }

    public String getFileName ()
    {
        return mFileName;
    }
}


