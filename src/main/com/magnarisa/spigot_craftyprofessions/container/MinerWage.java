package com.magnarisa.spigot_craftyprofessions.container;

import com.magnarisa.spigot_craftyprofessions.config.ConfigController;
import com.magnarisa.common.utility.CurrencyUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This Class is the implementation for the MinerWage Table
 * used to fetch the wage of a specific block mined or broken
 * based on that blocks name.
 */
public class MinerWage extends BlockTable
{
    public static final String MINER_PAYOUT = "Miner_Payout";
    private static final String STONE_AFFINITY = "Stone_Affinity";
    private static final String ORE_AFFINITY = "Ore_Affinity";
    private static final String MINER_WAGE_TABLE = "MinerWage";

    public MinerWage ()
    {
        super (TableName.Miner);
    }

    /**
     * This method will read in the specified table name labeled
     * resource in the parameters. The specified resource is then
     * grabbed from the ConfigController and the config file is
     * read into the MinerWage Table to be stored in memory
     * for quick access to the data.
     *
     * @param controller The configuration controller in order to gain
     *                   access to a specified resource file.
     */
    public void readTable (ConfigController controller)
    {
        YamlConfiguration wageTable = controller.getSpecialConfig (mTableName.getFileName ());

        mBlockMap.put (MINER_PAYOUT, new ConcurrentHashMap<> ());
        mBlockMap.put (STONE_AFFINITY, new ConcurrentHashMap<> ());
        mBlockMap.put (ORE_AFFINITY, new ConcurrentHashMap<> ());

        ConfigurationSection minerPayout = wageTable.getConfigurationSection (MINER_PAYOUT);
        Map<String, Object> table = minerPayout.getValues (true);

        for (Map.Entry<String, Object> entry : table.entrySet ())
        {
            mBlockMap.get (MINER_PAYOUT).put (entry.getKey(), CurrencyUtil.formatDouble ((Double) entry.getValue()));
        }

        minerPayout = wageTable.getConfigurationSection (STONE_AFFINITY);
        table = minerPayout.getValues (true);

        for (Map.Entry<String, Object> entry : table.entrySet ())
        {
            mBlockMap.get (STONE_AFFINITY).put (entry.getKey(), CurrencyUtil.formatDouble ((Double) entry.getValue()));
        }

        minerPayout = wageTable.getConfigurationSection (ORE_AFFINITY);
        table = minerPayout.getValues (true);

        for (Map.Entry<String, Object> entry : table.entrySet ())
        {
            mBlockMap.get (ORE_AFFINITY).put (entry.getKey(), CurrencyUtil.formatDouble ((Double) entry.getValue()));
        }
    }

    /**
     * The goal of this method is to be able to modify a value within the
     * Wage Table from a command or by other means.
     *
     * @param value The new value to modify the WageTable with
     * @param path The path to the key whose value you want to change
     *
     * @return True  - If the value change was successful
     *         False - If the value change was unsuccessful
     */
    public boolean modifyValue (BigDecimal value, String... path)
    {
        Map<String, BigDecimal> map = mBlockMap.get (path[0]);
        if (map == null)
        {
            return false;
        }

        BigDecimal oldValue = map.get (path[1]);
        if (oldValue == null)
        {
            return false;
        }

        mBlockMap.get(path[0]).put (path[1], value);
        return true;
    }

    /**
     * This method will allow grab the name of the WageTable
     *
     * @return The name of the wage table.
     */
    @Override
    public String getTableName ()
    {
        return MINER_WAGE_TABLE;
    }
}
