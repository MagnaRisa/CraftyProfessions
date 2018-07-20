package com.creedfreak.common.database.DAOs;

import com.google.common.primitives.UnsignedLong;
import com.creedfreak.common.database.databaseConn.Database;
import com.creedfreak.common.professions.IProfession;

import java.util.List;

public class ProfessionsDAO implements IDaoBase<IProfession>
{
    private Database mDatabase;

    public ProfessionsDAO (Database database)
    {
        mDatabase = database;
    }

    public void save (IProfession row)
    {

    }

    public void delete (UnsignedLong id)
    {

    }

    public IProfession update (IProfession row)
    {
        return row;
    }

    public List<IProfession> loadAll ()
    {
        return null;
    }
}
