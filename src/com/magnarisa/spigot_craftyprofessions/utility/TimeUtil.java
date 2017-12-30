package com.magnarisa.spigot_craftyprofessions.utility;

public class TimeUtil
{
    private static final Double MILLISECONDS = 1000000D;
    private static final Double SECONDS = 1000000000D;

    public static Double toMilliseconds (long nanoSeconds)
    {
        return nanoSeconds / MILLISECONDS;
    }

    public static Double toSeconds (long nanoSeconds)
    {
        return nanoSeconds / SECONDS;
    }
}
