package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Edition;
import com.robillard.bibliotheque.modele.classes.Exemplaire;
import com.robillard.bibliotheque.modele.classes.Ouvrage;
import com.robillard.bibliotheque.modele.dao.EditionDAO;
import com.robillard.bibliotheque.modele.dao.ExemplaireDAO;
import com.robillard.bibliotheque.modele.dao.OuvrageDAO;
import com.robillard.bibliotheque.util.Connexion;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjouterExemplaire extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setContentType("utf8");
        try 
        {
            Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
            Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
            Connection cnx = (Connection) Connexion.getInstance();
            EditionDAO editionDao;
            Edition edition;
            ExemplaireDAO exemplaireDao;
            if (request.getSession().getAttribute("type") == null ||
                (Integer)request.getSession().getAttribute("type") != 2)
            {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
                r.forward(request, response);
            }
            else if (request.getParameter("id") == null ||
                "".equals(request.getParameter("id").trim()))
            {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
                r.forward(request, response);
            }
            else if (request.getParameter("emplacement") == null ||
                "".equals(request.getParameter("emplacement").trim()))
            {
                String message = "L'exemplaire doit avoir un emplacement";
                response.sendRedirect("go?action=afficherModificationEdition&messageErreurExemplaire="+message+"&id="+request.getParameter("id"));
            }
            else
            {
                editionDao = new EditionDAO(cnx);
                edition = editionDao.read(request.getParameter("id"));
                
                if (edition != null)
                {
                    exemplaireDao = new ExemplaireDAO(cnx);
                    Exemplaire exemplaire = new Exemplaire(
                            request.getParameter("emplacement"),
                            edition
                    );
                    String message = "";
                    if (exemplaireDao.create(exemplaire))
                        message = "L'exemplaire a " 
                                + URLEncoder.encode("é", "UTF-8") 
                                + "t" + URLEncoder.encode("é", "UTF-8") + 
                                " ajout" + URLEncoder.encode("é", "UTF-8") + 
                                " avec succ" + URLEncoder.encode("è", "UTF-8") + "s";
                    response.sendRedirect("go?action=afficherModificationEdition&message="+message+"&id="+request.getParameter("id"));
                }
                else
                {
                    RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/gestionCatalogue.jsp");
                    r.forward(request, response);
                }
            }
        }
        catch (Exception e)
        {
            Logger logger = Logger.getLogger("monLogger");
            logger.log(Level.SEVERE, e.getMessage());
            logger.log(Level.SEVERE, e.toString());
            String message = "Une erreur inattendue s'est produite. Veuillez"
                    + " réessayer plus tard.";
            request.setAttribute("erreurException", message);
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/modEdition.jsp");
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
