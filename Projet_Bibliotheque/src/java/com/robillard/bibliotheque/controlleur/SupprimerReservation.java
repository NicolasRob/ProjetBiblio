//Permet de supprimer une réservation
//Si des réservations étaient en attente dans le futurs pour l'exemplaire en
//question, elles seront devancées par le nombre de jours restants à la
//réservation supprimé.
//Si la réservation est supprimé avant d'atteindre la date de début, toutes les
//réservations suivantes pour cette exemplaire seront devancées de 2 semaines.
package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Emprunt;
import com.robillard.bibliotheque.modele.dao.EmpruntDAO;
import com.robillard.bibliotheque.util.Connexion;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SupprimerReservation extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if (request.getSession().getAttribute("login") != null)
        {
            try
            {
                Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
                Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
                Connection cnx = (Connection) Connexion.getInstance();
                EmpruntDAO dao = new EmpruntDAO(cnx);
                Emprunt emprunt = dao.read(request.getParameter("id"));
                if (emprunt != null && emprunt.getCompte().getNumero() == request.getSession().getAttribute("login"))
                {
                    dao.delete(emprunt);
                    List<Emprunt> listeEmprunt = dao.findAllActiveByExemplaire(emprunt.getExemplaire().getId());
                    for (Emprunt e : listeEmprunt)
                    {
                        DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                        Date dt1 = formatDate.parse(e.getDateDebut());
                        Date dt2 = formatDate.parse(emprunt.getDateFin());
                        if (dt1.after(dt2))
                        {
                            e.devancerEmprunt(emprunt.getJoursRestants());
                            dao.update(e);
                        }
                    }
                    String message = "L'ouvrage a " + URLEncoder.encode("é", "UTF-8")
                            + "t" + URLEncoder.encode("é", "UTF-8")
                            + " supprim" + URLEncoder.encode("é", "UTF-8")
                            + " avec succ" + URLEncoder.encode("è", "UTF-8") + "s";
                    response.sendRedirect("go?action=afficherGestionCatalogue&message=" + message
                            + "&recherche=" + request.getParameter("recherche")
                            + "&critere=" + request.getParameter("critere"));
                }
                else
                {
                    RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
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
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
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
