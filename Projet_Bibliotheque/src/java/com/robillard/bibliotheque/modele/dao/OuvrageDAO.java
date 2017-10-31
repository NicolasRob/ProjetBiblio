/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robillard.bibliotheque.modele.dao;

import com.robillard.bibliotheque.modele.classes.Auteur;
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
 * @author Vengor
 */
public class OuvrageDAO extends DAO<Ouvrage>{
    
    private Logger logger = Logger.getLogger("monLogger");
        
    public OuvrageDAO(Connection c)
    {
        super(c);
    }
        
    @Override
    public boolean create(Ouvrage ouvrage) {
        PreparedStatement stm = null;
        String requete = "INSERT INTO ouvrage "
                + "(TITRE, TYPE, AUTEUR_ID) "
                + "VALUES (?, ?, ?)";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, ouvrage.getTitre());
            stm.setString(2, ouvrage.getType());
            stm.setString(3, ouvrage.getAuteur().getId());
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
    public boolean delete(Ouvrage ouvrage) {
        PreparedStatement stm = null;
        String requete = "DELETE FROM ouvrage WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(1, ouvrage.getId());
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
    public Ouvrage read(int id) {
            return this.read(""+id);
    }
        
    @Override
    public Ouvrage read(String id) {
        PreparedStatement stm = null;
        ResultSet resultat = null;
        String requete = "SELECT * FROM ouvrage"
                + " INNER JOIN auteur ON ouvrage.AUTEUR_ID = auteur.ID"
                + " WHERE ouvrage.ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, id);
            resultat = stm.executeQuery();
            if (resultat.next())
            {
                Ouvrage o = new Ouvrage();
                o.setId(resultat.getInt("ouvrage.ID"));
                o.setTitre(resultat.getString("TITRE"));
                o.setType(resultat.getString("TYPE"));
                Auteur a = new Auteur(
                        resultat.getString("auteur.ID"),
                        resultat.getString("PRENOM"),
                        resultat.getString("NOM")
                );
                o.setAuteur(a);
                resultat.close();
                stm.close();
                return o;
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
    public boolean update(Ouvrage ouvrage) {
        PreparedStatement stm = null;
        String requete = "UPDATE ouvrage SET TITRE = ?, TYPE = ?, AUTEUR_ID = ? WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, ouvrage.getTitre());
            stm.setString(2, ouvrage.getType());
            stm.setString(3, ouvrage.getAuteur().getId());
            stm.setInt(4, ouvrage.getId());
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
    public List<Ouvrage> findAll() {
        Statement stm = null;
        ResultSet resultat = null;
        List<Ouvrage> listeOuvrage = new LinkedList();
        try 
        {
            stm = cnx.createStatement(); 
            resultat = stm.executeQuery("SELECT * FROM ouvrage"
                    + " INNER JOIN auteur ON ouvrage.AUTEUR_ID = auteur.ID");
            while (resultat.next())
            {
                    Ouvrage o = new Ouvrage();
                    o.setId(resultat.getInt("ID"));
                    o.setTitre(resultat.getString("TITRE"));
                    o.setType(resultat.getString("TYPE"));
                    Auteur a = new Auteur(
                        resultat.getString("auteur.ID"),
                        resultat.getString("PRENOM"),
                        resultat.getString("NOM")
                    );
                    o.setAuteur(a);
                    listeOuvrage.add(o);
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
        return listeOuvrage;
    }
    
    public List<Ouvrage> findAll(String filtre, String recherche) {
        Statement stm = null;
        ResultSet resultat = null;
        List<Ouvrage> listeOuvrage = new LinkedList();
        String colonne;
        String requete = "";
        switch (filtre)
        {
            case "titre":
                colonne = "TITRE";
                break;
            case "auteur":
                colonne = "NOM";
                break;
            case "categorie":
                colonne = "TYPE";
                break;
            default:
                colonne = "";
        }
        if (!"".equals(colonne) && !"".equals(recherche.trim()))
        {
            try 
            {
                stm = cnx.createStatement(); 
                resultat = stm.executeQuery("SELECT * FROM ouvrage"
                        + " INNER JOIN auteur ON ouvrage.AUTEUR_ID = auteur.ID"
                        + " WHERE " + colonne + " LIKE " + "'%" + recherche + "%'");
                while (resultat.next())
                {
                        Ouvrage o = new Ouvrage();
                        o.setId(resultat.getInt("ID"));
                        o.setTitre(resultat.getString("TITRE"));
                        o.setType(resultat.getString("TYPE"));
                        Auteur a = new Auteur(
                            resultat.getString("auteur.ID"),
                            resultat.getString("PRENOM"),
                            resultat.getString("NOM")
                        );
                        o.setAuteur(a);
                        listeOuvrage.add(o);
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
        }
        return listeOuvrage;
    }
}
