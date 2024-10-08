function ConnexionJoueur(pseudo){
    
    //****************************************************//
    //Créé une ligne dans le tableau en fonction du pseudo//
    //****************************************************//
    
    var ligneJoueur = document.createElement("tr");
    /*
    ajout d'un id pour pouvoir le supprimer directement sans avoir 
    a regenerer la page lors de sa deconnexion
    */
    ligneJoueur.setAttribute("id",pseudo);
    
    //creation des elements de la ligne de tableau
    
    var colonnePseudo = document.createElement("td");
    colonnePseudo.textContent = pseudo;
    
    var colonneCase = document.createElement("td");
    
    var checkbox = document.createElement("input");
    checkbox.setAttribute("type","checkbox");
    checkbox.setAttribute("name","checkBox");
    checkbox.setAttribute("id",pseudo+"_CB");
    colonneCase.appendChild(checkbox);
    
    var colonneInfoJoueur = document.createElement("td");
    
    var boutonInfos = document.createElement("button");
    boutonInfos.setAttribute("onclick","RecupServletJoueur(\"" + pseudo + "\")");
    boutonInfos.textContent = "Informations";
    colonneInfoJoueur.appendChild(boutonInfos);
    
    ligneJoueur.appendChild(colonnePseudo);
    ligneJoueur.appendChild(colonneCase);
    ligneJoueur.appendChild(colonneInfoJoueur);
    
    tableauJoueurs.appendChild(ligneJoueur); 
}