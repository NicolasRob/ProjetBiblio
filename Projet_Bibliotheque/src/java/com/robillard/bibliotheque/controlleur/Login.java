//Tente de connecter l'utilisateur avec un numéro de membre et un mot de passe
//L'utilisateur ne peut pas être déja connecté
package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Compte;
import com.robillard.bibliotheque.modele.dao.CompteDAO;
import com.robillard.bibliotheque.util.Connexion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if (request.getSession().getAttribute("login") == null)
        {
            try
            {
                Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
                Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
                Connection cnx = (Connection) Connexion.getInstance();
                CompteDAO dao = new CompteDAO(cnx);
                Compte c = dao.read(request.getParameter("numeroMembre"));

                if (c != null && c.getMdp().equals(request.getParameter("pwd")))
                {
                    javax.servlet.http.HttpSession s = request.getSession(true);
                    s.setAttribute("login", c.getNumero());
                    s.setAttribute("type", c.getType());
                    response.sendRedirect("go?action=afficherEmprunts");
                }
                else
                {
                    request.setAttribute("erreurLogin", "Les informations de connection sont invalides");
                    RequestDispatcher r = this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp");
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
