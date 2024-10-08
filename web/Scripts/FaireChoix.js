function FaireChoix(choisi){
    
    //***************************************//
    //Envoie la decision du joueur au serveur//
    //***************************************//
    
    //desactive les boutons pour empecher le joueur d'envoyer de nouveaux choix
    //avant le debut du prochain round
    choix_deux.disabled = true;
    choix_couleur.disabled = true;
    choix_forme.disabled = true;
    choix_aucun.disabled = true;
    
    clearTimeout(inaction);
    socket.send("choix/"+ choisi + "/" + GetCookie("pseudo_courant"));

}