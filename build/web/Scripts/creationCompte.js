


//
document.querySelector("#creationCompte").addEventListener("submit", function(event) {
    event.preventDefault(); 
    
    var pseudo = document.querySelector("#pseudo").value;
    var password = document.querySelector("#mdp").value;
    var dateDeNaissance = document.querySelector("#age").value;
    var genre = document.querySelector("#genre").value;
 
    var socket = new WebSocket('ws://localhost:8080/Projet_DWA/CompteCreate');
    console.log("miaou");
    // Connection opened event listener
    socket.addEventListener('open', function (event) {
        // Send data to server
        socket.send(pseudo + "/" + password + "/" + dateDeNaissance + "/" + genre);
        
       
    });
    
    socket.addEventListener('message', function (event) {
    console.log('Message from server: ' + event.data);
    
    if(event.data.equals("Succes")){
        //liste d'evenements pour afficher que c'est reussi 
        setTimeout(3000);
        window.location = "http://localhost:9827/ProjetDWA/login.html"
    }
    
});

    // Error event listener
    socket.addEventListener('error', function (event) {
        console.error('WebSocket error: ', event);
    });
});