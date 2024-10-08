<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AffichageJoueurs</title>
        <link rel="stylesheet" href="Styles/style.css">
        <link rel="stylesheet" href="Styles/auxiliaire_liste.css">
         <link rel="stylesheet" href="Styles/bouton_aux.css">
        
        <script src="Scripts/RecupServletJoueur.js" ></script>
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
        <%@ page import="JeuClasses.*" %>
        <%@ page import="DAOJungleSpeed.*" %>
        <%@ page import="java.util.List" %>
        <%@ page import="java.util.Collections" %>
        <%@ page import="java.util.ArrayList" %>
<%

    try{
            //Création DAOJoueur pour recuperer la liste des joueurs
           
            
            JungleSpeedDAOFactory factory = new JungleSpeed_JPA_DAOFactory();
            DAO<Joueur> daoJoueurs = factory.getDAOJoueur();
            List<Joueur> joueurs = daoJoueurs.findAll();
            
            
            //pour chaque joueur : Envoie ses infos a travers la websocket
            //afin que le client puisse afficher un tableau
            List<String> pseudos = new ArrayList<String>();
            out.println("<table>");
            for (Joueur joueur : joueurs){
                pseudos.add(joueur.getPseudo());
            }
            Collections.sort(pseudos);
            for (String pseudo : pseudos){
                out.println("<tr><td> <button  onclick=\"RecupServletJoueur(\'"+pseudo+"\')\">"+pseudo+"</td></tr>");
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
