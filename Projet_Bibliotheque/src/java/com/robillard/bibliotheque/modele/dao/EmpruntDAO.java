/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robillard.bibliotheque.modele.dao;
import com.robillard.bibliotheque.modele.classes.Emprunt;
import com.robillard.bibliotheque.modele.classes.Exemplaire;
import com.robillard.bibliotheque.modele.classes.Compte;
import com.robillard.bibliotheque.modele.classes.Edition;
import com.robillard.bibliotheque.modele.classes.Ouvrage;
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

public class EmpruntDAO extends DAO<Emprunt> {
    
    private static final Logger logger = Logger.getLogger("monLogger");
    
    public EmpruntDAO(Connection c)
    {
        super(c);
    }
        
    @Override
    public boolean create(Emprunt emprunt) {
        PreparedStatement stm = null;
        String requete = "INSERT INTO emprunt "
                + "(DATE_DEBUT, DATE_FIN, STATUS, COMPTE_ID, EXEMPLAIRE_ID ) "
                + "VALUES (?, ?, ?, ?, ?)";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, emprunt.getDateDebut());
            stm.setString(2, emprunt.getDateFin());
            stm.setString(3, emprunt.getStatus());
            stm.setString(4, emprunt.getCompte().getNumero());
            stm.setInt(5, emprunt.getExemplaire().getId());
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
        String requete = "SELECT * FROM emprunt" 
                +"INNER JOIN Compte on Compte.NUMERO = emprunt.COMPTE_ID"
                +"INNER JOIN exemplaire ON exemplaire.ID = emprunt.EXEMPLAIRE_ID"
                +"INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID"
                +"INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID"
                +"INNER JOIN auteur ON auteur.ID = ouvrage.AUTEUR_ID"
                +"WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, id);
            resultat = stm.executeQuery();
            if (resultat.next())
            {
                Emprunt emp = new Emprunt();
                emp.setId(resultat.getInt("ID"));
                emp.setDateDebut(resultat.getString("DATE_DEBUT"));
                emp.setDateFin(resultat.getString("DATE_FIN"));
                emp.setStatus(resultat.getString("STATUS"));
                emp.setCompte(new Compte(
                        resultat.getString("NUMERO"),
                        resultat.getString("PRENOM"),
                        resultat.getString("NOM"),
                        resultat.getString("MDP"),
                        resultat.getInt("compte.TYPE")));                
                emp.setExemplaire(new Exemplaire(
                        resultat.getInt("exemplaire.ID"),
                        resultat.getString("EMPLACEMENT"),
                        new Edition(
                                resultat.getInt("edition.ID"),
                                resultat.getInt("NOMBRE_PAGE"),
                                resultat.getString("ISBN"),
                                resultat.getString("DATE_PUBLICATION"),
                                resultat.getString("IMAGE"),
                                resultat.getString("EDITEUR"),
                                new Ouvrage(
                                        resultat.getInt("ouvrage.ID"),
                                        resultat.getString("ouvrage.TITRE"),
                                        resultat.getString("ouvrage.Type"),
                                        new Auteur(
                                                resultat.getString("auteur.ID"),
                                                resultat.getString("auteur.NOM"),
                                                resultat.getString("auteur.PRENOM"))))));
                resultat.close();
                stm.close();
                return emp;
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
    public boolean update(Emprunt emprunt) {
        PreparedStatement stm = null;
        String requete = "UPDATE emprunt SET DATE_DEBUT = ?, DATE_FIN = ?, "
                + "STATUS = ?, COMPTE_ID = ?, EXEMPLAIRE_ID = ? WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, emprunt.getDateDebut());
            stm.setString(2, emprunt.getDateFin());
            stm.setString(3, emprunt.getStatus());
            stm.setString(4, emprunt.getCompte().getNumero());
            stm.setInt(5, emprunt.getExemplaire().getId());
            stm.setInt(6, emprunt.getId());
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
        try 
        {
            stm = cnx.createStatement(); 
            resultat = stm.executeQuery("SELECT * FROM emprunt" 
                +"INNER JOIN Compte on Compte.NUMERO = emprunt.COMPTE_ID"
                +"INNER JOIN exemplaire ON exemplaire.ID = emprunt.EXEMPLAIRE_ID"
                +"INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID"
                +"INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID"
                +"INNER JOIN auteur ON auteur.ID = ouvrage.AUTEUR_ID");
            while (resultat.next())
            {
                    Emprunt emp = new Emprunt();
                    emp.setId(resultat.getInt("ID"));
                    emp.setDateDebut(resultat.getString("DATE_DEBUT"));
                    emp.setDateFin(resultat.getString("DATE_FIN"));
                    emp.setStatus(resultat.getString("STATUS"));
                    emp.setCompte(new Compte(
                            resultat.getString("NUMERO"),
                            resultat.getString("PRENOM"),
                            resultat.getString("NOM"),
                            resultat.getString("MDP"),
                            resultat.getInt("compte.TYPE")
                    ));

                    emp.setExemplaire(new Exemplaire(
                            resultat.getInt("ID"),
                            resultat.getString("EMPLACEMENT"),
                            new Edition(
                                    resultat.getInt("ID"),
                                    resultat.getInt("NOMBRE_PAGE"),
                                    resultat.getString("ISBN"),
                                    resultat.getString("DATE_PUBLICATION"),
                                    resultat.getString("IMAGE"),
                                    resultat.getString("EDITEUR"),
                                    new Ouvrage(
                                            resultat.getInt("ouvrage.ID"),
                                            resultat.getString("ouvrage.TITRE"),
                                            resultat.getString("ouvrage.Type"),
                                            new Auteur(
                                                    resultat.getString("auteur.ID"),
                                                    resultat.getString("auteur.NOM"),
                                                    resultat.getString("auteur.PRENOM"))))));
                    listeEmprunt.add(emp);
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
    
    public String findMaxDateByExemplaire(int id) {
        return this.findMaxDateByExemplaire("" + id);
    }
    
    public String findMaxDateByExemplaire(String id) {
        PreparedStatement stm = null;
        ResultSet resultat = null;
        String date = "";
        try 
        {
            String requete = "SELECT MAX(DATE_FIN) FROM emprunt" 
                +" INNER JOIN Compte on Compte.NUMERO = emprunt.COMPTE_ID"
                +" INNER JOIN exemplaire ON exemplaire.ID = emprunt.EXEMPLAIRE_ID"
                +" INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID"
                +" INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID"
                +" INNER JOIN auteur ON auteur.ID = ouvrage.AUTEUR_ID"
                +" WHERE EXEMPLAIRE_ID = ? ";
            stm = cnx.prepareStatement(requete);
            stm.setString(1, id);
            resultat = stm.executeQuery();
            while (resultat.next())
            {
                date = resultat.getString("MAX(DATE_FIN)");
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
        return date;
    }
}