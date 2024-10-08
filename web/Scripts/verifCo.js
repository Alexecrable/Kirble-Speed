

if (document.title === "Inscription" && !(GetCookie("pseudo_courant") === null) ){
    window.location = "http://localhost:8080/Projet_DWA/";
}

if ((document.title === "Matchmaking" || document.title === "InfoCompte") && (GetCookie("pseudo_courant") === null) ){
    window.location = "http://localhost:8080/Projet_DWA/";
}

var bouton_jeu = document.querySelector("#bouton_jeu");


var zone_connection = document.querySelector("#Compte");


var bouton_compte = document.createElement("a");

//generation html pour changer l'accueil dans la version connectée
if(GetCookie("pseudo_courant") === null){
    
    bouton_compte.setAttribute("href","./login.html");
    bouton_compte.textContent = "Connexion";
    bouton_jeu.disabled = true;
    
}
else{//generation html pour changer l'accueil dans la version invitée
    bouton_compte.setAttribute("href","./infoCompte.html");
    var pseudo = GetCookie("pseudo_courant");
    bouton_compte.textContent = pseudo; //recuperé du 
    bouton_jeu.disabled = false;
    
}
zone_connection.appendChild(bouton_compte);
