


//
document.querySelector("#login").addEventListener("submit", function(event) {
    event.preventDefault(); 
    
    var pseudo = document.querySelector("#pseudo").value;
    var password = document.querySelector("#mdp").value;
 
    var socket = new WebSocket('ws://localhost:8080/Projet_DWA/CompteCreate');
    console.log("miaou");
    // Connection opened event listener
    socket.addEventListener('open', function (event) {
        // Send data to server
        socket.send(pseudo + "/" + password);
       
    });
    
    socket.addEventListener('message', function (event) {
    console.log('Message from server: ' + event.data);
    
    if(event.data.equals("Reussite")){
        
        localStorage.setItem("pseudo_courant",pseudo); //sauvegarde l'identité du mec pour naviguer le site

        
        //liste d'evenements pour afficher une reussite
        
        //revenir a la page d'accueil
        window.location = "http://localhost:9827/ProjetDWA/index.html";
        
    }
    
});

    // Error event listener
    socket.addEventListener('error', function (event) {
        console.error('WebSocket error: ', event);
    });
});