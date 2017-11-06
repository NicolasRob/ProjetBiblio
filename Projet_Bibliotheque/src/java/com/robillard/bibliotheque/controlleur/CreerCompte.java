//Créer un nouveau compte
//Tous les champs doivent être remplis
//L'utilisateur doit être un employé de type 2

package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Compte;
import com.robillard.bibliotheque.modele.dao.CompteDAO;
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

public class CreerCompte extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setCharacterEncoding("utf8");
        response.setContentType("utf8");
        try
        {
            Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
            Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
            Connection cnx = (Connection) Connexion.getInstance();
            CompteDAO compteDao = new CompteDAO(cnx);
            Compte compte;
            if (request.getSession().getAttribute("type") == null
                    || (Integer) request.getSession().getAttribute("type") != 2)
            {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
                r.forward(request, response);
            }
            else if (request.getParameter("numero") == null
                    || "".equals(request.getParameter("numero").trim())
                    || request.getParameter("mdp") == null
                    || "".equals(request.getParameter("mdp").trim())
                    || request.getParameter("type") == null
                    || "".equals(request.getParameter("type").trim())
                    || request.getParameter("prenom") == null
                    || "".equals(request.getParameter("prenom").trim())
                    || request.getParameter("nom") == null
                    || "".equals(request.getParameter("nom").trim()))
            {
                request.setAttribute("messageErreur", "Tout les champs doivent être remplis");
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/creationCompte.jsp");
                r.forward(request, response);
            }
            else if (compteDao.read(request.getParameter("numero")) != null)
            {
                request.setAttribute("messageErreur", "Un compte avec ce numéro existe déja");
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/creationCompte.jsp");
                r.forward(request, response);
            }
            else
            {
                compte = new Compte(
                        request.getParameter("numero"),
                        request.getParameter("prenom"),
                        request.getParameter("nom"),
                        request.getParameter("mdp"),
                        Integer.parseInt(request.getParameter("type"))
                );
                
                String message = "Une erreur s'est produite";
                if (compteDao.create(compte))
                    message = "Le compte a "
                            + URLEncoder.encode("é", "UTF-8")
                            + "t" + URLEncoder.encode("é", "UTF-8")
                            + " ajout" + URLEncoder.encode("é", "UTF-8")
                            + " avec succ" + URLEncoder.encode("è", "UTF-8") + "s";
                response.sendRedirect("go?action=afficherCreationCompte&message=" + message);
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
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/creationCompte.jsp");
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
