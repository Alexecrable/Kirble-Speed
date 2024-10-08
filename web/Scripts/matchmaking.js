var socket = new WebSocket('ws://localhost:8080/Projet_DWA/matchmaking/' + GetCookie("pseudo_courant"));

var tableauJoueurs = document.querySelector('#joueursCo');

socket.addEventListener('message', function (event) {
   
    var infosmessage = event.data.split("/");
    
    if (event.data === "dejaCo"){
        
        document.querySelector('#boutons').remove();
        document.querySelector('#joueursCo').remove();
        var main = document.querySelector('#main');
        var erreur = document.createElement("p");
        erreur.textContent = "vous avez deja cette page ouverte dans un autre onglet";
        main.appendChild(erreur);
        
        alert("vous avez deja cette page ouverte dans un autre onglet");
        
    }
    if (infosmessage[1] !== "[]"){
        
    
        switch(infosmessage[0]){
            case "connection" : ConnexionJoueur(infosmessage[1])
                break;
            case "fermeture" : FermetureJoueur(infosmessage[1])
                break;
            case "recup" : 
          
                var infos = infosmessage[1].slice(1,-1);
                
                var tableau = infos.split(', ');
                
                
                CreationTableauMatchmaking(tableau);
                break;
            case "invitation":
                AfficheInvitation(infosmessage[1]);
        
        }
    }
});







