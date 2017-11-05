package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Emprunt;
import com.robillard.bibliotheque.modele.dao.EmpruntDAO;
import com.robillard.bibliotheque.util.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AfficherReservations extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Emprunt> listeEmprunt = new LinkedList();
        if(request.getSession().getAttribute("login") != null){
            try{
                Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
                Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
                Connection cnx = (Connection) Connexion.getInstance();
                EmpruntDAO dao = new EmpruntDAO(cnx);
                //a faire : ajouter la methode a EmpruntDAO
                listeEmprunt = dao.findAll(request.getSession().getAttribute("login"));
                request .setAttribute("reservations", listeEmprunt);
            }
            
            catch (Exception exp)
            {
                Logger logger = Logger.getLogger("monLogger");
                logger.log(Level.SEVERE, exp.getMessage());
                String message = "Une erreur inattendue s'est produite lors"
                        + " de la recherche. Veuillez réessayer plus tard.";
                request.setAttribute("erreurException", message);
            }
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/reservations.jsp");
            r.forward(request, response);
        }
        else
        {
            request.setAttribute("erreurInput", "erreur 404, tu est un tata.");
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/reservations.jsp");
            r.forward(request, response);
        }
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/reservations.jsp");
            r.forward(request, response);
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
