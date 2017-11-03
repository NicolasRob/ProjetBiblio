/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robillard.bibliotheque.modele.dao;


import com.robillard.bibliotheque.modele.classes.Auteur;
import com.robillard.bibliotheque.modele.classes.Edition;
import com.robillard.bibliotheque.modele.classes.Ouvrage;
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

public class EditionDAO extends DAO<Edition>{
    
    private Logger logger = Logger.getLogger("monLogger");
        
    public EditionDAO(Connection c)
    {
        super(c);
    }
        
    @Override
    public boolean create(Edition edition) {
        PreparedStatement stm = null;
        String requete = "INSERT INTO edition "
                + "(NOMBRE_PAGE, ISBN, DATE_PUBLICATION, IMAGE, EDITEUR, OUVRAGE_ID) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(1, edition.getNombrePage());
            stm.setString(2, edition.getIsbn());
            stm.setString(3, edition.getDatePublication());
            stm.setString(4, edition.getImage());
            stm.setString(5, edition.getEditeur());
            stm.setInt(6, edition.getOuvrage().getId());
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
    public boolean delete(Edition edition) {
        PreparedStatement stm = null;
        String requete = "DELETE FROM edition WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(1, edition.getId());
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
    public Edition read(int id) {
            return this.read(""+id);
    }
        
    @Override
    public Edition read(String id) {
        PreparedStatement stm = null;
        ResultSet resultat = null;
        String requete = "SELECT * FROM edition"
                + " INNER JOIN ouvrage ON edition.OUVRAGE_ID = ouvrage.ID"
                + " INNER JOIN auteur ON ouvrage.AUTEUR_ID = auteur.ID"
                + " WHERE edition.ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, id);
            resultat = stm.executeQuery();
            if (resultat.next())
            {
                Edition e = new Edition();
                e.setId(resultat.getInt("edition.ID"));
                e.setNombrePage(resultat.getInt("NOMBRE_PAGE"));
                e.setIsbn(resultat.getString("ISBN"));
                e.setDatePublication(resultat.getString("DATE_PUBLICATION"));
                e.setImage(resultat.getString("IMAGE"));
                e.setEditeur(resultat.getString("EDITEUR"));
                Ouvrage o = new Ouvrage(
                        resultat.getInt("ouvrage.ID"),
                        resultat.getString("TYPE"),
                        resultat.getString("TITRE"),
                        new Auteur(
                            resultat.getString("ID"),
                            resultat.getString("PRENOM"),
                            resultat.getString("NOM")
                        )
                );
                e.setOuvrage(o);
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
    public boolean update(Edition edition) {
        PreparedStatement stm = null;
        String requete = "UPDATE edition SET NOMBRE_PAGE = ?, ISBN = ?, "
                + "DATE_PUBLICATION = ?, IMAGE = ?, EDITEUR = ?, OUVRAGE_ID = ? WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(1, edition.getNombrePage());
            stm.setString(2, edition.getIsbn());
            stm.setString(3, edition.getDatePublication());
            stm.setString(4, edition.getImage());
            stm.setString(5, edition.getEditeur());
            stm.setInt(6, edition.getOuvrage().getId());
            stm.setInt(7, edition.getId());
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
    public List<Edition> findAll() {
        Statement stm = null;
        ResultSet resultat = null;
        List<Edition> listeEdition = new LinkedList();
        try 
        {
            stm = cnx.createStatement(); 
            resultat = stm.executeQuery("SELECT * FROM edition"
                    + " INNER JOIN ouvrage ON edition.OUVRAGE_ID = ouvrage.ID"
                    + " INNER JOIN auteur ON ouvrage.AUTEUR_ID = auteur.ID");
            while (resultat.next())
            {
                    Edition e = new Edition();
                    e.setId(resultat.getInt("edition.ID"));
                    e.setNombrePage(resultat.getInt("NOMBRE_PAGE"));
                    e.setIsbn(resultat.getString("ISBN"));
                    e.setDatePublication(resultat.getString("DATE_PUBLICATION"));
                    e.setImage(resultat.getString("IMAGE"));
                    e.setEditeur(resultat.getString("EDITEUR"));
                    Ouvrage o = new Ouvrage(
                            resultat.getInt("ouvrage.ID"),
                            resultat.getString("TYPE"),
                            resultat.getString("TITRE"),
                            new Auteur(
                                resultat.getString("ID"),
                                resultat.getString("PRENOM"),
                                resultat.getString("NOM")
                            )
                    );
                    e.setOuvrage(o);
                    listeEdition.add(e);
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
        return listeEdition;
    }
}
