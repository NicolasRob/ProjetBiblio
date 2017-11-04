package com.robillard.bibliotheque.modele.dao;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T>
{

    protected Connection cnx;
    
    public abstract boolean create(T x);
    public abstract T read(int id);
    public abstract T read(String id);
    public abstract boolean update(T x);
    public abstract boolean delete(T x);
    public abstract List<T> findAll();

    public DAO(Connection cnx)
    {
        this.cnx = cnx;
    }

    public Connection getCnx()
    {
        return cnx;
    }

    public void setCnx(Connection cnx)
    {
        this.cnx = cnx;
    }
}
