package magnarisa.craftyprofessions.container;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import magnarisa.craftyprofessions.utility.WageTableParser;
import org.bukkit.block.Block;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * This Class is the implementation for the MinerWage Table
 * used to fetch the wage of a specific block mined or broken
 * based on that blocks name.
 */
public class MinerWage extends BlockTable
{
    public MinerWage (WageTableParser parser)
    {
        super (parser);

        initializeTable ();
    }

    @Override
    public <T> BigDecimal mapItem (T item, String... subStrings)
    {
        HashMap<String, BigDecimal> mapType = mBlockMap.get (subStrings[1]);
        Block generic;

        if (item instanceof Block && mapType != null)
        {
            generic = (Block) item;
        }
        else
        {
            return null;
        }

        return mapType.get (generic.getType().toString ());
    }

    private void initializeTable ()
    {
        JsonObject json = parseJsonObject ("miner_wage");
        if (json == null)
        {
            return;
        }
        // This line of code will fill in the mBlockmap with the given Type of the Miner_Wage table from miner_wage.json
        mBlockMap = new Gson ().fromJson (json.getAsString (), new TypeToken<HashMap<String, HashMap<String, BigDecimal>>>(){}.getType ());
    }

    /**
     * This method is the implementation of the IWageTable's interface
     * in which the table is able to be read into the MinerWage table
     * given the name of the table.
     *
     * @param tableName The name of the file to read from the file.
     *
     * @return A Miner Wage table read from the file with the given tableName
     */
    public IWageTable readTable (String tableName)
    {
        return null;
    }

    public void writeTable ()
    {

    }
}
