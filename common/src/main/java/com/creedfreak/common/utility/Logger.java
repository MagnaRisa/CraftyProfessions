package com.creedfreak.common.utility;

public class Logger
{
	private static Logger ourInstance = new Logger ();

	public static Logger getInstance ()
	{

		return ourInstance;
	}

	private Logger ()
	{

	}
}
