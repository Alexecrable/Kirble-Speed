package Servlets;

import DAOJungleSpeed.DAO;
import DAOJungleSpeed.DAOException;
import DAOJungleSpeed.JungleSpeedDAOFactory;
import DAOJungleSpeed.JungleSpeed_JPA_DAOFactory;

import JeuClasses.JoueurPartieStat;
import JeuClasses.Partie;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/infostat"})
public class infoStat extends HttpServlet {
    
    protected int compteur = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            String numpartie =  request.getParameter("numpartie");
            JungleSpeedDAOFactory factory = new JungleSpeed_JPA_DAOFactory();
            DAO<Partie> daoParties = factory.getDAOPartie();
            Partie partie = daoParties.find(numpartie);
            out.println("<html>\n" +
                    "    <head>\n" +
                    "        <title>infoPartie</title>\n" +
                            "        <link rel=\"stylesheet\" href=\"Styles/style.css\">\n" +
                            "    </head>\n" +
                            "    <body>\n" +
                            "        <header>\n" +
                            "            <div class=\"cote_bandeau\"><a rel=\"noopener noreferrer\" href=\"http://localhost:8080/Projet_DWA/\"><img src=\"images/kirbhome.png\" alt=\"accueil\"></a></div>\n" +
                            "            <div>\n" +
                            "                <h1>"+numpartie+"</h1>\n" +
                            "            </div>\n" +
                            "            <div class=\"cote_bandeau\" id=\"Compte\"></div>\n" +
                            "        </header>\n" +
                            "        <main>\n" +
                                       
                                    "            <table>");
            for (JoueurPartieStat info : partie.getJoueurPartieStatList()){
                out.println("<tr><td>" + info.toString() + "</td></tr>");
            }
            out.println("</table>\n" +
                    "        </main>\n" +
                    "        <footer>\n" +
                    "            <div class=\"cote_bandeau\"><a rel=\"noopener noreferrer\" target=\"_blank\" href=\"regles.html\"><img src=\"images/regles.png\" alt=\"regles\"></a></div>\n" +
                    "            <div>LATTARD & PENNE</div>\n" +
                    "            <div class=\"cote_bandeau\"><img src=\"images/logo.png\" alt=\"Logo\"></div>\n" +
                    "        </footer>\n" +
                    "    </body>\n" +
                    "</html>");
        } catch (IOException | DAOException ex) {
            Logger.getLogger(infoStat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
        
        
    }

    // Handle GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    // Handle POST requests
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
