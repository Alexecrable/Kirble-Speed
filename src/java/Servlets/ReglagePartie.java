package Servlets;





import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/reglagepartie"}) //statut : admin/invité - nom hote
public class ReglagePartie extends HttpServlet {
    
    protected int compteur = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            String role = request.getParameter("role");
            out.println("<html>\n" +
                    "    <head>\n" +
                    "        <title>"+role+"</title>\n" +
                            "        <link rel=\"stylesheet\" href=\"Styles/style.css\">\n" +
                            "        <link rel=\"stylesheet\" href=\"Styles/formulaire.css\">\n" +
                            "        <link rel=\"stylesheet\" href=\"Styles/reglage.css\">\n" +
                            "        <script src=\"Scripts/ConnexionJoueurReglages.js\"></script>\n" +
                            "        <script src=\"Scripts/LancementPartie.js\"></script>\n" +
                            "        <script src=\"Scripts/GetCookie.js\"></script>\n" +
                            "        <script src=\"Scripts/FermetureJoueur.js\"></script>\n" +
                            "        <script src=\"Scripts/CreationTableauMatchmaking.js\"></script>\n" +
                            "        <script src=\"Scripts/reglagePartie"+role+".js\" defer></script>\n" +
                                    "    </head>\n" +
                                    "    <body>\n" +
                                    "        <header>\n" +
                                    "            <div class=\"cote_bandeau\"><a rel=\"noopener noreferrer\" href=\"http://localhost:8080/Projet_DWA/\"><img src=\"images/kirbhome.png\" alt=\"accueil\"></a></div>\n" +
                                    "            <div>\n" +
                                    "                <h1>KIRBLE SPEED</h1>\n" +
                                    "            </div>\n" +
                                    "            <div class=\"cote_bandeau\" id=\"Compte\"></div>\n" +
                                    "        </header>\n" +
                                    "        <main>");
            if(role.equals("Hote")){
                out.println("<script>\n" +
                        "                var socket = new WebSocket('ws://localhost:8080/Projet_DWA/reglagepartie/' + GetCookie(\"pseudo_courant\") + '/' + GetCookie(\"pseudo_courant\"));\n" +
                        "            </script>\n" +
                        "            <form id=\"reglage\" action=\"JEU\" method=\"post\">\n" +
                        "                <div>\n" +
                        "                    <label for=\"nb_tours\">nombre de tours</label>\n" +
                        "                    <input type=\"number\" name=\"nb_tours\" min=\"2\" max=\"20\" value=\"10\">\n" +
                        "                </div>\n" +
                        "                <div>\n" +
                        "                    <label>temps par tour</label>\n" +
                        "                    <input type=\"range\" name=\"timer\" id=\"timeslide\" min=\"2000\" max=\"7000\" value=\"4000\">\n" +
                        "                    <input type=\"number\" type=\"number\" id=\"timespan\" min=\"2000\" max=\"7000\" value=\"4000\">\n" +
                        "                </div>\n" +
                        "                <div>\n" +
                        "                    <label for=\"nb_formes\">nombre de formes</label>\n" +
                        "                    <input type=\"number\" name=\"nb_formes\" min=\"2\" max=\"6\" value=\"4\">\n" +
                        "                </div>\n" +
                        "                <div>\n" +
                        "                    <label for=\"nb_couleurs\">nombre de couleurs</label>\n" +
                        "                    <input type=\"number\" name=\"nb_couleurs\" min=\"2\" max=\"7\" value=\"4\">\n" +
                        "                </div>\n" +
                        "                <div>\n" +
                        "                    <label for=\"avance\">Mode avancé</label>\n" +
                        "                    <input type=\"checkbox\" name=\"avance\">\n" +
                        "                </div>\n" +
                        "                <input id=\"submitter\" type=\"submit\" value=\"Lancer La Partie\" disabled>\n" +
                        "            </form>\n" +
                        "            <div id=\"couronne\"><img src=\"images/kirbcrown.png\"/><div id=\"hote_ligne\"></div></div>");      
            }
            else{
                String hote = request.getParameter("hote");
                out.println("<script>\n" +
                        "                var socket = new WebSocket('ws://localhost:8080/Projet_DWA/reglagepartie/"+hote+"/' + GetCookie(\"pseudo_courant\"));\n" +
                        "            </script>\n" +
                        "            <p>en attente du reglage du lancement de la partie</p>\n" +
                        "            <div id=\"couronne\"><img src=\"images/kirbcrown.png\"/><div id=\"hote_ligne\">"+hote+"</div></div>");
            }   out.println("<table id=\"joueursCo\"></table>\n" +
                    "        </main>\n" +
                    "        <footer>\n" +
                    "            <div class=\"cote_bandeau\"><a rel=\"noopener noreferrer\" target=\"_blank\" href=\"regles.html\"><img src=\"images/regles.png\" alt=\"regles\"></a></div>\n" +
                    "            <div>LATTARD & PENNE</div>\n" +
                    "            <div class=\"cote_bandeau\"><img src=\"images/logo.png\" alt=\"Logo\"></div>\n" +
                    "        </footer>\n" +
                    "    </body>\n" +
                    "</html>");
        } catch (IOException ex) {
            Logger.getLogger(ReglagePartie.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
       
    }

    // Handle GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    // Handle POST requests
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
