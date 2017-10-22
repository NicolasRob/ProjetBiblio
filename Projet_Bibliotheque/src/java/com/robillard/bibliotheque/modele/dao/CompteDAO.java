/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robillard.bibliotheque.modele.dao;

import com.robillard.bibliotheque.modele.classes.Compte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vengor
 */
public class CompteDAO extends DAO<Compte>{
    
    public CompteDAO(Connection c)
    {
        super(c);
    }
        
    @Override
    public boolean create(Compte compte) {
        PreparedStatement stm = null;
        String requete = "INSERT INTO compte "
                + "('NUMERO' , 'PRENOM', 'NOM, 'MDP', 'TYPE') "
                + "VALUES (?, ?, ?, ?, ?)";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, compte.getNumero());
            stm.setString(2, compte.getPrenom());
            stm.setString(3, compte.getNom());
            stm.setString(4, compte.getMdp());
            stm.setInt(5, compte.getType());
            int n = stm.executeUpdate();
            if (n>0)
            {
                    stm.close();
                    return true;
            }
        }
        catch (SQLException exp)
        {
            return false;
        }
        finally
        {
            if (stm!=null)
            try {
                    stm.close();
            } catch (SQLException e) {
                    e.printStackTrace();
            }			
        }
        return false;
    }
    
    @Override
    public boolean delete(Compte compte) {
        PreparedStatement stm = null;
        String requete = "DELETE FROM compte WHERE NUMERO = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, compte.getNumero());
            int n = stm.executeUpdate();
            if (n>0)
            {
                    stm.close();
                    return true;
            }
        }
        catch (SQLException exp)
        {
            return false;
        }
        finally
        {
            if (stm!=null)
            try {
                    stm.close();
            } catch (SQLException e) {
                    e.printStackTrace();
            }			
        }
        return false;
    }
    
    @Override
    public Compte read(int numero) {
            return this.read(""+numero);
    }
        
    @Override
    public Compte read(String numero) {
        PreparedStatement stm = null;
        ResultSet resultat = null;
        String requete = "SELECT * FROM compte WHERE NUMERO = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, numero);
            resultat = stm.executeQuery();
            if (resultat.next())
            {
                Compte c = new Compte();
                c.setNumero(resultat.getString("NUMERO"));
                c.setPrenom(resultat.getString("PRENOM"));
                c.setNom(resultat.getString("NOM"));
                c.setMdp(resultat.getString("MDP"));
                c.setType(resultat.getInt("TYPE"));
                resultat.close();
                stm.close();
                return c;
            }
        }
        catch (SQLException exp)
        {
        }
        finally
        {
            if (stm!=null)
            try {
                    resultat.close();
                    stm.close();
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }			
        }
        return null;
    }
    
    @Override
    public boolean update(Compte compte) {
        PreparedStatement stm = null;
        String requete = "UPDATE compte SET NUMERO = ?, PRENOM = ?, NOM = ?, "
                + "MDP = ?, TYPE = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, compte.getNumero());
            stm.setString(2, compte.getPrenom());
            stm.setString(3, compte.getNom());
            stm.setString(4, compte.getMdp());
            stm.setInt(5, compte.getType());
            int n = stm.executeUpdate();
            if (n>0)
            {
                    stm.close();
                    return true;
            }
        }
        catch (SQLException exp)
        {
            
        }
        finally
        {
            if (stm!=null)
            try {
                    stm.close();
            } catch (SQLException e) {
                    e.printStackTrace();
            }			
        }
        return false;
    }
        
    @Override
    public List<Compte> findAll() {
        List<Compte> listeCompte = new LinkedList();
        try 
        {
            Statement stm = cnx.createStatement(); 
            ResultSet resultat = stm.executeQuery("SELECT * FROM compte");
            while (resultat.next())
            {
                    Compte c = new Compte();
                    c.setNumero(resultat.getString("NUMERO"));
                    c.setPrenom(resultat.getString("PRENOM"));
                    c.setNom(resultat.getString("NOM"));
                    c.setMdp(resultat.getString("MDP"));
                    c.setType(resultat.getInt("TYPE"));
                    listeCompte.add(c);
            }
            resultat.close();
            stm.close();
        }
        catch (SQLException exp)
        {
        }
        return listeCompte;
    }
}
