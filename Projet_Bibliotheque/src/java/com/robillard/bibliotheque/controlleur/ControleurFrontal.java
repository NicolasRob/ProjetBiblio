package com.robillard.bibliotheque.controlleur;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControleurFrontal extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
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
                case "reserver":
                    r = this.getServletContext().getNamedDispatcher("Reserver");
                    r.forward(request, response);
                    break;
                case "afficherLogin":
                    r = this.getServletContext().getNamedDispatcher("AfficherLogin");
                    r.forward(request, response);
                    break;
                case "afficherReservations":
                    r = this.getServletContext().getNamedDispatcher("AfficherReservations");
                    r.forward(request, response);
                    break;
                case "afficherEmprunts":
                    r = this.getServletContext().getNamedDispatcher("AfficherEmprunts");
                    r.forward(request, response);
                    break;
                case "afficherGestionCatalogue":
                    r = this.getServletContext().getNamedDispatcher("AfficherGestionCatalogue");
                    r.forward(request, response);
                    break;
                case "afficherGestionCompte":
                    r = this.getServletContext().getNamedDispatcher("AfficherGestionCompte");
                    r.forward(request, response);
                    break;
                case "afficherAjoutOuvrage":
                    r = this.getServletContext().getNamedDispatcher("AfficherAjoutOuvrage");
                    r.forward(request, response);
                    break;
                case "afficherAjoutEdition":
                    r = this.getServletContext().getNamedDispatcher("AfficherAjoutEdition");
                    r.forward(request, response);
                    break;
                case "afficherSuggestion":
                    r = this.getServletContext().getNamedDispatcher("AfficherSuggestion");
                    r.forward(request, response);
                    break;
                case "afficherGestionSuggestion":
                    r = this.getServletContext().getNamedDispatcher("AfficherGestionSuggestion");
                    r.forward(request, response);
                    break;
                case "supprimerOuvrage":
                    r = this.getServletContext().getNamedDispatcher("SupprimerOuvrage");
                    r.forward(request, response);
                    break;
                case "supprimerEdition":
                    r = this.getServletContext().getNamedDispatcher("SupprimerEdition");
                    r.forward(request, response);
                    break;
                case "login":
                    r = this.getServletContext().getNamedDispatcher("Login");
                    r.forward(request, response);
                    break;
                case "logout":
                    r = this.getServletContext().getNamedDispatcher("Logout");
                    r.forward(request, response);
                    break;
                case "ajouterOuvrage":
                    r = this.getServletContext().getNamedDispatcher("AjouterOuvrage");
                    r.forward(request, response);
                    break;
                case "ajouterEdition":
                    r = this.getServletContext().getNamedDispatcher("AjouterEdition");
                    r.forward(request, response);
                    break;
                case "ajouterExemplaire":
                    r = this.getServletContext().getNamedDispatcher("AjouterExemplaire");
                    r.forward(request, response);
                    break;
                case "supprimerExemplaire":
                    r = this.getServletContext().getNamedDispatcher("SupprimerExemplaire");
                    r.forward(request, response);
                    break;
                case "supprimerReservation":
                    r = this.getServletContext().getNamedDispatcher("SupprimerReservation");
                    r.forward(request, response);
                    break;
                case "afficherModificationOuvrage":
                    r = this.getServletContext().getNamedDispatcher("AfficherModOuvrage");
                    r.forward(request, response);
                    break;
                case "afficherModificationAuteur":
                    r = this.getServletContext().getNamedDispatcher("AfficherModAuteur");
                    r.forward(request, response);
                    break;
                case "afficherModificationEdition":
                    r = this.getServletContext().getNamedDispatcher("AfficherModEdition");
                    r.forward(request, response);
                    break;
                case "afficherCreationCompte":
                    r = this.getServletContext().getNamedDispatcher("AfficherCreationCompte");
                    r.forward(request, response);
                    break;
                case "creerCompte":
                    r = this.getServletContext().getNamedDispatcher("CreerCompte");
                    r.forward(request, response);
                    break;
                case "modifierOuvrage":
                    r = this.getServletContext().getNamedDispatcher("ModifierOuvrage");
                    r.forward(request, response);
                    break;
                case "modifierAuteur":
                    r = this.getServletContext().getNamedDispatcher("ModifierAuteur");
                    r.forward(request, response);
                    break;
                case "modifierEdition":
                    r = this.getServletContext().getNamedDispatcher("ModifierEdition");
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
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>
}
