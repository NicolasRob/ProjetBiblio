/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robillard.bibliotheque.modele.dao;
import com.robillard.bibliotheque.modele.classes.Reservation;
import com.robillard.bibliotheque.modele.classes.Exemplaire;
import com.robillard.bibliotheque.modele.classes.Compte;
import com.robillard.bibliotheque.modele.classes.Edition;
import com.robillard.bibliotheque.modele.classes.Editeur;
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

public class ReservationDAO extends DAO<Reservation> {
    
    private static final Logger logger = Logger.getLogger("monLogger");
    
    public ReservationDAO(Connection c)
    {
        super(c);
    }
        
    @Override
    public boolean create(Reservation reservation) {
        PreparedStatement stm = null;
        String requete = "INSERT INTO reservation "
                + "(ID , DATE_DEBUT, DATE_FIN, COMPTE_ID,EXEMPLAIRE_ID ) "
                + "VALUES (?, ?, ?, ?, ?)";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(1, reservation.getId());
            stm.setString(2, reservation.getDateDebut());
            stm.setString(3, reservation.getDateFin());
            stm.setString(4, reservation.getCompte().getNumero());
            stm.setInt(5, reservation.getExemplaire().getId());
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
    public boolean delete(Reservation reservation) {
        PreparedStatement stm = null;
        String requete = "DELETE FROM reservation WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(1, reservation.getId());
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
    public Reservation read(int numero) {
            return this.read(""+numero);
    }
        
    @Override
    public Reservation read(String ID) {
        PreparedStatement stm = null;
        ResultSet resultat = null;
        String requete = "SELECT * FROM reservation" 
                +"INNER JOIN Compte on Compte.NUMERO = reservation.COMPTE_ID"
                +"INNER JOIN exemplaire ON exemplaire.ID = reservation.EXEMPLAIRE_ID"
                +"INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID"
                +"INNER JOIN editeur ON editeur.ID = edition.EDITEUR_ID"
                +"INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID"
                +"INNER JOIN auteur ON auteur.ID = ouvrage.AUTEUR_ID"
                +"WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, ID);
            resultat = stm.executeQuery();
            if (resultat.next())
            {
                Reservation r = new Reservation();
                r.setId(resultat.getInt("ID"));
                r.setDateFin(resultat.getString("DATE_FIN"));
                r.setDateDebut(resultat.getString("NOM"));
                r.setCompte(new Compte(
                        resultat.getString("NUMERO"),
                        resultat.getString("PRENOM"),
                        resultat.getString("NOM"),
                        resultat.getString("MDP"),
                        resultat.getInt("TYPE")));                
                r.setExemplaire(new Exemplaire(
                        resultat.getInt("ID"),
                        resultat.getString("EMPLACEMENT"),
                        new Edition(
                                resultat.getInt("ID"),
                                resultat.getInt("NOMBRE_PAGE"),
                                resultat.getString("ISBN"),
                                resultat.getString("DATE_PUBLICATION"),
                                resultat.getString("IMAGE"),
                                new Editeur(
                                        resultat.getInt("editeur.ID"),
                                        resultat.getString("editeur.NOM")),
                                new Ouvrage(
                                        resultat.getInt("ouvrage.ID"),
                                        resultat.getString("ouvrage.TITRE"),
                                        resultat.getString("ouvrage.Type"),
                                        new Auteur(
                                                resultat.getInt("auteur.ID"),
                                                resultat.getString("auteur.NOM"),
                                                resultat.getString("auteur.PRENOM"))))));
                resultat.close();
                stm.close();
                return r;
            }
        }
        catch (SQLException exp)
        {
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
    public boolean update(Reservation reservation) {
        PreparedStatement stm = null;
        String requete = "UPDATE reservation SET ID = ?, DATE_DEBUT = ?, "
                + "DATE_FIN = ?, COMPTE_ID = ?, EXEMPLAIRE_ID = ? WHERE ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(1, reservation.getId());
            stm.setString(2, reservation.getDateDebut());
            stm.setString(3, reservation.getDateFin());
            stm.setString(4, reservation.getCompte().getNumero());
            stm.setInt(5, reservation.getExemplaire().getId());
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
    public List<Reservation> findAll() {
        Statement stm = null;
        ResultSet resultat = null;
        List<Reservation> listeReservation = new LinkedList();
        try 
        {
            stm = cnx.createStatement(); 
            resultat = stm.executeQuery("SELECT * FROM reservation" 
                +"INNER JOIN Compte on Compte.NUMERO = reservation.COMPTE_ID"
                +"INNER JOIN exemplaire ON exemplaire.ID = reservation.EXEMPLAIRE_ID"
                +"INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID"
                +"INNER JOIN editeur ON editeur.ID = edition.EDITEUR_ID"
                +"INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID"
                +"INNER JOIN auteur ON auteur.ID = ouvrage.AUTEUR_ID"
                +"WHERE ID = ?");
            while (resultat.next())
            {
                    Reservation r = new Reservation();
                    r.setId(resultat.getInt("ID"));
                    r.setDateFin(resultat.getString("DATE_FIN"));
                    r.setDateDebut(resultat.getString("NOM"));

                    r.setCompte(new Compte(
                            resultat.getString("NUMERO"),
                            resultat.getString("PRENOM"),
                            resultat.getString("NOM"),
                            resultat.getString("MDP"),
                            resultat.getInt("TYPE")
                    ));

                    r.setExemplaire(new Exemplaire(
                            resultat.getInt("ID"),
                            resultat.getString("EMPLACEMENT"),
                            new Edition(
                                    resultat.getInt("ID"),
                                    resultat.getInt("NOMBRE_PAGE"),
                                    resultat.getString("ISBN"),
                                    resultat.getString("DATE_PUBLICATION"),
                                    resultat.getString("IMAGE"),
                                    new Editeur(
                                            resultat.getInt("editeur.ID"),
                                            resultat.getString("editeur.NOM")),
                                    new Ouvrage(
                                            resultat.getInt("ouvrage.ID"),
                                            resultat.getString("ouvrage.TITRE"),
                                            resultat.getString("ouvrage.Type"),
                                            new Auteur(
                                                    resultat.getInt("auteur.ID"),
                                                    resultat.getString("auteur.NOM"),
                                                    resultat.getString("auteur.PRENOM"))))));
                    listeReservation.add(r);
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
        return listeReservation;
    }
}