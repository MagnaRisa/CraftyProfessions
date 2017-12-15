package com.magnarisa.craftyprofessions.exceptions;

import org.bukkit.ChatColor;

/**
 * This Exception signifies if a command specified by the sender
 * is within the command map or not.
 */
public class CommandNotFoundException extends Exception
{
    public CommandNotFoundException ()
    {
        super (ChatColor.DARK_AQUA + "No command exists with those arguments");
    }

    public CommandNotFoundException (String message)
    {
        super (ChatColor.DARK_AQUA + message);
    }

}
