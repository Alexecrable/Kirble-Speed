//permet d'envoyer la demande de connexion au serveur
document.querySelector("#login").addEventListener("submit", function(event) {
    event.preventDefault(); 
    var reponse = document.querySelector("#info");
    var pseudo = document.querySelector("#pseudo").value;
    var password = document.querySelector("#mdp").value;
 
    var socket = new WebSocket('ws://localhost:8080/Projet_DWA/Connexion');
    
    socket.addEventListener('open', function (event) {
        // Send data to server
        socket.send(pseudo + "/" + password);
       
    });
    
    socket.addEventListener('message', function (event) {
     
        reponse.textContent = event.data;
        if(event.data === "Succes"){
        
        
            //sauvegarde l'identité du client pour naviguer le site
            document.cookie = "pseudo_courant=" + pseudo ;
        
            //liste d'evenements pour afficher une reussite
            const myTimeout = setTimeout(()=>{window.location = "http://localhost:8080/Projet_DWA/index.html";}, 2000);
            //revenir a la page d'accueil
        
        
        }
    
    });

    
});