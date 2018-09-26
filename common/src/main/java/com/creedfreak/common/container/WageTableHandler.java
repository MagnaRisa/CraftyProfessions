package com.creedfreak.common.container;

import com.creedfreak.common.professions.BlockTable;
import com.creedfreak.common.professions.IProfession;
import com.creedfreak.common.professions.IWageTable;
import com.creedfreak.common.professions.TableType;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The Wage Table Handler will
 *
 *
 * The Wage Table Handler also implements the method
 */
public class WageTableHandler
{
	public static final Type DEFAULT_WT_TYPE =
			new TypeToken<HashMap<String, HashMap<String, Double>>> () {}.getType ();

	private static WageTableHandler Instance = new WageTableHandler ();

	private HashMap<TableType, IWageTable> mTableHandler;

	/**
	 * The default constructor for the WageTableHandler. This is private to
	 * prevent any other instantiations elsewhere to uphold the
	 * Singleton Pattern.
	 */
	private WageTableHandler ()
	{
		mTableHandler = new HashMap<>();
	}

	/**
	 * Return the singular instance of the Wage Table Handler.
	 *
	 * @return Instance - The only Wage Table Handler
	 */
	public static WageTableHandler getInstance ()
	{
		return Instance;
	}

	/**
	 * InitializeWageTables will use the default wage table name array to
	 * read in all of the wage tables and store them within the internal
	 * Table Handler object.
	 *
	 * This method also doubles as the initialization of the Flyweight factory
	 * which handles all of the Wage Tables for dispersal at a later time after
	 * they have been initialized.
	 */
	public void InitializeWageTables (boolean debug, Logger Log)
	{
		// Initialize all block tables here.
		mTableHandler.put (TableType.Miner, new BlockTable (TableType.Miner));
		mTableHandler.put (TableType.Architect, new BlockTable (TableType.Architect));

		// Initialize all item related tables here.
		// mTableHandler.put (TableType.Alchemy, new AlchemyWage ());

		if (debug)
		{
			Log.info ("Starting Initialization of Wage Tables...");
		}

		// Read in all of the wage tables within the wage table handler.
		for (Map.Entry<TableType, IWageTable> entry : mTableHandler.entrySet ())
		{
			entry.getValue ().readTable (entry.getKey ().getFileName());
			if (debug)
			{
				Log.info ("Initialized " + entry.getKey ().getFileName ());
			}
		}

		if (debug)
		{
			Log.info ("Initialization of Wage Tables are complete!");
		}
	}

	/**
	 * WriteWageTables will write out all of the wage tables to their appropriate
	 * files. In the event of changes made to the files marked by a bit within the
	 * wage table we will write that table out to file. This should only occur when
	 * the onDisable event has been fired.
	 */
	public void WriteWageTables ()
	{
		for (Map.Entry<TableType, IWageTable> entry : mTableHandler.entrySet ())
		{
			entry.getValue ().writeTable (entry.getKey ().getFileName ());
		}
	}

	/**
	 * ModifiedTables will return a list of Wage Tables that have been modified
	 * since runtime and has not been written already to their respective file.
	 * This is mainly used to display the modified files to a user to track.
	 *
	 * @return modifiedTables - The list of modified tables.
	 */
	public List<TableType> ModifiedTables ()
	{
		List<TableType> modifiedTables = new ArrayList<>();

		for (Map.Entry<TableType, IWageTable> entry : mTableHandler.entrySet ())
		{
			if (entry.getValue ().hasChanged ())
			{
				modifiedTables.add (entry.getKey ());
			}
		}

		return modifiedTables;
	}

	/**
	 * The factory method to retrieve the Wage Tables according to the
	 * flyweight pattern.
	 *
	 * @param tableType - The type of table reference to return.
	 *
	 * @return - mTableHandler.get(tableType) - The specified Wage Table.
	 */
	public IWageTable GetWageTable (TableType tableType)
	{
		return mTableHandler.get (tableType);
	}
}
