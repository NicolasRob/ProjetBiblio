/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robillard.bibliotheque.controlleur;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moumene
 */
public class ControleurFrontal extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher r = null;
        if (request.getParameter("action") == null)
        {
            r = this.getServletContext().getRequestDispatcher("/index.jsp");
            r.forward(request, response);
        }
        else
        {
            String act = request.getParameter("action");
            switch (act)
            {
                case "accueil":
                    r = this.getServletContext().getNamedDispatcher("AfficherAccueil");
                    r.forward(request, response);
                    break;
                case "catalogue":
                    r = this.getServletContext().getNamedDispatcher("AfficherCatalogue");
                    r.forward(request, response);
                    break;
                case "detailsLivre":
                    r = this.getServletContext().getNamedDispatcher("AfficherDetails");
                    r.forward(request, response);
                    break;
                case "afficherLogin":
                    r = this.getServletContext().getNamedDispatcher("AfficherLogin");
                    r.forward(request, response);
                    break;
                case "connection":
                    r = this.getServletContext().getNamedDispatcher("Connection");
                    r.forward(request, response);
                    break;
                default:
                    r = this.getServletContext().getNamedDispatcher("AfficherAccueil");
                    r.forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */

    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}