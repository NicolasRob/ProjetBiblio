/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robillard.bibliotheque.modele.dao;

import com.robillard.bibliotheque.modele.classes.Auteur;
import com.robillard.bibliotheque.modele.classes.Compte;
import com.robillard.bibliotheque.modele.classes.Editeur;
import com.robillard.bibliotheque.modele.classes.Edition;
import com.robillard.bibliotheque.modele.classes.Emprunt;
import com.robillard.bibliotheque.modele.classes.Exemplaire;
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
 * @author c.blais
 */
public class EmpruntDAO extends DAO<Emprunt> {
     private Logger logger = Logger.getLogger("monLogger");
    
    public EmpruntDAO(Connection c)
    {
        super(c);
    }
        
    @Override
    public boolean create(Emprunt emprunt) {
        PreparedStatement stm = null;
        String requete = "INSERT INTO emprunt"
                + "(COMPTE_ID , EXEMPLAIRE_ID, DATE_DEBUT, DATE_FIN)"
                + "VALUES (?, ?, ?, ?)";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, emprunt.getCompte().getNumero());
            stm.setInt(2, emprunt.getExemplaire().getId());
            stm.setString(3, emprunt.getDateDebut());
            stm.setString(4, emprunt.getDateFin());
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
    public boolean delete(Emprunt emprunt) {
        PreparedStatement stm = null;
        String requete = "DELETE FROM emprunt WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(1, emprunt.getId());
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
    public Emprunt read(int id) {
            return this.read(""+id);
    }
        
    @Override
    public Emprunt read(String id) {
        PreparedStatement stm = null;
        ResultSet resultat = null;
        String requete = "SELECT * FROM Emprunt" +
                "INNER JOIN compte ON emprunt.COMPTE_ID = compte.NUMERO" +
                "INNER JOIN exemplaire ON exemplaire.ID = emprunt.EXEMPLAIRE_ID" +
                "INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID" +
                "INNER JOIN editeur ON editeur.ID = edition.EDITEUR_ID" +
                "INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID" +
                "INNER JOIN auteur On ouvrage.AUTEUR_ID = auteur.ID" +
                "WHERE ID = ?" ;
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, id);
            resultat = stm.executeQuery();
            if (resultat.next())
            {
                Emprunt emprunt = new Emprunt();
                Compte c = new Compte();
                Exemplaire exemplaire = new Exemplaire();
                Edition e = new Edition ();
                Editeur editeur = new Editeur();
                Ouvrage o = new Ouvrage();
                Auteur a = new Auteur();
                
                emprunt.setId(resultat.getInt("emprunt.ID"));
                    c.setNumero(resultat.getString("compte.NUMERO"));
                    c.setPrenom(resultat.getString("compte.PRENOM"));
                    c.setNom(resultat.getString("compte.NOM"));
                    c.setMdp(resultat.getString("compte.MDP"));
                    c.setType(resultat.getInt("compte.TYPE"));
                emprunt.setCompte(c);
                    exemplaire.setId(resultat.getInt("exemplaire.ID"));
                    exemplaire.setEmplacement(resultat.getString("exemplaire.EMPLACEMENT"));
                        e.setId(resultat.getInt("edition.ID"));
                        e.setNombrePage(resultat.getInt("edition.NOMBRE_PAGE"));
                        e.setIsbn(resultat.getString("edition.ISBN"));
                        e.setDatePublication(resultat.getString("edition.DATE_PUBLICATION"));
                        e.setImage(resultat.getString("edition.IMAGE"));
                            editeur.setId(resultat.getInt("editeur.ID"));
                            editeur.setNom(resultat.getString("editeur.NOM"));
                        e.setEditeur(editeur);
                            o.setId(resultat.getInt("ouvrage.ID"));
                            o.setTitre(resultat.getString("ouvrage.TITRE"));
                            o.setType(resultat.getString("ouvrage.TYPE"));
                                a.setId(resultat.getInt("auteur.ID"));
                                a.setNom(resultat.getString("auteur.NOM"));
                                a.setPrenom(resultat.getString("auteur.PRENOM"));
                            o.setAuteur(a);
                        e.setOuvrage(o);
                    exemplaire.setEdition(e);
                emprunt.setExemplaire(exemplaire);
                resultat.close();
                stm.close();
                return emprunt;
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
    public boolean update(Emprunt e) {
        PreparedStatement stm = null;
        String requete = "UPDATE emprunt COMPTE_ID= ?, EXEMPLAIRE_ID = ? WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, e.getCompte().getNumero());
            stm.setInt(2, e.getExemplaire().getId());
            stm.setInt(3, e.getId());
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
    public List<Emprunt> findAll() {
        Statement stm = null;
        ResultSet resultat = null;
        List<Emprunt> listeEmprunt = new LinkedList();
                     String requete = "SELECT * FROM Emprunt" +
                "INNER JOIN compte ON emprunt.COMPTE_ID = compte.NUMERO" +
                "INNER JOIN exemplaire ON exemplaire.ID = emprunt.EXEMPLAIRE_ID" +
                "INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID" +
                "INNER JOIN editeur ON editeur.ID = edition.EDITEUR_ID" +
                "INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID" +
                "INNER JOIN auteur On ouvrage.AUTEUR_ID = auteur.ID";
        try 
        {
            resultat = stm.executeQuery(requete);
            if (resultat.next())
            {
                Emprunt emprunt = new Emprunt();
                Compte c = new Compte();
                Exemplaire exemplaire = new Exemplaire();
                Edition e = new Edition ();
                Editeur editeur = new Editeur();
                Ouvrage o = new Ouvrage();
                Auteur a = new Auteur();
                
                emprunt.setId(resultat.getInt("emprunt.ID"));
                    c.setNumero(resultat.getString("compte.NUMERO"));
                    c.setPrenom(resultat.getString("compte.PRENOM"));
                    c.setNom(resultat.getString("compte.NOM"));
                    c.setMdp(resultat.getString("compte.MDP"));
                    c.setType(resultat.getInt("compte.TYPE"));
                emprunt.setCompte(c);
                    exemplaire.setId(resultat.getInt("exemplaire.ID"));
                    exemplaire.setEmplacement(resultat.getString("exemplaire.EMPLACEMENT"));
                        e.setId(resultat.getInt("edition.ID"));
                        e.setNombrePage(resultat.getInt("edition.NOMBRE_PAGE"));
                        e.setIsbn(resultat.getString("edition.ISBN"));
                        e.setDatePublication(resultat.getString("edition.DATE_PUBLICATION"));
                        e.setImage(resultat.getString("edition.IMAGE"));
                            editeur.setId(resultat.getInt("editeur.ID"));
                            editeur.setNom(resultat.getString("editeur.NOM"));
                        e.setEditeur(editeur);
                            o.setId(resultat.getInt("ouvrage.ID"));
                            o.setTitre(resultat.getString("ouvrage.TITRE"));
                            o.setType(resultat.getString("ouvrage.TYPE"));
                                a.setId(resultat.getInt("auteur.ID"));
                                a.setNom(resultat.getString("auteur.NOM"));
                                a.setPrenom(resultat.getString("auteur.PRENOM"));
                            o.setAuteur(a);
                        e.setOuvrage(o);
                    exemplaire.setEdition(e);
                emprunt.setExemplaire(exemplaire);
                resultat.close();
                stm.close();
                    
                listeEmprunt.add(emprunt);
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
        return listeEmprunt;
    }
    
    public List<Emprunt> findAll(String numero) {
        Statement stm = null;
        ResultSet resultat = null;
        List<Emprunt> listeEmprunt = new LinkedList();
                     String requete = "SELECT * FROM Emprunt" +
                "INNER JOIN compte ON emprunt.COMPTE_ID = compte.NUMERO" +
                "INNER JOIN exemplaire ON exemplaire.ID = emprunt.EXEMPLAIRE_ID" +
                "INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID" +
                "INNER JOIN editeur ON editeur.ID = edition.EDITEUR_ID" +
                "INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID" +
                "INNER JOIN auteur On ouvrage.AUTEUR_ID = auteur.ID" +
                "WHERE compte.NUMERO = ?";
        try
        {
            stm = cnx.prepareStatement(requete);
            //je comprend pas l'erreur
            stm.setString(1, numero);
            resultat = stm.executeQuery(requete);
            if (resultat.next())
            {
                Emprunt emprunt = new Emprunt();
                Compte c = new Compte();
                Exemplaire exemplaire = new Exemplaire();
                Edition e = new Edition ();
                Editeur editeur = new Editeur();
                Ouvrage o = new Ouvrage();
                Auteur a = new Auteur();
                
                emprunt.setId(resultat.getInt("emprunt.ID"));
                    c.setNumero(resultat.getString("compte.NUMERO"));
                    c.setPrenom(resultat.getString("compte.PRENOM"));
                    c.setNom(resultat.getString("compte.NOM"));
                    c.setMdp(resultat.getString("compte.MDP"));
                    c.setType(resultat.getInt("compte.TYPE"));
                emprunt.setCompte(c);
                    exemplaire.setId(resultat.getInt("exemplaire.ID"));
                    exemplaire.setEmplacement(resultat.getString("exemplaire.EMPLACEMENT"));
                        e.setId(resultat.getInt("edition.ID"));
                        e.setNombrePage(resultat.getInt("edition.NOMBRE_PAGE"));
                        e.setIsbn(resultat.getString("edition.ISBN"));
                        e.setDatePublication(resultat.getString("edition.DATE_PUBLICATION"));
                        e.setImage(resultat.getString("edition.IMAGE"));
                            editeur.setId(resultat.getInt("editeur.ID"));
                            editeur.setNom(resultat.getString("editeur.NOM"));
                        e.setEditeur(editeur);
                            o.setId(resultat.getInt("ouvrage.ID"));
                            o.setTitre(resultat.getString("ouvrage.TITRE"));
                            o.setType(resultat.getString("ouvrage.TYPE"));
                                a.setId(resultat.getInt("auteur.ID"));
                                a.setNom(resultat.getString("auteur.NOM"));
                                a.setPrenom(resultat.getString("auteur.PRENOM"));
                            o.setAuteur(a);
                        e.setOuvrage(o);
                    exemplaire.setEdition(e);
                emprunt.setExemplaire(exemplaire);
                resultat.close();
                stm.close();
                    
                listeEmprunt.add(emprunt);
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
        return listeEmprunt;
    }
}
