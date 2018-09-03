package com.creedfreak.common.professions;

import com.creedfreak.common.AbsConfigController;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This implementation of a Wage Table is based around the Blocks
 * of Minecraft, this way any Professions that revolve around
 */
public abstract class BlockTable implements IWageTable
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
    protected ConcurrentHashMap<String, ConcurrentHashMap<String, BigDecimal>> mBlockMap;
    protected TableName mTableName;
    private boolean mbHasChanged;

    protected BlockTable (TableName tableName)
    {
        mTableName = tableName;
        mBlockMap = new ConcurrentHashMap<> ();
        mbHasChanged = false;
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
     * @param controller The configuration controller in order to gain
     *                   access to a specified resource file.
     *
     * TODO: Fix this Method, it's currently really Broken
     */
    @Override
    public void writeTable (AbsConfigController controller)
    {
        Gson rootGson = new Gson ();
        // rootGson.toJson
//        JSONObject rootObject = new JSONObject (new HashMap<String, BigDecimal> ());
//      YamlConfiguration wageTable = controller.getSpecialConfig (mTableName.getFileName ());

        try
        {
            FileWriter writer = new FileWriter ("");

            for (Map.Entry<String, ConcurrentHashMap<String, BigDecimal>> tableEntry : mBlockMap.entrySet ())
            {
//                JSONObject childObject = new JSONObject (new HashMap<String, BigDecimal> ());

                ConcurrentHashMap<String, BigDecimal> tableMap = tableEntry.getValue ();
                ConcurrentHashMap<String, BigDecimal> blockMapSection = mBlockMap.get (tableEntry.getKey ());

                for (Map.Entry<String, BigDecimal> entry : tableMap.entrySet ())
                {
                    // Build the json object
//                    childObject.put (entry.getKey(), entry.getValue ());
                }

//                rootObject.put (tableEntry.getKey (), childObject);
            }

//            writer.write (rootObject.toJSONString ());
        }
        catch (IOException exception)
        {
            exception.printStackTrace ();
        }
    }


//    for (Map.Entry<String, ConcurrentHashMap<String, BigDecimal>> tableEntry : mBlockMap.entrySet ())
//    {
//        ConcurrentHashMap<String, BigDecimal> tableMap = tableEntry.getValue ();
//        ConcurrentHashMap<String, BigDecimal> blockMapSection = mBlockMap.get (tableEntry.getKey ());
//
//
//
//        for (Map.Entry<String, BigDecimal> entry : tableMap.entrySet ())
//        {
//            object.put (tableEntry.getKey() + "." + entry.getKey (), )
//            wageTable.set (tableEntry.getKey () + "." + entry.getKey (), blockMapSection.get (entry.getKey ()));
//        }
//    }
//
//            controller.saveConfig (wageTable, mTableName.getFileName ());


    @Override
    public boolean hasChanged ()
    {
        return mbHasChanged;
    }
}
