package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Edition;
import com.robillard.bibliotheque.modele.classes.Exemplaire;
import com.robillard.bibliotheque.modele.classes.Ouvrage;
import com.robillard.bibliotheque.modele.dao.EditionDAO;
import com.robillard.bibliotheque.modele.dao.EmpruntDAO;
import com.robillard.bibliotheque.modele.dao.ExemplaireDAO;
import com.robillard.bibliotheque.util.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
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

public class AfficherDetails extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
            Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
            Connection cnx = (Connection) Connexion.getInstance();
            EditionDAO dao = new EditionDAO(cnx);
            Edition edition = dao.read(request.getParameter("id"));
            if (edition != null)
            {
                ExemplaireDAO exemplaireDao = new ExemplaireDAO(cnx);
                List<Exemplaire> listeExemplaire = new LinkedList();
                listeExemplaire = exemplaireDao.findByEdition(edition.getId());
                HashMap<Exemplaire, String> mapExemplaireDate = new HashMap<Exemplaire, String>();
                request.setAttribute("edition", edition);
                EmpruntDAO empruntDao = new EmpruntDAO(cnx);
                for (Exemplaire ex : listeExemplaire)
                    mapExemplaireDate.put(ex, empruntDao.findMaxDateByExemplaire(ex.getId()));
                request.setAttribute("exemplaires", mapExemplaireDate);
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/details.jsp");
                r.forward(request, response);
            }
            else
            {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/catalogue.jsp");
                r.forward(request, response);
            }
        }
        catch (Exception exp)
        {
            Logger logger = Logger.getLogger("monLogger");
            logger.log(Level.SEVERE, exp.getMessage());
            String message = "Une erreur inattendue s'est produite lors"
                    + " de l'affichage. Veuillez réessayer plus tard.";
            request.setAttribute("erreurException", message);
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/catalogue.jsp");
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
            throws ServletException, IOException {
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
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
