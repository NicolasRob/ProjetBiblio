//Affichage des réservations du compte connecté
package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Emprunt;
import com.robillard.bibliotheque.modele.dao.EmpruntDAO;
import com.robillard.bibliotheque.util.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AfficherReservations extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getSession().getAttribute("login") != null){
            try{
                List<Emprunt> listeEmprunt = new LinkedList();
                Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
                Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
                Connection cnx = (Connection) Connexion.getInstance();
                EmpruntDAO dao = new EmpruntDAO(cnx);
                listeEmprunt = dao.findByCompte((String) request.getSession().getAttribute("login"));
                List<Emprunt> listeReservation = new LinkedList();
                //crée un iterateur et parcoure la liste pour ne sauvegarder que les reservation 
                System.out.println(listeEmprunt.size());
                for(Emprunt item : listeEmprunt){
                    System.out.println(item.getCompte());
                }
                System.out.println("salut");
                for (Iterator<Emprunt> it = listeEmprunt.iterator(); it.hasNext();) {
                    System.out.println(it.next());
                    System.out.println(it.hasNext());
                    if(it.next().getStatus().equalsIgnoreCase("RESERVATION")){
                        listeReservation.add(it.next());
                    }
                    else{}
                }
                request.setAttribute("listeReservation", listeReservation);
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
