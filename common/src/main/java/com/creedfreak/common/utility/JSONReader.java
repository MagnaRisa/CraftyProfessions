package com.creedfreak.common.utility;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JSONReader
{
    private Gson mJsonObject = new Gson ();
    private Type mRetType = new TypeToken<Map<String, ConcurrentHashMap<String, BigDecimal>>> (){}.getType ();

//    private ConcurrentHashMap<String, Map> mJsonObject;

    // May not need this constructor
    public JSONReader () { }

    public ConcurrentHashMap<String, ConcurrentHashMap<String, BigDecimal>> readJsonObject (String fileName)
    {
        Map retMap = new ConcurrentHashMap<String, ConcurrentHashMap<String, BigDecimal>> ();

        try
        {
            BufferedReader inFile = new BufferedReader (new FileReader (fileName));

            retMap = mJsonObject.fromJson (inFile, mRetType);
        }
        catch (IOException exception)
        {
            exception.printStackTrace ();
        }

        return null;
    }

    public void writeJsonObject (String fileName, ConcurrentHashMap<String, Object> jsonMap)
    {

    }

}
