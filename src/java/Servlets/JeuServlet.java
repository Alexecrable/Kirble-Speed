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

@WebServlet(urlPatterns = {"/JEU"}) //statut : admin/invité - nom hote
public class JeuServlet extends HttpServlet {
    
    protected int compteur = 0;
    private String role;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)  {
        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            if (request.getParameter("hote") == null){
                
                this.role = "Hote";
            }
            else{
                
                this.role = "Invit";
            }   out.println("<html>\n" +
                    "    <head>\n" +
                    "        <title>miaou</title>\n" +
                    "        <link rel=\"stylesheet\" href=\"Styles/style.css\">\n" +
                    
                    "<link rel=\"stylesheet\" href=\"Styles/bouton_aux.css\">"+
                    "<link rel=\"stylesheet\" href=\"Styles/auxiliaire_liste.css\">"+
                    "        <link rel=\"stylesheet\" href=\"Styles/jeu.css\">\n" +
                    "        <script src=\"Scripts/GetCookie.js\"></script>\n" +
                    "        <script src=\"Scripts/FermetureJoueur.js\"></script>\n" +
                    "        <script src=\"Scripts/FinPartie.js\"></script>\n" +
                    "        <script src=\"Scripts/FaireChoix.js\"></script>\n" +
                    "        <script src=\"Scripts/FinPartie.js\"></script>\n" +
                    "        <script src=\"Scripts/ConnexionJoueurJeu.js\"></script>\n" +
                    "        <script src=\"Scripts/UpdateScore.js\"></script>\n" +
                    "        <script src=\"Scripts/CreationTableauJeu.js\"></script>\n" +
                    "        <script src=\"Scripts/AffichageCartes.js\"></script>\n" +
                    "        <script src=\"Scripts/deroulementJeu"+this.role+".js\" defer></script>");
            if(this.role.equals("Hote")){
                int nbTours = Integer.parseInt(request.getParameter("nb_tours"));
                int timer = Integer.parseInt(request.getParameter("timer"));
                int nbFormes = Integer.parseInt(request.getParameter("nb_formes"));
                int nbCouleurs = Integer.parseInt(request.getParameter("nb_couleurs"));
                String avance = request.getParameter("avance");
                
                
                out.println("<script>\n" +
                        "            var socket = new WebSocket('ws://localhost:8080/Projet_DWA/jeu/' + GetCookie(\"pseudo_courant\") + '/' + GetCookie(\"pseudo_courant\"));\n" +
                        "        </script>\n" +
                        "        <script src=\"Scripts/InitPartie.js\"></script>\n" +
                        "        <button style=\"display: none\" id=\"bouton_lance\" onclick=\"InitPartie("+nbTours+","+timer+","+nbFormes+","+nbCouleurs+","+avance+"); this.remove()\"></button>");      
            }
            else{
                out.println("<script>\n" +
                        "            var socket = new WebSocket('ws://localhost:8080/Projet_DWA/jeu/"+request.getParameter("hote")+"/' + GetCookie(\"pseudo_courant\"));\n" +
                        "        </script>");
            }
            out.println("</head>\n" +
                    "    <body>\n" +
                    "        <header>\n" +
                    "            <div class=\"cote_bandeau\"><a rel=\"noopener noreferrer\" href=\"http://localhost:8080/Projet_DWA/\"><img src=\"images/kirbhome.png\" alt=\"accueil\"></a></div>\n" +
                    "            <div>\n" +
                    "                <h1>KIRBLE SPEED</h1>\n" +
                    "            </div>\n" +
                    "            <div class=\"cote_bandeau\" id=\"Compte\"></div>\n" +
                    "        </header>\n" +
                    "        <main>\n" +
                    "            <div id=\"sider\">\n" +
                    "                <table id=\"scores\"></table>\n" +
                    "            </div>\n" +
                    "<div id=\"centercont\">" +
                    "            <div id=\"Milieu\"></div>\n" +
                    "            <div id=\"Boutons\">\n" +
                    "                <button id=\"deux\" onclick=\"FaireChoix('deux')\" disabled>Les deux</button>\n" +
                    "                <button id=\"forme\" onclick=\"FaireChoix('forme')\" disabled>Forme</button>\n" +
                    "                <button id=\"couleur\" onclick=\"FaireChoix('couleur')\" disabled>Couleur</button>\n" +
                    "                <button id=\"aucun\" onclick=\"FaireChoix('aucun')\" disabled>Aucun</button>\n" +
                    "            </div>\n" +
                    "            <div id=\"Joueur\"></div>\n" +
                    "</div>" +
                    "        </main>\n" +
                    "        <footer>\n" +
                    "            <div class=\"cote_bandeau\"><a rel=\"noopener noreferrer\" target=\"_blank\" href=\"regles.html\"><img src=\"images/regles.png\" alt=\"regles\"></a></div>\n" +
                    "            <div>LATTARD & PENNE</div>\n" +
                    "            <div class=\"cote_bandeau\"><img src=\"images/logo.png\" alt=\"Logo\"></div>\n" +
                    "        </footer>\n" +
                    "    </body>\n" +
                    "</html>");
        } catch (IOException ex) {
            Logger.getLogger(JeuServlet.class.getName()).log(Level.SEVERE, null, ex);
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
