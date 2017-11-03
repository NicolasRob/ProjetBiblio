/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robillard.bibliotheque.modele.dao;

import com.robillard.bibliotheque.modele.classes.Editeur;
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
 * @author c.blais
 */
public class EditeurDAO extends DAO<Editeur> {
    
    private Logger logger = Logger.getLogger("monLogger");
    
    public EditeurDAO(Connection c)
    {
        super(c);
    }
        
    @Override
    public boolean create(Editeur editeur) {
        PreparedStatement stm = null;
        String requete = "INSERT INTO editeur"
                + "(NOM)"
                + "VALUES (?)";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, editeur.getNom());
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
    public boolean delete(Editeur editeur) {
        PreparedStatement stm = null;
        String requete = "DELETE FROM editeur WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(1, editeur.getId());
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
    public Editeur read(int id) {
            return this.read(""+id);
    }
        
    @Override
    public Editeur read(String id) {
        PreparedStatement stm = null;
        ResultSet resultat = null;
        String requete = "SELECT * FROM editeur WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, id);
            resultat = stm.executeQuery();
            if (resultat.next())
            {
                Editeur e = new Editeur ();
                e.setId(resultat.getInt("ID"));
                e.setNom(resultat.getString("NOM"));
                resultat.close();
                stm.close();
                return e;
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
    public boolean update(Editeur editeur) {
        PreparedStatement stm = null;
        String requete = "UPDATE editeur NOM = ?, "
                + " WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(2, editeur.getId());
            stm.setString(1, editeur.getNom());
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
    public List<Editeur> findAll() {
        Statement stm = null;
        ResultSet resultat = null;
        List<Editeur> listeEditeur = new LinkedList();
        try 
        {
            stm = cnx.createStatement(); 
            resultat = stm.executeQuery("SELECT * FROM editeur");
            while (resultat.next())
            {
                    Editeur e = new Editeur ();
                    e.setId(resultat.getInt("ID"));
                    e.setNom(resultat.getString("NOM"));
                    listeEditeur.add(e);
            }
            resultat.close();
            stm.close();
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
        return listeEditeur;
    }
}
