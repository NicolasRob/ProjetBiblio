//Affichage des emprunts du compte connecté
//L'utilisateur doit être connecté pour accéder à la page des emprunts

package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Emprunt;
import com.robillard.bibliotheque.modele.dao.EmpruntDAO;
import com.robillard.bibliotheque.util.Connexion;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AfficherEmprunts extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if (request.getSession().getAttribute("login") != null)
        {
            try{
                List<Emprunt> listeEmprunt = new LinkedList();
                Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
                Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
                Connection cnx = (Connection) Connexion.getInstance();
                EmpruntDAO dao = new EmpruntDAO(cnx);
                listeEmprunt = dao.findByCompte((String) request.getSession().getAttribute("login"));
                for (Emprunt e : listeEmprunt) {if(e.getStatus().equals("RESERVATION")){listeEmprunt.remove(e);}}
                request.setAttribute("listeEmprunt", listeEmprunt);
            }
            
            catch (Exception exp)
            {
                Logger logger = Logger.getLogger("monLogger");
                logger.log(Level.SEVERE, exp.getMessage());
                String message = "Une erreur inattendue s'est produite lors"
                        + " de la recherche. Veuillez réessayer plus tard.";
                request.setAttribute("erreurException", message);
            }
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/emprunts.jsp");
            r.forward(request, response);
        }
        else
        {
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
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
