function ConnexionJoueurJeu(pseudo){
    
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
    
    var colonneScore = document.createElement("td");
    colonneScore.setAttribute("id",pseudo + "_Score");
    colonneScore.value = 0; 
    colonneScore.textContent = colonneScore.value;
    
    ligneJoueur.appendChild(colonnePseudo);
    ligneJoueur.appendChild(colonneScore);
 
    
    tableauScores.appendChild(ligneJoueur); 
}