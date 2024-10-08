<%-- 
    Document   : AffichageJoueurs
    Created on : 8 avr. 2024, 21:25:44
    Author     : alexecrable
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AffichageParties</title>
    </head>
    <body>
        <%@ page import="donnees.*" %>
<%@ page import="DAOJungleSpeed.*" %>
<%@ page import="java.util.List" %>
<%

    try{
            //Création DAOJoueur pour recuperer la liste des joueurs
            
            JungleSpeedDAOFactory factory = new JungleSpeed_JPA_DAOFactory();
            DAO<Partie> daoParties = factory.getDAOPartie();
            List<Partie> parties = daoParties.findAll();
            
            //pour chaque joueur : Envoie ses infos a travers la websocket
            //afin que le client puisse afficher un tableau
            out.println("<table>");
            for (Partie partie : parties){
                out.println("<tr><td>Partie N°" + partie.getNumpartie() + "</td></tr>");
                for (JoueurPartieStat jps : partie.getJoueurPartieStatList()){
                    out.println("<tr><td></td><td>jps.toString</td></tr>");
                }
            }
            out.println("</table>");
            
        } catch (Exception e) {
          
            e.printStackTrace();
        }
%>
      
    </body>
</html>

 