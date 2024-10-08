function ConnexionJoueurReglages(pseudo){
    
    //****************************************************//
    //Version de ConnexionJoueur sans éléments superflux  //
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
    
    
    ligneJoueur.appendChild(colonnePseudo);
    
    
    tableauJoueurs.appendChild(ligneJoueur); 
}