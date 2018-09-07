package com.creedfreak.common.professions;

import com.creedfreak.common.container.WageTableHandler;
import com.creedfreak.common.utility.JsonWrapper;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * This implementation of a Wage Table is based around the Blocks
 * of Minecraft, this way any Professions that revolve around
 */
public class BlockTable implements IWageTable
{
    /**
     * TODO: This Should be the table design that is used throughout all the wage tables.
     * So my intended purpose here is that whenever someone breaks or places
     * a block which could be a possibility for any jobs if configured for it
     * is to be able to handle multiple types of actions. So for instance If you
     * have the Miner Job and your able to break iron and place it for Money, we
     * will be able to have both types of actions both placing and breaking being
     * handled within this table. For Blocks this may not be too big of a deal since
     * you only really want to either place a block for a job or break it. But when handling
     * actions like breaking and planting crops this type of table should work nicely.
     */
    private HashMap mBlockMap;
    private TableName mTableName;
    private boolean mbHasChanged;

    public BlockTable (TableName tableName)
    {
        mTableName = tableName;
        mbHasChanged = false;
    }

    @Override
    public void readTable (String resource)
    {
        JsonWrapper wrapper = new JsonWrapper ();

        mBlockMap = wrapper.readJson (WageTableHandler.DEFAULT_WT_TYPE, resource);
    }

    /**
     * This method will map the given item into the mBlockMap
     * this will return a BigDecimal Value if the item is found
     * or null if the item is not found within the Map.
     * TODO: Reimplement this method, with more generics. Maybe create your own temporary block class and then interface that class with both spigot and sponge
     *
     * @param item The item to look for the in the BlockMap
     *
     * @return The BigDecimal Value that the Item maps to within mBlockMap
     */
    @Override
    public <T> BigDecimal mapItem (T item, String profStatus)
    {
        // Loop through the string args trying the different table types or specializations
        // A crafty player might have. When we find a match we stop the search.
        /*ConcurrentHashMap<String, BigDecimal> mapType = mBlockMap.get (profStatus);
        T generic;

        if (mapType != null)
        {
            generic = (Block) item;
        }
        else
        {
            return null;
        }

        return mapType.get (generic.getType().toString ());*/
        return null;
    }

    /** CURRENTLY UNTESTED! NEED TO REPLICATE THIS IN THE READING OF THE FILE AS WELL
     * This method will write the table specified by the internal parents
     * protected mTableName field.
     *
     * @param resource The file to write the json to.
     */
    @Override
    public void writeTable (String resource)
    {
        if (mbHasChanged)
        {
            JsonWrapper wrapper = new JsonWrapper ();

            wrapper.writeJson (mBlockMap, WageTableHandler.DEFAULT_WT_TYPE, resource);
        }
    }

    /**
     * @return if the table has been changed or not.
     */
    @Override
    public boolean hasChanged ()
    {
        return mbHasChanged;
    }

    /**
     *
     * @param key The key whose value is to be changed
     * @param value The value in which to modify the previous value
     *
     * @return If the modification was valid or not.
     */
    public boolean modifyValue (String key, Double value)
    {

        return false;
    }
}
