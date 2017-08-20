package magnarisa.craftyprofessions.container;

import java.math.BigDecimal;

/**
 * This Interface outlines the common uses of a Wage Table for the
 * various professions within the Plugin.
 */
public interface IWageTable
{
    /**
     * <p> This Method is used in order to Hash a certain
     *     Item, Block, Crafting recipe etc.. In order to
     *     obtain the wage that that Object has associated
     *     with it within the Wage Table. </p>
     *
     * @param element This object to be mapped into the Table
     *
     * @return The Value stored at that Table Location or null
     *          if the Table does not have the desired Item.
     */
    <T> BigDecimal mapItem (T element, String... subStrings);

    /**
     * <p> This method will allow us to read the wage table
     *      from a specific file specified from the given
     *      tableName parameter. In general all of the wage
     *      table names should be in the format of
     *      profession_wage.yml</p>
     *
     * @param tableName The name of the file to read from the file.
     *
     * @return the IWageTable object that was read in from the file.
     */
    IWageTable readTable (String tableName);

    /**
     * <p> writeTable will essentially take any modified data
     *      and write it back to the file who's name is stored
     *      within the wage table itself.
     *
     *
     *
     * </p>
     */
    void writeTable ();
}
