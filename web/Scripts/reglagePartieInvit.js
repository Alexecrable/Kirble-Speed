

var tableauJoueurs = document.querySelector('#joueursCo');

var partielancee = false;
socket.addEventListener('message', function (event) {

    var infosmessage = event.data.split("/");
    console.log(event.data);
    if (event.data === "dejaCo"){
        
        document.querySelector('main').remove();
        
        
        alert("vous avez deja cette page ouverte dans un autre onglet");
        
    }
    if (infosmessage[1] !== "[]"){
        
    
        switch(infosmessage[0]){
            
            case "Sessionferme" : 
                alert("salon fermé");
                window.location = "http://localhost:8080/Projet_DWA/";
                break;
                
            case "connection" : ConnexionJoueurReglages(infosmessage[1]);
                break;
            case "fermeture" : FermetureJoueur(infosmessage[1]);
                break;
                
            case "HoteParti" :
                if(!partielancee){
                    alert("hoteparti");
                    window.location = "http://localhost:8080/Projet_DWA/";
                }
                
                break;
            case "recup" : 
          
                var infos = infosmessage[1].slice(1,-1);
                
                var tableau = infos.split(', ');
                
                
                for (let i =0; i < tableau.length; i++){
                    ConnexionJoueurReglages(tableau[i]);
                }
                break;
                
            case "LANCEMENT" : 
                partielancee = true;
                LancementPartie(infosmessage[1]);
               
                break;
            
        
        }
    }
});







