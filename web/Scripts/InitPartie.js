function InitPartie(nbTours, timer, nbFormes, nbCouleurs, avance){
    
    //*****************************************************************************//
    //Envoie les parametres de la partie au serveur pour l'initialiser et la lancer//
    //*****************************************************************************//
    
    var message = "Initialisation/"+ nbTours + "/" + timer + "/" + nbFormes + "/" + nbCouleurs + "/" + avance;

    socket.send(message);
    
}

