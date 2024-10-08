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
        <title>AffichageJoueurs</title>
        <script>
            function fetchservlet(pseudo) {
                
                var servletUrl = 'infojoueur?pseudo=' + pseudo;
                var xhr = new XMLHttpRequest();
  
                xhr.open('POST', servletUrl, true);
                xhr.send();
                
               
                
                if(xhr.status  === 220 ){
                    document.open();
                    document.write(xhr.responseText);
                    document.close();
                }
            }
         </script>
    </head>
    
    <body>
        <%@ page import="donnees.*" %>
        <%@ page import="DAOJungleSpeed.*" %>
        <%@ page import="java.util.List" %>
<%

    try{
            //Création DAOJoueur pour recuperer la liste des joueurs
            
            JungleSpeedDAOFactory factory = new JungleSpeed_JPA_DAOFactory();
            DAO<Joueur> daoJoueurs = factory.getDAOJoueur();
            List<Joueur> joueurs = daoJoueurs.findAll();
            
            //pour chaque joueur : Envoie ses infos a travers la websocket
            //afin que le client puisse afficher un tableau
            out.println("<table>");
            for (Joueur joueur : joueurs){
                out.println("<tr><td> <button  onclick=\"fetchservlet(\'"+joueur.getPseudo()+"\')\" method=\"post\" value=\"" + joueur.getPseudo()  + "\">"+joueur.getPseudo()+"</td></tr>");
            }
            out.println("</table>");
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
%>
 
        
    </body>
</html>
