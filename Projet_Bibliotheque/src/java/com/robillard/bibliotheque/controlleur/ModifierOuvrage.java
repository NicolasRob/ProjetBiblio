//Modifie un ouvrage de la base de données
//L'utilisateur doit être connecté et être de type 2 (employé)
//Tous les champs doivent être remplis
//Le id soumis doit correspondre à un ouvrage dans la base de données
//Comme dans l'ajout d'ouvrage, il est possible d'ajouter ou de modifier l'auteur
package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Auteur;
import com.robillard.bibliotheque.modele.classes.Ouvrage;
import com.robillard.bibliotheque.modele.dao.AuteurDAO;
import com.robillard.bibliotheque.modele.dao.OuvrageDAO;
import com.robillard.bibliotheque.util.Connexion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifierOuvrage extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setCharacterEncoding("utf8");
        response.setContentType("utf8");
        try
        {
            if (request.getSession().getAttribute("type") == null
                    || (Integer) request.getSession().getAttribute("type") != 2)
            {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
                r.forward(request, response);
            }
            else if (request.getParameter("titre") == null
                    || "".equals(request.getParameter("titre").trim())
                    || request.getParameter("type") == null
                    || "".equals(request.getParameter("type").trim())
                    || request.getParameter("prenom") == null
                    || "".equals(request.getParameter("prenom").trim())
                    || request.getParameter("nom") == null
                    || "".equals(request.getParameter("nom").trim())
                    || request.getParameter("idAuteur") == null
                    || "".equals(request.getParameter("idAuteur").trim()))
            {
                Auteur auteur = new Auteur(
                        request.getParameter("idAuteur"),
                        request.getParameter("prenom"),
                        request.getParameter("nom")
                );
                Ouvrage ouvrage = new Ouvrage(
                        request.getParameter("titre"),
                        request.getParameter("type"),
                        auteur
                );
                ouvrage.setId(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("ouvrage", ouvrage);
                request.setAttribute("erreurMod", "Tous les champs doivent être remplis");
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/modOuvrage.jsp");
                r.forward(request, response);
            }
            else
            {
                Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
                Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
                Connection cnx = (Connection) Connexion.getInstance();
                AuteurDAO auteurDao = new AuteurDAO(cnx);
                OuvrageDAO ouvrageDao = new OuvrageDAO(cnx);

                Auteur auteur = new Auteur(
                        request.getParameter("idAuteur"),
                        request.getParameter("prenom"),
                        request.getParameter("nom")
                );

                Auteur auteurDb = auteurDao.read(auteur.getId());
                //Création de l'auteur si nécéssaire
                if (auteurDb == null)
                {
                    auteurDao.create(auteur);
                    auteurDb = auteurDao.read(auteur.getId());
                }
                //Test si l'identifiant unique fait bel et bien référence à 
                //l'auteur entré.
                if (auteurDb != null
                        && auteurDb.getNom().equals(auteur.getNom())
                        && auteurDb.getPrenom().equals(auteur.getPrenom()))
                {
                    Ouvrage ouvrage = new Ouvrage(
                            request.getParameter("titre"),
                            request.getParameter("type"),
                            auteurDb
                    );

                    ouvrage.setId(Integer.parseInt(request.getParameter("id")));

                    ouvrageDao.update(ouvrage);
                    String message = "L'ouvrage a été modifié avec succès";
                    request.setAttribute("ouvrage", ouvrage);
                    request.setAttribute("message", message);
                    RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/modOuvrage.jsp");
                    r.forward(request, response);
                }
                else
                {
                    String message = "L'identification " + auteur.getId()
                            + " est déja associé à un auteur du nom de "
                            + auteurDb.getPrenom() + " " + auteurDb.getNom();
                    Ouvrage ouvrage = ouvrageDao.read(request.getParameter("id"));
                    request.setAttribute("ouvrage", ouvrage);
                    request.setAttribute("erreurAuteur", message);
                    RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/modOuvrage.jsp");
                    r.forward(request, response);
                }
            }
        }
        catch (Exception e)
        {
            Logger logger = Logger.getLogger("monLogger");
            logger.log(Level.SEVERE, e.getMessage());
            String message = "Une erreur inattendue s'est produite. Veuillez"
                    + " réessayer plus tard.";
            request.setAttribute("erreurException", message);
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/modOuvrage.jsp");
            r.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
