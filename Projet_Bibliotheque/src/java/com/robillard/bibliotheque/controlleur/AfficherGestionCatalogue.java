//Affichage de la page de gestion catalogue
//Contient intialement un formulaire de recherche
//Si les paramèters nécéssaires sont fournis dans la requête,
//Un map qui contient les ouvrages correspondants et leurs éditions sera mis dans request
//La vue parcourera ensuite ce map pour afficher les ouvrages et éditions
//L'utilisateur doit être connecté et être de type 2 (employé) pour y accéder
package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Edition;
import com.robillard.bibliotheque.modele.classes.Ouvrage;
import com.robillard.bibliotheque.modele.dao.EditionDAO;
import com.robillard.bibliotheque.modele.dao.OuvrageDAO;
import com.robillard.bibliotheque.util.Connexion;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AfficherGestionCatalogue extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if (request.getSession().getAttribute("type") != null
                && Integer.parseInt(request.getSession().getAttribute("type").toString()) >= 2)
        {
            List<Ouvrage> listeOuvrage;
            List<Edition> listeEdition;
            HashMap<Ouvrage, List<Edition>> mapOuvrageEdition = new HashMap<Ouvrage, List<Edition>>();
            if (request.getParameter("recherche") != null
                    && !"".equals(request.getParameter("recherche").trim())
                    && request.getParameter("critere") != null)
            {
                try
                {
                    Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
                    Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
                    Connection cnx = (Connection) Connexion.getInstance();
                    OuvrageDAO dao = new OuvrageDAO(cnx);
                    EditionDAO editionDao = new EditionDAO(cnx);
                    System.out.println(request.getParameter("critere"));
                    System.out.println(request.getParameter("recherche"));
                    listeOuvrage = dao.findAll(request.getParameter("critere"),
                            request.getParameter("recherche"));
                    request.setAttribute("ouvrages", listeOuvrage);
                    listeEdition = editionDao.findAll();
                    for (Ouvrage o : listeOuvrage)
                        mapOuvrageEdition.put(o, new LinkedList());
                    for (Edition e : listeEdition)
                        if (mapOuvrageEdition.containsKey(e.getOuvrage()))
                            mapOuvrageEdition.get(e.getOuvrage()).add(e);
                    request.setAttribute("ouvragesEditions", mapOuvrageEdition);
                }
                catch (Exception exp)
                {
                    Logger logger = Logger.getLogger("monLogger");
                    logger.log(Level.SEVERE, exp.getMessage());
                    String message = "Une erreur inattendue s'est produite lors"
                            + " de la recherche. Veuillez réessayer plus tard.";
                    request.setAttribute("erreurException", message);
                }
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/gestionCatalogue.jsp");
                r.forward(request, response);
            }
            else
            {
                request.setAttribute("erreurInput", "Les champs ne peuvent être vides");
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/gestionCatalogue.jsp");
                r.forward(request, response);
            }
        }
        else
        {
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
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
