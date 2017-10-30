package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Auteur;
import com.robillard.bibliotheque.modele.classes.Ouvrage;
import com.robillard.bibliotheque.modele.dao.AuteurDAO;
import com.robillard.bibliotheque.modele.dao.OuvrageDAO;
import com.robillard.bibliotheque.util.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifierAuteur extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try 
        {
            if (request.getSession().getAttribute("type") == null ||
                (Integer)request.getSession().getAttribute("type") != 2)
            {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
                r.forward(request, response);
            }
            else if (request.getParameter("prenom") == null ||
                "".equals(request.getParameter("prenom").trim()) ||
                request.getParameter("nom") == null ||
                "".equals(request.getParameter("nom").trim()) )
            {
                Auteur auteur = new Auteur(
                        request.getParameter("id"),
                        request.getParameter("prenom"),
                        request.getParameter("nom")
                );
                request.setAttribute("auteur", auteur);
                request.setAttribute("erreurMod", "Tous les champs doivent être remplis");
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/modAuteur.jsp");
                r.forward(request, response);
            }
            else
            {
                Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
                Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
                Connection cnx = (Connection) Connexion.getInstance();
                AuteurDAO auteurDao = new AuteurDAO(cnx);

                Auteur auteur = new Auteur(
                    request.getParameter("id"),
                    request.getParameter("prenom"),
                    request.getParameter("nom")
                );
                
                System.out.println(auteur.getId());
                System.out.println(auteur.getPrenom());
                System.out.println(auteur.getNom());
                auteurDao.update(auteur);
                String message = "L'auteur a été modifié avec succès";
                request.setAttribute("auteur", auteur);
                request.setAttribute("message", message);
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/modAuteur.jsp");
                r.forward(request, response);
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
