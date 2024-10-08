
var zone_connection = document.querySelector("#Compte");
var bouton_jeu = document.querySelector("#bouton_jeu");

var bouton_compte = document.createElement("a");
//generation html pour changer l'accueil dans la version connectée
if(true){

    bouton_compte.setAttribute("href","./login.html");
    bouton_compte.textContent = "Connection";
    bouton_jeu.disabled = true;
}
else{//generation html pour changer l'accueil dans la version invitée
    bouton_compte.setAttribute("href","./infoCompte.html");
    bouton_compte.textContent = "Pseudo"; //recuperé du localstorage
    bouton_jeu.disabled = false;
}
zone_connection.appendChild(bouton_compte);
