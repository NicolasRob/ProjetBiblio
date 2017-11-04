package com.robillard.bibliotheque.modele.dao;

import com.robillard.bibliotheque.modele.classes.Exemplaire;
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

public class ExemplaireDAO extends DAO<Exemplaire>
{

    private static final Logger logger = Logger.getLogger("monLogger");

    public ExemplaireDAO(Connection c)
    {
        super(c);
    }

    @Override
    public boolean create(Exemplaire exemplaire)
    {
        PreparedStatement stm = null;
        String requete = "INSERT INTO exemplaire "
                + "(EMPLACEMENT, EDITION_ID) "
                + "VALUES (?, ?)";
        try
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, exemplaire.getEmplacement());
            stm.setInt(2, exemplaire.getEdition().getId());
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
    public boolean delete(Exemplaire exemplaire)
    {
        PreparedStatement stm = null;
        String requete = "DELETE FROM exemplaire WHERE ID = ?";
        try
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(1, exemplaire.getId());
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
    public Exemplaire read(int id)
    {
        return this.read("" + id);
    }

    @Override
    public Exemplaire read(String id)
    {
        PreparedStatement stm = null;
        ResultSet resultat = null;
        String requete = "SELECT * FROM exemplaire"
                + " INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID"
                + " INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID"
                + " INNER JOIN auteur ON auteur.ID = ouvrage.AUTEUR_ID"
                + " WHERE exemplaire.ID = ?";
        try
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, id);
            resultat = stm.executeQuery();
            if (resultat.next())
            {
                Exemplaire ex = new Exemplaire(
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
                                                resultat.getString("auteur.PRENOM")))));
                resultat.close();
                stm.close();
                return ex;
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
    public boolean update(Exemplaire exemplaire)
    {
        PreparedStatement stm = null;
        String requete = "UPDATE exemplaire SET EMPLACEMENT = ?, EDITION_ID = ?, "
                + "WHERE ID = ?";
        try
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, exemplaire.getEmplacement());
            stm.setInt(2, exemplaire.getEdition().getId());
            stm.setInt(3, exemplaire.getId());
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
    public List<Exemplaire> findAll()
    {
        Statement stm = null;
        ResultSet resultat = null;
        List<Exemplaire> listeExemplaire = new LinkedList();
        try
        {
            stm = cnx.createStatement();
            resultat = stm.executeQuery("SELECT * FROM exemplaire"
                    + "INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID"
                    + "INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID"
                    + "INNER JOIN auteur ON auteur.ID = ouvrage.AUTEUR_ID");
            while (resultat.next())
            {
                Exemplaire ex = new Exemplaire(
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
                                                resultat.getString("auteur.PRENOM")))));
                listeExemplaire.add(ex);
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
        return listeExemplaire;
    }

    public List<Exemplaire> findByEdition(int id)
    {
        return this.findByEdition("" + id);
    }

    //Retourne la liste d'exemplaire pour une édition
    public List<Exemplaire> findByEdition(String id)
    {
        PreparedStatement stm = null;
        ResultSet resultat = null;
        List<Exemplaire> listeExemplaire = new LinkedList();
        try
        {
            String requete = "SELECT * FROM exemplaire"
                    + " INNER JOIN edition ON exemplaire.EDITION_ID = edition.ID"
                    + " INNER JOIN ouvrage ON ouvrage.ID = edition.OUVRAGE_ID"
                    + " INNER JOIN auteur ON auteur.ID = ouvrage.AUTEUR_ID"
                    + " WHERE EDITION_ID = ?";
            stm = cnx.prepareStatement(requete);
            stm.setString(1, id);
            resultat = stm.executeQuery();
            while (resultat.next())
            {
                Exemplaire ex = new Exemplaire(
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
                                                resultat.getString("auteur.PRENOM")))));
                listeExemplaire.add(ex);
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
        return listeExemplaire;
    }
}
