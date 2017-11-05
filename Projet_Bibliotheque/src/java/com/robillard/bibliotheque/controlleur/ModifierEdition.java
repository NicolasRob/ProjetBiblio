//Modifie un édition de la base de données
//L'utilisateur doit être connecté et être de type 2 (employé)
//Tous les champs doivent être remplis
//La date doit avoir le format yyyy-MM-dd
//Le nombre de page doit être entier
//Le id soumis doit correspondre à un ouvrage dans la base de données
package com.robillard.bibliotheque.controlleur;

import com.mysql.jdbc.Connection;
import com.robillard.bibliotheque.modele.classes.Edition;
import com.robillard.bibliotheque.modele.dao.EditionDAO;
import com.robillard.bibliotheque.util.Connexion;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class ModifierEdition extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setCharacterEncoding("utf8");
        response.setContentType("utf8");
        OutputStream out = null;
        InputStream in = null;
        try
        {
            if (request.getSession().getAttribute("type") == null
                    || (Integer) request.getSession().getAttribute("type") != 2)
            {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
                r.forward(request, response);
            }
            else if (request.getParameter("titre") == null
                    || "".equals(request.getParameter("titre").trim())
                    || request.getParameter("auteur") == null
                    || "".equals(request.getParameter("auteur").trim())
                    || request.getParameter("isbn") == null
                    || "".equals(request.getParameter("isbn").trim())
                    || request.getParameter("editeur") == null
                    || "".equals(request.getParameter("editeur").trim())
                    || request.getParameter("date") == null
                    || "".equals(request.getParameter("date").trim())
                    //|| request.getParameter("image") == null
                    //|| "".equals(request.getParameter("image").trim())
                    || request.getParameter("id") == null
                    || "".equals(request.getParameter("id").trim()))
            {
                String message = "Tous les champs doivent etre remplis";
                response.sendRedirect("go?action=afficherModificationEdition&message=" + message + "&id=" + request.getParameter("id"));
            }
            else
            {
                //La date sera conservé sous forme de String, mais il faut
                //tout de même tester si elle a le bon format. Si la date entré
                //n'a pas le bon format, le parse déclenchera une ParseException
                DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatDate.parse(request.getParameter("date"));
                System.out.println("TEST1");
                Class.forName(this.getServletContext().getInitParameter("piloteJDBC"));
                Connexion.setUrl(this.getServletContext().getInitParameter("urlBd"));
                Connection cnx = (Connection) Connexion.getInstance();
                EditionDAO editionDao = new EditionDAO(cnx);

                System.out.println("TEST2");

                Edition edition = editionDao.read(request.getParameter("id"));
                if (edition != null)
                {
                    String path = this.getServletContext().getRealPath("/") + "/img";
                    final Part filePart = request.getPart("image");
                    String nomImage = getFileName(filePart);
                    out = new FileOutputStream(new File(path + File.separator + nomImage));
                    in = filePart.getInputStream();
                    int read = 0;
                    final byte[] bytes = new byte[1024];
                    while ((read = in.read(bytes)) != -1)
                    {
                        out.write(bytes, 0, read);
                    }
                    Edition e = new Edition(
                            Integer.parseInt(request.getParameter("id")),
                            Integer.parseInt(request.getParameter("pages")),
                            request.getParameter("isbn"),
                            request.getParameter("date"),
                            nomImage,
                            request.getParameter("editeur"),
                            edition.getOuvrage()
                    );
                    String message = "";
                    if (editionDao.update(e))
                    {
                        message = "L'" + URLEncoder.encode("é", "UTF-8") + "dition a "
                                + URLEncoder.encode("é", "UTF-8")
                                + "t" + URLEncoder.encode("é", "UTF-8")
                                + " modifi" + URLEncoder.encode("é", "UTF-8")
                                + " avec succ" + URLEncoder.encode("è", "UTF-8") + "s";
                    }
                    response.sendRedirect("go?action=afficherModificationEdition&message=" + message + "&id=" + request.getParameter("id"));
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
            response.sendRedirect("go?action=afficherModificationEdition&messageErreur=" + message + "&id=" + request.getParameter("id"));
        }
        catch (NumberFormatException e)
        {
            Logger logger = Logger.getLogger("monLogger");
            logger.log(Level.SEVERE, e.getMessage());
            String message = "Le nombre de page doit etre un nombre entier.";
            response.sendRedirect("go?action=afficherModificationEdition&messageErreur=" + message + "&id=" + request.getParameter("id"));
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
        finally
        {
            if (out != null)
            {
                out.close();
            }
            if (in != null)
            {
                in.close();
            }
        }
    }

    private String getFileName(final Part part)
    {
        System.out.println(part.getHeader("content-disposition"));
        String[] t = part.getHeader("content-disposition").split(";");
        for (String content : t)
        {
            if (content.trim().startsWith("filename"))
            {
                String s = content.substring(content.indexOf('=') + 1).trim();
                return s.substring(s.lastIndexOf(File.separator) + 1).replace("\"", "");
            }
        }
        return null;
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
