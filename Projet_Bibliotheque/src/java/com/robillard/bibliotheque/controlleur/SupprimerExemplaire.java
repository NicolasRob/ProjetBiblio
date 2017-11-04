package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Edition;
import com.robillard.bibliotheque.modele.classes.Exemplaire;
import com.robillard.bibliotheque.modele.dao.EditionDAO;
import com.robillard.bibliotheque.modele.dao.ExemplaireDAO;
import com.robillard.bibliotheque.util.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SupprimerExemplaire extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
            Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
            Connection cnx = (Connection) Connexion.getInstance();
            ExemplaireDAO dao = new ExemplaireDAO(cnx);
            Exemplaire exemplaire = dao.read(request.getParameter("id"));
            if (exemplaire != null)
            {
                dao.delete(exemplaire);
                String message = "L'exemplaire a " 
                                + URLEncoder.encode("é", "UTF-8") 
                                + "t" + URLEncoder.encode("é", "UTF-8") + 
                                " supprim" + URLEncoder.encode("é", "UTF-8") + 
                                " avec succ" + URLEncoder.encode("è", "UTF-8") + "s";
                response.sendRedirect("go?action=afficherModificationEdition&message="+message+"&id="+exemplaire.getEdition().getId());
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
