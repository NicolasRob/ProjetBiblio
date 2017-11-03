/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robillard.bibliotheque.modele.dao;

import com.robillard.bibliotheque.modele.classes.Auteur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vengor
 */
public class AuteurDAO extends DAO<Auteur> {
    
    private Logger logger = Logger.getLogger("monLogger");
    
    public AuteurDAO(Connection c)
    {
        super(c);
    }
        
    @Override
    public boolean create(Auteur auteur) {
        PreparedStatement stm = null;
        String requete = "INSERT INTO auteur"
                + "(ID , PRENOM, NOM) "
                + "VALUES (?, ?, ?)";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(1, auteur.getId());
            stm.setString(2, auteur.getPrenom());
            stm.setString(3, auteur.getNom());
            int n = stm.executeUpdate();
            if (n>0)
            {
                    stm.close();
                    return true;
            }
        }
        catch (SQLException exp)
        {
            logger.log(Level.SEVERE, exp.getMessage());
        }
        finally
        {
            if (stm!=null)
            try 
            {
                stm.close();
            } 
            catch (SQLException exp) 
            {
                logger.log(Level.SEVERE, exp.getMessage());
            }			
        }
        return false;
    }
    
    @Override
    public boolean delete(Auteur auteur) {
        PreparedStatement stm = null;
        String requete = "DELETE FROM auteur WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(1, auteur.getId());
            int n = stm.executeUpdate();
            if (n>0)
            {
                    stm.close();
                    return true;
            }
        }
        catch (SQLException exp)
        {
            logger.log(Level.SEVERE, exp.getMessage());
        }
        finally
        {
            if (stm!=null)
            try 
            {
                    stm.close();
            } 
            catch (SQLException exp) 
            {
                logger.log(Level.SEVERE, exp.getMessage());
            }			
        }
        return false;
    }
    
    @Override
    public Auteur read(int id) {
            return this.read(""+id);
    }
        
    @Override
    public Auteur read(String id) {
        PreparedStatement stm = null;
        ResultSet resultat = null;
        String requete = "SELECT * FROM auteur WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, id);
            resultat = stm.executeQuery();
            if (resultat.next())
            {
                Auteur a = new Auteur();
                a.setId(resultat.getInt("ID"));
                a.setPrenom(resultat.getString("PRENOM"));
                a.setNom(resultat.getString("NOM"));
                resultat.close();
                stm.close();
                return a;
            }
        }
        catch (SQLException exp)
        {
            logger.log(Level.SEVERE, exp.getMessage());
        }
        finally
        {
            if (stm!=null)
            try 
            {
                    resultat.close();
                    stm.close();
            } 
            catch (SQLException exp) 
            {
                logger.log(Level.SEVERE, exp.getMessage());
            }			
        }
        return null;
    }
    
    @Override
    public boolean update(Auteur auteur) {
        PreparedStatement stm = null;
        String requete = "UPDATE auteur SET PRENOM = ?, NOM = ? WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, auteur.getPrenom());
            stm.setString(2, auteur.getNom());
            stm.setInt(3, auteur.getId());
            int n = stm.executeUpdate();
            if (n>0)
            {
                    stm.close();
                    return true;
            }
        }
        catch (SQLException exp)
        {
            logger.log(Level.SEVERE, exp.getMessage());
        }
        finally
        {
            if (stm!=null)
            try 
            {
                stm.close();
            } 
            catch (SQLException exp) 
            {
                logger.log(Level.SEVERE, exp.getMessage());
            }			
        }
        return false;
    }
        
    @Override
    public List<Auteur> findAll() {
        Statement stm = null;
        ResultSet resultat = null;
        List<Auteur> listeAuteur = new LinkedList();
        try 
        {
            stm = cnx.createStatement(); 
            resultat = stm.executeQuery("SELECT * FROM auteur");
            while (resultat.next())
            {
                    Auteur a = new Auteur();
                    a.setId(resultat.getInt("ID"));
                    a.setPrenom(resultat.getString("PRENOM"));
                    a.setNom(resultat.getString("NOM"));
                    listeAuteur.add(a);
            }
        }
        catch (SQLException exp)
        {
            logger.log(Level.SEVERE, exp.getMessage());
        }
        finally
        {
            if (stm!=null)
            try 
            {
                resultat.close();
                stm.close();
            } 
            catch (SQLException exp) 
            {
                logger.log(Level.SEVERE, exp.getMessage());
            }			
        }
        return listeAuteur;
    }
}
