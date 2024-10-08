<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AffichageParties</title>
        <link rel="stylesheet" href="Styles/style.css">
         <link rel="stylesheet" href="Styles/auxiliaire_liste.css">
          <link rel="stylesheet" href="Styles/bouton_aux.css">
         
        <script src="Scripts/RecupServletPartieStat.js" ></script>
        
    </head>
    
    <body>
           <header>
            <div class="cote_bandeau"><a rel="noopener noreferrer" href="http://localhost:8080/Projet_DWA/"><img src="images/kirbhome.png" alt="accueil"></a></div>
            <div>
                <h1>KIRBLE SPEED</h1>
            </div>
            <div class="cote_bandeau" id="Compte"></div>
        </header>
    <body>
        <main>
        <%@ page import="JeuClasses.Partie" %>
        <%@ page import="JeuClasses.JoueurPartieStat" %>
        <%@ page import="DAOJungleSpeed.*" %>
        <%@ page import="java.util.List" %>
        <%@ page import="java.util.ArrayList" %>
<%

    try{
        
    
            //Création DAOJoueur pour recuperer la liste des joueurs
            
            JungleSpeedDAOFactory factory = new JungleSpeed_JPA_DAOFactory();
            DAO<Partie> daoParties = factory.getDAOPartie();
            List<Partie> parties = daoParties.findAll();
            
            out.println("<table>");
        
            for (Partie partie : parties){
           
                out.println("<tr><td> <button  onclick=\"RecupServletPartieStat(\'"+partie.getNumpartie()+"\')\">"+partie.getNumpartie()+"</td><td class=\"joueurs\">");
                for(JoueurPartieStat jps : partie.getJoueurPartieStatList()){
                
                    out.println("<div>"+jps.getJoueurPartieStatPK().getPseudo()+"</div>");
    }
    
        out.println("</td></tr>");
                
            }
            out.println("</table>");
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
%>
        </main>
    </body>
 <footer>
            <div class="cote_bandeau"><a rel="noopener noreferrer" target="_blank" href="regles.html"><img src="images/regles.png" alt="regles"></a></div>
            <div>LATTARD & PENNE</div>
            <div class="cote_bandeau"><img src="images/logo.png" alt="Logo"></div>
        </footer>
        
    </body>
</html>
