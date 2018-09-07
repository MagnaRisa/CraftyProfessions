package com.creedfreak.common.container;

import com.creedfreak.common.professions.BlockTable;
import com.creedfreak.common.professions.IWageTable;
import com.creedfreak.common.professions.MinerWage;
import com.creedfreak.common.professions.TableName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WageTableHandler
{
	public static final Type DEFAULT_WT_TYPE =
			new TypeToken<HashMap<String, HashMap<String, Double>>> () {}.getType ();

	private static WageTableHandler Instance = new WageTableHandler ();

	private HashMap<TableName, IWageTable> mTableHandler;

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
	 */
	public void InitializeWageTables ()
	{
		// Initialize all tables here.
		mTableHandler.put (TableName.Miner, new BlockTable (TableName.Miner));
		// mTableHandler.put (TableName.Alchemy, new AlchemyWage ());

		// Read in all of the wage tables within the wage table handler.
		for (Map.Entry<TableName, IWageTable> entry : mTableHandler.entrySet ())
		{
			entry.getValue ().readTable (entry.getKey ().getFileName());
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
		for (Map.Entry<TableName, IWageTable> entry : mTableHandler.entrySet ())
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
	public List<TableName> ModifiedTables ()
	{
		List<TableName> modifiedTables = new ArrayList<>();

		for (Map.Entry<TableName, IWageTable> entry : mTableHandler.entrySet ())
		{
			if (entry.getValue ().hasChanged ())
			{
				modifiedTables.add (entry.getKey ());
			}
		}

		return modifiedTables;
	}
}
