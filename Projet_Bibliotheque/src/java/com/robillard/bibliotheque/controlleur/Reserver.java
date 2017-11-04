package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Compte;
import com.robillard.bibliotheque.modele.classes.Edition;
import com.robillard.bibliotheque.modele.classes.Emprunt;
import com.robillard.bibliotheque.modele.classes.Exemplaire;
import com.robillard.bibliotheque.modele.dao.CompteDAO;
import com.robillard.bibliotheque.modele.dao.EditionDAO;
import com.robillard.bibliotheque.modele.dao.EmpruntDAO;
import com.robillard.bibliotheque.modele.dao.ExemplaireDAO;
import com.robillard.bibliotheque.modele.dao.ReservationDAO;
import com.robillard.bibliotheque.util.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Reserver extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setContentType("utf8");
        if (request.getSession().getAttribute("login") != null)
        {
            try
            {
                Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
                Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
                Connection cnx = (Connection) Connexion.getInstance();
                EmpruntDAO empruntDao = new EmpruntDAO(cnx);
                ExemplaireDAO exemplaireDao = new ExemplaireDAO(cnx);
                CompteDAO compteDao = new CompteDAO(cnx);
                if (request.getParameter("id") != null)
                {
                    if (empruntDao.findCurrentReservationForEdition(
                            request.getParameter("id"), 
                            request.getSession().getAttribute("login").toString()) != null)
                    {
                        String message = "Vous avez d" + URLEncoder.encode("é", "UTF-8") 
                                + "ja une r" + URLEncoder.encode("é", "UTF-8") 
                                +"servation ou un emprunt actif sur ce titre.";
                        response.sendRedirect("go?action=detailsLivre&message="+message+"&id="+request.getParameter("id"));

                    }
                    else
                    {
                        Pair<String, String> exemplaireDate = empruntDao.findEarliestAvailableExemplaireForEdition(request.getParameter("id"));
                        Exemplaire exemplaire = exemplaireDao.read(exemplaireDate.getKey());
                        Compte compte = compteDao.read(request.getSession().getAttribute("login").toString());
                        DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = formatDate.parse(exemplaireDate.getValue());
                        Emprunt emprunt = new Emprunt(
                            compte,
                            exemplaire,
                            date,
                            "RESERVATION"
                        );
                        boolean resultat = empruntDao.create(emprunt);

                        if (resultat)
                        {
                            String message = "La r" + URLEncoder.encode("é", "UTF-8") + "servation"
                            + " a " + URLEncoder.encode("é", "UTF-8") 
                            + "t" + URLEncoder.encode("é", "UTF-8") + 
                            " effectu" + URLEncoder.encode("é", "UTF-8") + 
                            " avec succ" + URLEncoder.encode("è", "UTF-8") + "s";
                            response.sendRedirect("go?action=detailsLivre&message="+message+"&id="+request.getParameter("id"));
                        }
                        else
                        {
                            String message = "Impossible d'effectuer une r" + URLEncoder.encode("é", "UTF-8") +
                                    "servation de cet ouvrage pour le moment";
                            response.sendRedirect("go?action=detailsLivre&message="+message+"&id="+request.getParameter("id"));
                        }
                    }
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
