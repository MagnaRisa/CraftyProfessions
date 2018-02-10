package com.magnarisa.common.utility;

public class LevelEquation
{
    public static Double calculateLevel (Double baseExp, int currentLevel,
        Double prevLevelExp)
    {
        if (currentLevel == 1)
        {
            return baseExp;
        }
        else
        {
            return (baseExp * currentLevel) + prevLevelExp;
        }
    }
}
