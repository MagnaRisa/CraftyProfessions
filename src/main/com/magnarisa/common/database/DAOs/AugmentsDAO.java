package com.magnarisa.common.database.DAOs;

import com.google.common.primitives.UnsignedLong;
import com.magnarisa.common.database.databaseConn.Database;
import com.magnarisa.common.professions.IAugment;

import java.util.List;

public class AugmentsDAO implements IDaoBase<IAugment>
{
    private Database mDatabase;

    public AugmentsDAO (Database database)
    {
        mDatabase = database;
    }

    public void save (IAugment row)
    {
        // Save the augment here.
    }

    public void delete (UnsignedLong id)
    {
        // Delete the augment based on it's Id
    }

    public IAugment update (IAugment row)
    {
        return row;
    }

    public List<IAugment> loadAll ()
    {
        return null;
    }

}
