function CreationTableauMatchmaking(listePseudos){
    
    //************************************//
    //Appelle la fonction ConnexionJoueur //
    //pour chaque pseudo de la liste      //
    //************************************//
    
    for (let i =0; i < listePseudos.length; i++){
        ConnexionJoueur(listePseudos[i]);
    }
}