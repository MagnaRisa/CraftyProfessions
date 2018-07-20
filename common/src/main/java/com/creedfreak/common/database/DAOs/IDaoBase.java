package com.creedfreak.common.database.DAOs;

import com.google.common.primitives.UnsignedLong;

import java.util.List;

public interface IDaoBase<T>
{
    public void save (T row);

    public void delete (UnsignedLong id);

    public T update (T row);

    public List<T> loadAll ();
}
