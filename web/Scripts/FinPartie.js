function FinPartie(vainqueur){
    
    //****************************************//
    //Affiche les cartes sur l'ecran du joueur//
    //****************************************//

    document.querySelector("#Milieu").remove();
    document.querySelector("#boutons").remove();
    document.querySelector("#Joueur").remove();
    document.querySelector("#centercont").remove();
    
    var divFin = document.createElement("div");
    
    var Vainqueurlabel = document.createElement("label");
    Vainqueurlabel.textContent = "Le vainqueur est : " + vainqueur;
    divFin.appendChild(Vainqueurlabel);
    
    var boutonQuit = document.createElement("button");
    boutonQuit.textContent = "Quitter la partie";
    boutonQuit.addEventListener("click",function(){
        window.location = "http://localhost:8080/Projet_DWA/";
    });
    
    divFin.appendChild(boutonQuit);
    
    document.querySelector("main").appendChild(divFin);
    
    
    
}