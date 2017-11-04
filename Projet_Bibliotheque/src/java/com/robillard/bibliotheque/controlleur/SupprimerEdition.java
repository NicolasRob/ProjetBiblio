//Supprime une édition de la bd
//L'utilisateur doit être connecté et être de type 2 (employé)
//Le id soumis doit correspondre à une édition dans la base de données
package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Edition;
import com.robillard.bibliotheque.modele.dao.EditionDAO;
import com.robillard.bibliotheque.util.Connexion;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SupprimerEdition extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if (request.getSession().getAttribute("type") != null
                && Integer.parseInt(request.getSession().getAttribute("type").toString()) >= 2)
        {
            try
            {
                Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
                Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
                Connection cnx = (Connection) Connexion.getInstance();
                EditionDAO dao = new EditionDAO(cnx);
                Edition edition = dao.read(request.getParameter("id"));
                if (edition != null)
                {
                    dao.delete(edition);
                    String message = "L'" + URLEncoder.encode("é", "UTF-8") + "dition a "
                            + URLEncoder.encode("é", "UTF-8")
                            + "t" + URLEncoder.encode("é", "UTF-8")
                            + " supprim" + URLEncoder.encode("é", "UTF-8")
                            + " avec succ" + URLEncoder.encode("è", "UTF-8") + "s";
                    response.sendRedirect("go?action=afficherGestionCatalogue&message=" + message
                            + "&recherche=" + request.getParameter("recherche")
                            + "&critere=" + request.getParameter("critere"));
                }
                else
                {
                    RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/gestionCatalogue.jsp");
                    r.forward(request, response);
                }
            }
            catch (Exception exp)
            {
                Logger logger = Logger.getLogger("monLogger");
                logger.log(Level.SEVERE, exp.getMessage());
                String message = "Une erreur inattendue s'est produite. Veuillez"
                        + " réessayer plus tard.";
                request.setAttribute("erreurException", message);
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
