package com.creedfreak.common.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;

// TODO: Write better exception handling messages. This should log the error to the console and disable the plugin.
public class JsonWrapper
{

	private Gson mGson;
	private JsonReader mJsonReader = null;
	private JsonWriter mJsonWriter = null;

	private ClassLoader mClassLoader;

	public JsonWrapper ()
	{
		mGson = new GsonBuilder ().setPrettyPrinting ().create ();
		mClassLoader = this.getClass ().getClassLoader ();
	}

	/**
	 * readTable is used to read in Json objects from a file into a HashMap, where the Json (Key, Value) pair
	 * corresponds to the HashMap (Key, Value) pair. Likewise depending on the passed in type token, the HashMap
	 * may have more nested HashMaps or other type of objects.
	 *
	 * @param token - The Type to pass to the Json Reader in order to parse the Json objects correctly.
	 * @param resource - The path to the resource we want to parse.
	 *
	 * @return - A HashMap which contains the json information from the resource parameter.
	 */
	public HashMap readJson (Type token, String resource)
	{
		BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (mClassLoader.getResourceAsStream (resource)));
		HashMap parsedTable = null;

		try
		{
			mJsonReader = mGson.newJsonReader (bufferedReader);

			parsedTable = mGson.fromJson (mJsonReader, token);

			mJsonReader.close();
		}
		catch (IOException exception)
		{
			exception.printStackTrace ();
		}

		return parsedTable;
	}

	/**
	 * writeJson is used to write any HashMap out to a Json file. Each (Key, Value) pair in the HashMap gets
	 * written out to the file as a Json (Key, Value) pair.
	 *
	 * @param map - The HashMap to write out to file.
	 * @param token - The Type of the HashMap in which to write to the file.
	 * @param resource - The path to the file to write out to.
	 */
	public void writeJson (HashMap map, Type token, String resource)
	{
		BufferedWriter bufferedWriter;

		try
		{
			bufferedWriter = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (resource)));

			mJsonWriter = mGson.newJsonWriter (bufferedWriter);

			// Writes the map to the specified source opened by the Buffered Writer.
			mGson.toJson (map, token, mJsonWriter);

			mJsonWriter.close ();
		}
		catch (IOException exception)
		{
			exception.printStackTrace ();
		}
	}
}
