package com.magnarisa.craftyprofessions.container;

import com.magnarisa.craftyprofessions.config.ConfigController;

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
    <T> BigDecimal mapItem (T element, String... stringArgs);

    /**
     * <p> This method will allow us to read the wage table
     *      from a specific file specified from the given
     *      tableName parameter. In general all of the wage
     *      table names should be in the format of
     *      profession_wage.yml</p>
     *
     * @param controller The config controller in which to retrieve
     *                   the configuration for reading.
     */
    void readTable (ConfigController controller);

    /**
     * <p> writeTable will essentially take any modified data
     *      and write it back to the file who's name is stored
     *      within the wage table itself. This method will be
     *      called whenever the command to modify the table
     *      is called. </p>
     *
     * @param controller The config controller in which to retrieve
     *                   the configuration for writing.
     */
    void writeTable (ConfigController controller);

    /**
     * This method will return an internal boolean which will
     * signify if the WageTable has changed after it has been read
     * in from. This will allow for quicker writing times whenever
     * saving the wage tables.
     *
     * @return True  - If the Table has been modified
     *         False - If the Table has not been modified
     */
    boolean hasChanged ();

    /**
     * This method will allow the modification of a specific value
     * within the wage table.
     *
     * @param path The path to the key whose value you want to change
     * @param newValue The new value to set into the given paths value
     *
     * @return True  - If the operation was a success
     *         False - If the operation was a failure
     */
    boolean modifyValue (BigDecimal newValue, String... path);
}
