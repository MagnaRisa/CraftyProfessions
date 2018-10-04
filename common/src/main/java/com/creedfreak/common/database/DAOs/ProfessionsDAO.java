package com.creedfreak.common.database.DAOs;

import com.google.common.primitives.UnsignedLong;
import com.creedfreak.common.database.databaseConn.Database;
import com.creedfreak.common.professions.Profession;

import java.util.List;

public class ProfessionsDAO implements IDaoBase<Profession>
{
    private Database mDatabase;

    public ProfessionsDAO (Database database)
    {
        mDatabase = database;
    }

    public void save (Profession row)
    {

    }

    public void delete (UnsignedLong id)
    {

    }

    public Profession update (Profession row)
    {
        return row;
    }

    public List<Profession> loadAll ()
    {
        return null;
    }
}
