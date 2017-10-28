package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Auteur;
import com.robillard.bibliotheque.modele.classes.Ouvrage;
import com.robillard.bibliotheque.modele.dao.AuteurDAO;
import com.robillard.bibliotheque.modele.dao.OuvrageDAO;
import com.robillard.bibliotheque.util.Connexion;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjouterOuvrage extends HttpServlet {

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
                request.getParameter("type") == null ||
                request.getParameter("type").trim() == "" ||
                request.getParameter("prenom") == null ||
                request.getParameter("prenom").trim() == "" ||
                request.getParameter("nom") == null ||
                request.getParameter("nom").trim() == "" ||
                request.getParameter("idAuteur") == null ||
                request.getParameter("idAuteur").trim() == "")
            {
                request.setAttribute("erreurAjout", "Tous les champs doivent être remplis");
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutOuvrage.jsp");
                r.forward(request, response);
            }
            else
            {
                System.out.println("TEST1");
                Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
                Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
                Connection cnx = (Connection) Connexion.getInstance();
                AuteurDAO auteurDao = new AuteurDAO(cnx);
                OuvrageDAO ouvrageDao = new OuvrageDAO(cnx);

                System.out.println("TEST2");
                Auteur auteur = new Auteur(
                    request.getParameter("idAuteur"),
                    request.getParameter("prenom"),
                    request.getParameter("nom")
                );

                System.out.println("TEST3");
                Auteur auteurDb = auteurDao.read(auteur.getId());

                System.out.println("TEST4");
                System.out.println(auteur.getId() + auteur.getPrenom() + auteur.getNom());
                //System.out.println(auteurDb.getId() + auteurDb.getPrenom() + auteurDb.getNom()); 
                if (auteurDb == null)
                {
                    auteurDao.create(auteur);
                    auteurDb = auteurDao.read(auteur.getId());
                }
                System.out.println("TEST5");                
                if (auteurDb != null && 
                    auteurDb.getNom().equals(auteur.getNom()) &&
                    auteurDb.getPrenom().equals(auteur.getPrenom()))
                {
                    Ouvrage ouvrage = new Ouvrage(
                        request.getParameter("titre"),
                        request.getParameter("type"),
                        auteurDb
                    );

                    ouvrageDao.create(ouvrage);
                    String message = "L'ouvrage a " + URLEncoder.encode("é", "UTF-8") 
                            + "t" + URLEncoder.encode("é", "UTF-8") + 
                            " ajout" + URLEncoder.encode("é", "UTF-8") + 
                            " avec succ" + URLEncoder.encode("è", "UTF-8") + "s";
                    response.sendRedirect("go?action=afficherAjoutOuvrage&message="+message);
                }
                else
                {
                    String message = "L'identification " + auteur.getId() +
                         " est déja associé à un auteur du nom de " +
                         auteurDb.getPrenom() + " " + auteurDb.getNom();
                    request.setAttribute("erreurAuteur", message);
                    RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutOuvrage.jsp");
                    r.forward(request, response);
                }
            }
        }
        catch (Exception e)
        {
            Logger logger = Logger.getLogger("monLogger");
            logger.log(Level.SEVERE, e.getMessage());
            String message = "Une erreur inattendue s'est produite. Veuillez"
                    + " réessayer plus tard.";
            request.setAttribute("erreurException", message);
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutOuvrage.jsp");
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
