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
import javafx.util.Pair;

public class EmpruntDAO extends DAO<Emprunt>
{

    private static final Logger logger = Logger.getLogger("monLogger");

    public EmpruntDAO(Connection c)
    {
        super(c);
    }

    @Override
    public boolean create(Emprunt emprunt)
    {
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
            if (n > 0)
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
            if (stm != null)
            {
                try
                {
                    stm.close();
                }
                catch (SQLException exp)
                {
                    logger.log(Level.SEVERE, exp.getMessage());
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(Emprunt emprunt)
    {
        PreparedStatement stm = null;
        String requete = "DELETE FROM emprunt WHERE ID = ?";
        try
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(1, emprunt.getId());
            int n = stm.executeUpdate();
            if (n > 0)
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
            if (stm != null)
            {
                try
                {
                    stm.close();
                }
                catch (SQLException exp)
                {
                    logger.log(Level.SEVERE, exp.getMessage());
                }
            }
        }
        return false;
    }

    @Override
    public Emprunt read(int id)
    {
        return this.read("" + id);
    }

    @Override
    public Emprunt read(String id)
    {
        PreparedStatement stm = null;
        ResultSet resultat = null;
        String requete = "SELECT * FROM emprunt"
                + "INNER JOIN Compte on Compte.NUMERO = emprunt.COMPTE_ID"
                + "INNER JOIN exemplaire ON exemplaire.ID = emprunt.EXEMPLAIRE_ID"
                + "INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID"
                + "INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID"
                + "INNER JOIN auteur ON auteur.ID = ouvrage.AUTEUR_ID"
                + "WHERE ID = ?";
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
            if (stm != null)
            {
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
        return null;
    }

    @Override
    public boolean update(Emprunt emprunt)
    {
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
            if (n > 0)
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
            if (stm != null)
            {
                try
                {
                    stm.close();
                }
                catch (SQLException exp)
                {
                    logger.log(Level.SEVERE, exp.getMessage());
                }
            }
        }
        return false;
    }

    @Override
    public List<Emprunt> findAll()
    {
        Statement stm = null;
        ResultSet resultat = null;
        List<Emprunt> listeEmprunt = new LinkedList();
        try
        {
            stm = cnx.createStatement();
            resultat = stm.executeQuery("SELECT * FROM emprunt"
                    + "INNER JOIN Compte on Compte.NUMERO = emprunt.COMPTE_ID"
                    + "INNER JOIN exemplaire ON exemplaire.ID = emprunt.EXEMPLAIRE_ID"
                    + "INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID"
                    + "INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID"
                    + "INNER JOIN auteur ON auteur.ID = ouvrage.AUTEUR_ID");
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
            if (stm != null)
            {
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
        return listeEmprunt;
    }
    
    public List<Emprunt> findByCompte(String id)
    {
        PreparedStatement stm = null;
        ResultSet resultat = null;
        List<Emprunt> listeEmprunt = new LinkedList();
        try
        {
            String requete ="SELECT * FROM emprunt "
                    + "INNER JOIN compte on compte.NUMERO = emprunt.COMPTE_ID "
                    + "INNER JOIN exemplaire ON exemplaire.ID = emprunt.EXEMPLAIRE_ID "
                    + "INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID "
                    + "INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID "
                    + "INNER JOIN auteur ON auteur.ID = ouvrage.AUTEUR_ID "
                    + "WHERE emprunt.COMPTE_ID = ? ";
            stm = cnx.prepareStatement(requete);
            stm.setString(1,id);
            resultat = stm.executeQuery();
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
            if (stm != null)
            {
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
        return listeEmprunt;
    }

    public String findMaxDateByExemplaire(int id)
    {
        return this.findMaxDateByExemplaire("" + id);
    }

    //Retourne une String qui représente la date jusqu'à laquelle un exemplaire
    //est emprunté ou reservé
    public String findMaxDateByExemplaire(String id)
    {
        PreparedStatement stm = null;
        ResultSet resultat = null;
        String date = "";
        try
        {
            String requete = "SELECT MAX(DATE_FIN) FROM emprunt"
                    + " INNER JOIN Compte on Compte.NUMERO = emprunt.COMPTE_ID"
                    + " INNER JOIN exemplaire ON exemplaire.ID = emprunt.EXEMPLAIRE_ID"
                    + " INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID"
                    + " INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID"
                    + " INNER JOIN auteur ON auteur.ID = ouvrage.AUTEUR_ID"
                    + " WHERE EXEMPLAIRE_ID = ? ";
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
            if (stm != null)
            {
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
        return date;
    }

    public Emprunt findCurrentReservationForEdition(int id, String numero)
    {
        return this.findCurrentReservationForEdition("" + id, numero);
    }

    //Retourne un objet Emprunt qui représente un emprunt ou une réservation
    //active sur un exemplaire par un compte. Si le compte n'a pas d'emprunt
    //ou de réservation active sur l'exemplaire, l'objet aura la value null
    public Emprunt findCurrentReservationForEdition(String id, String numero)
    {
        PreparedStatement stm = null;
        ResultSet resultat = null;
        Emprunt emprunt = null;
        try
        {
            String requete = "SELECT * FROM emprunt"
                    + " INNER JOIN Compte on Compte.NUMERO = emprunt.COMPTE_ID"
                    + " INNER JOIN exemplaire ON exemplaire.ID = emprunt.EXEMPLAIRE_ID"
                    + " INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID"
                    + " INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID"
                    + " INNER JOIN auteur ON auteur.ID = ouvrage.AUTEUR_ID"
                    + " WHERE edition.ID = ? AND COMPTE_ID = ? AND DATE_FIN >= CURDATE()"
                    + " ORDER BY DATE_FIN"
                    + " LIMIT 1";
            stm = cnx.prepareStatement(requete);
            stm.setString(1, id);
            stm.setString(2, numero);
            resultat = stm.executeQuery();
            while (resultat.next())
            {
                emprunt = new Emprunt();
                emprunt.setId(resultat.getInt("ID"));
                emprunt.setDateDebut(resultat.getString("DATE_DEBUT"));
                emprunt.setDateFin(resultat.getString("DATE_FIN"));
                emprunt.setStatus(resultat.getString("STATUS"));
                emprunt.setCompte(new Compte(
                        resultat.getString("NUMERO"),
                        resultat.getString("PRENOM"),
                        resultat.getString("NOM"),
                        resultat.getString("MDP"),
                        resultat.getInt("compte.TYPE")
                ));

                emprunt.setExemplaire(new Exemplaire(
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
            if (stm != null)
            {
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
        return emprunt;
    }

    public Pair<String, String> findEarliestAvailableExemplaireForEdition(int id)
    {
        return this.findEarliestAvailableExemplaireForEdition("" + id);
    }

    //Retourne une paire de String qui contient le id de l'exemplaire d'une édition qui sera
    //disponible le plus tôt pour un emprunt, ainsi que la date à laquelle il sera disponible
    //Si l'édition n'a pas d'exemplaire, la pair sera null
    //Si un exemplaire de l'édition n'a pas d'emprunt ou de réservation, la date
    //aura la valeur de la date courante
    public Pair<String, String> findEarliestAvailableExemplaireForEdition(String id)
    {
        PreparedStatement stm = null;
        ResultSet resultat = null;
        Pair<String, String> exemplaireDate = null;
        try
        {
            String requete = "SELECT ID, MIN_DATE FROM "
                    + "(SELECT exemplaire.ID, IFNULL(MAX(DATE_FIN), CURDATE()) AS MIN_DATE "
                    + "FROM emprunt "
                    + "INNER JOIN Compte on Compte.NUMERO = emprunt.COMPTE_ID "
                    + "RIGHT JOIN exemplaire ON exemplaire.ID = emprunt.EXEMPLAIRE_ID "
                    + "INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID "
                    + "INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID "
                    + "INNER JOIN auteur ON auteur.ID = ouvrage.AUTEUR_ID "
                    + "WHERE EDITION_ID = ? "
                    + "GROUP BY exemplaire.ID "
                    + "ORDER BY MIN_DATE) AS liste "
                    + "LIMIT 1";
            stm = cnx.prepareStatement(requete);
            stm.setString(1, id);
            resultat = stm.executeQuery();
            while (resultat.next())
            {
                if (resultat.getString("ID") != null)
                {
                    exemplaireDate = new Pair(resultat.getString("ID"), resultat.getString("MIN_DATE"));
                }
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
            if (stm != null)
            {
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
        return exemplaireDate;
    }

}