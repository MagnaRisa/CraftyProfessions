package com.magnarisa.spigot_craftyprofessions.utility;

import com.magnarisa.spigot_craftyprofessions.CraftyProfessions;

import java.io.*;
import java.util.logging.Level;

public class SQLReader
{
    public static final String EOF = "-1";
    private static final CharSequence COMMENT = "-- ";
    private static final CharSequence STATEMENT_END = ";";

    private BufferedReader mSQLReader;

    /**************************************************************************
     * Method:      openReader
     *
     * Description: Opens a file for reading, specifically in this case an
     *                  SQL setup file using a string resource.
     *
     * Parameters:
     * @param resource - The path to the file to open.
     *
     * Return: None
     *************************************************************************/
    public void openReader (String resource) throws IOException
    {
        mSQLReader = new BufferedReader (new FileReader (resource));
    }

    /**************************************************************************
     * Method:      openReader
     *
     * Description: Opens a file for reading, specifically in this case an
     *                  SQL setup file using an InputStream.
     *
     * Parameters:
     * @param resource - The input stream to read from.
     *
     * Return: None
     *************************************************************************/
    public void openReader (InputStream resource) throws IOException
    {
        mSQLReader = new BufferedReader (new InputStreamReader (resource));
    }

    /**************************************************************************
     * Method:      closeReader
     *
     * Description: Closes and opened BufferedReader
     *
     * Parameters:  None
     *
     * Return:      None
     *************************************************************************/
    public void closeReader ()
    {
        try
        {
            mSQLReader.close ();
        }
        catch (IOException exception)
        {
            CraftyProfessions.LogMessage (Level.SEVERE, "Could Not Close Resource:" + exception);
        }
    }

    /**************************************************************************
     * Method:      readStatement
     *
     * Description: Reads lines of the file opened until we reach the end
     *              of an SQL Statement.
     *
     * Parameters:  None
     *
     * Return:
     * @return A single SQL Statement from the internal Reader
     *************************************************************************/
    public String readStatement ()
    {
        StringBuilder builder = new StringBuilder ();

        try
        {
            String line = mSQLReader.readLine ();

            if (null == line)
            {
                return EOF;
            }

            line = line.trim ();

            while (!line.contains (STATEMENT_END) || (line.contains (STATEMENT_END) && line.contains (COMMENT)))
            {
                if (!line.isEmpty () && !line.contains (COMMENT))
                {
                    line = line.replaceAll ("\t", " ");
                    builder.append (line);
                }
                line = mSQLReader.readLine ();
            }
            builder.append (line);
        }
        catch (IOException exception)
        {
            CraftyProfessions.LogMessage (Level.SEVERE, "Could Not Read From File:" + exception);
        }
        catch (NullPointerException exception)
        {
            CraftyProfessions.LogMessage (Level.SEVERE, "End of SQL should have no empty lines!");
        }
        return cleanStatement (builder);
    }

    /**************************************************************************
     * Method:      cleanStatement
     *
     * Description: Cleans the SQL Statement of it's semicolons
     *
     * Parameters:
     * @param sqlStatement - The statement to clean
     *
     * Return:
     * @return The cleaned SQL Stream
     *************************************************************************/
    private String cleanStatement (StringBuilder sqlStatement)
    {
        return sqlStatement.deleteCharAt (sqlStatement.length () - 1).toString ();
    }
}
