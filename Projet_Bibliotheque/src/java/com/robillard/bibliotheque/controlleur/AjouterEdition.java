
package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Auteur;
import com.robillard.bibliotheque.modele.classes.Edition;
import com.robillard.bibliotheque.modele.classes.Ouvrage;
import com.robillard.bibliotheque.modele.dao.AuteurDAO;
import com.robillard.bibliotheque.modele.dao.EditionDAO;
import com.robillard.bibliotheque.modele.dao.OuvrageDAO;
import com.robillard.bibliotheque.util.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
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

public class AjouterEdition extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setContentType("utf8");
        try 
        {
            if (request.getSession().getAttribute("type") == null ||
                (Integer)request.getSession().getAttribute("type") != 2)
            {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
                r.forward(request, response);
            }
            else if (request.getParameter("titre") == null ||
                request.getParameter("titre").trim() == "" || 
                request.getParameter("auteur") == null ||
                request.getParameter("auteur").trim() == "" ||
                request.getParameter("isbn") == null ||
                request.getParameter("isbn").trim() == "" ||
                request.getParameter("editeur") == null ||
                request.getParameter("editeur").trim() == "" ||
                request.getParameter("date") == null ||
                request.getParameter("date").trim() == "" ||
                request.getParameter("image") == null ||
                request.getParameter("image").trim() == "" ||
                request.getParameter("id") == null ||
                request.getParameter("id").trim() == "")
            {
                request.setAttribute("erreurAjout", "Tous les champs doivent être remplis");
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutEdition.jsp");
                r.forward(request, response);
            }
            else
            {
                DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatDate.parse(request.getParameter("date"));
                System.out.println("TEST1");
                Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
                Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
                Connection cnx = (Connection) Connexion.getInstance();
                EditionDAO editionDao = new EditionDAO(cnx);
                OuvrageDAO ouvrageDao = new OuvrageDAO(cnx);

                System.out.println("TEST2");
                
                Ouvrage ouvrage = ouvrageDao.read(request.getParameter("id"));
                if (ouvrage != null)
                {
                    Edition e = new Edition(
                            Integer.parseInt(request.getParameter("pages")),
                            request.getParameter("isbn"),
                            request.getParameter("date"),
                            request.getParameter("image"),
                            request.getParameter("editeur"),
                            ouvrage
                    );
                    String message = "";
                    if (editionDao.create(e))
                        message = "L'"+URLEncoder.encode("é", "UTF-8")+"dition a " 
                                + URLEncoder.encode("é", "UTF-8") 
                                + "t" + URLEncoder.encode("é", "UTF-8") + 
                                " ajout" + URLEncoder.encode("é", "UTF-8") + 
                                " avec succ" + URLEncoder.encode("è", "UTF-8") + "s";
                    response.sendRedirect("go?action=afficherAjoutEdition&message="+message+"&id="+request.getParameter("id"));
                }
                else
                {
                    RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/gestionCatalogue.jsp");
                    r.forward(request, response);
                }
            }
        }
        catch (ParseException e)
        {
            Logger logger = Logger.getLogger("monLogger");
            logger.log(Level.SEVERE, e.getMessage());
            String message = "La date de publication doit utiliser le format"
                    + " YYYY-MM-DD";
            response.sendRedirect("go?action=afficherAjoutEdition&messageErreur="+message+"&id="+request.getParameter("id"));
        }
        catch (NumberFormatException e)
        {
            Logger logger = Logger.getLogger("monLogger");
            logger.log(Level.SEVERE, e.getMessage());
            String message = "Le nombre de page doit etre un nombre entier.";
            response.sendRedirect("go?action=afficherAjoutEdition&messageErreur="+message+"&id="+request.getParameter("id"));
        }
        catch (Exception e)
        {
            Logger logger = Logger.getLogger("monLogger");
            logger.log(Level.SEVERE, e.getMessage());
            logger.log(Level.SEVERE, e.toString());
            String message = "Une erreur inattendue s'est produite. Veuillez"
                    + " réessayer plus tard.";
            request.setAttribute("erreurException", message);
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutEdition.jsp");
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
