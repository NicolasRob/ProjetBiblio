
package com.robillard.bibliotheque.modele.dao;
import com.robillard.bibliotheque.modele.classes.Compte;
import com.robillard.bibliotheque.modele.classes.Suggestion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuggestionDAO extends DAO<Suggestion> {
    private Logger logger = Logger.getLogger("monLogger");
    
    public SuggestionDAO(Connection c)
    {
        super(c);
    }
        
    @Override
    public boolean create(Suggestion Suggestion) {
        PreparedStatement stm = null;
        String requete = "INSERT INTO Suggestion "
                + "(ID, TITRE, AUTEUR, MESSAGE, COMPTE) "
                + "VALUES (?, ?, ?, ?, ?)";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(1, Suggestion.getId());
            stm.setString(2, Suggestion.getTitre());
            stm.setString(3, Suggestion.getAuteur());
            stm.setString(4, Suggestion.getMessage());
            stm.setObject(5, Suggestion.getCompte());
            /*
            stm.setString(1, Suggestion.getNumero());
            stm.setString(2, Suggestion.getPrenom());
            stm.setString(3, Suggestion.getNom());
            stm.setString(4, Suggestion.getMdp());
            stm.setInt(5, Suggestion.getType());
            */
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
    public boolean delete(Suggestion Suggestion) {
        PreparedStatement stm = null;
        String requete = "DELETE FROM Suggestion WHERE NUMERO = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(1, Suggestion.getId());
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
    public Suggestion read(int id) {
            return this.read(""+id);
    }
        
    @Override
    public Suggestion read(String id) {
        PreparedStatement stm = null;
        ResultSet resultat = null;
        String requete = "SELECT * FROM suggestion"
                + " INNER JOIN compte ON compte.NUMERO = suggestion.COMPTE_ID"
                + " WHERE suggestion.ID = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setString(1, id);
            resultat = stm.executeQuery();
            if (resultat.next())
            {
                Suggestion suggestion = new Suggestion();
                suggestion.setId(resultat.getInt("ID"));
                suggestion.setTitre(resultat.getString("TITRE"));
                suggestion.setAuteur(resultat.getString("AUTEUR"));
                suggestion.setMessage(resultat.getString("MESSAGE"));
                Compte compte = new Compte(
                        resultat.getString("NUMERO"),
                        resultat.getString("PRENOM"),
                        resultat.getString("NOM"),
                        resultat.getString("MDP"),
                        resultat.getInt("TYPE"));                        
                suggestion.setCompte(compte);
                resultat.close();
                stm.close();
                return suggestion;
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
    public boolean update(Suggestion Suggestion) {
        PreparedStatement stm = null;
        String requete = "UPDATE Suggestion SET ID = ?, TITRE = ?, "
                + "AUTEUR = ?, MESSAGE = ? WHERE  = ?";
        try 
        {
            stm = cnx.prepareStatement(requete);
            stm.setInt(1, Suggestion.getId());
            stm.setString(2, Suggestion.getTitre());
            stm.setString(3, Suggestion.getAuteur());
            stm.setString(4, Suggestion.getMessage());
            stm.setString(5, Suggestion.getCompte().getNumero());
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
    public List<Suggestion> findAll() {
        Statement stm = null;
        ResultSet resultat = null;
        List<Suggestion> listeSuggestion = new LinkedList();
        try 
        {
            stm = cnx.createStatement(); 
            resultat = stm.executeQuery("SELECT * FROM suggestion"
                + " INNER JOIN compte ON compte.NUMERO = suggestion.COMPTE_ID");
            while (resultat.next())
            {
                    Suggestion s = new Suggestion();
                    s.setId(s.getId());
                    s.setTitre(s.getTitre());
                    s.setAuteur(s.getAuteur());
                    s.setMessage(s.getMessage());
                    Compte compte = new Compte(
                        resultat.getString("NUMERO"),
                        resultat.getString("PRENOM"),
                        resultat.getString("NOM"),
                        resultat.getString("MDP"),
                        resultat.getInt("TYPE"));                       
                s.setCompte(compte);
                    
                    
                    listeSuggestion.add(s);
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
        return listeSuggestion;
    }
}
