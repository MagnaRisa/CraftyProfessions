package com.creedfreak.common.exceptions;

import com.creedfreak.common.professions.IWageTable;

public class TableStateException extends Exception
{
	public TableStateException ()
	{
		super ("Table state error in Wage Table Handler");
	}

	public TableStateException (String message)
	{
		super (message);
	}
}
