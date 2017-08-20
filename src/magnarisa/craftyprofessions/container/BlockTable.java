package magnarisa.craftyprofessions.container;

import com.google.gson.JsonObject;
import magnarisa.craftyprofessions.config.ConfigController;
import magnarisa.craftyprofessions.utility.WageTableParser;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * This implementation of a Wage Table is based around the Blocks
 * of Minecraft, this way any Professions that revolve around
 */
public abstract class BlockTable implements IWageTable
{
    private WageTableParser mParser;

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
    // protected HashMap<String, HashMap<String, BigDecimal>> mBlockMap;
    protected HashMap<String, HashMap<String, BigDecimal>> mBlockMap;

    protected BlockTable (WageTableParser parser)
    {
        mParser = parser;
        mBlockMap = new HashMap<>();
    }

    protected JsonObject parseJsonObject (String table)
    {
        return mParser.parseTable (table);
    }
}
