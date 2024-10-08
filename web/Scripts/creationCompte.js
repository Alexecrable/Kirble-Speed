
//******************************************************************************************//
//Recupere les données du formulaire pour les communiquer a une websocket et creer un compte//
//******************************************************************************************//
document.querySelector("#creationCompte").addEventListener("submit", function(event) {
    event.preventDefault(); 
    
    var pseudo = document.querySelector("#pseudo").value;
    var password = document.querySelector("#mdp").value;
    var dateDeNaissance = document.querySelector("#age").value;
    var genre = document.querySelector("#genre").value;
 
    var reponse = document.querySelector("#info");
    var socket = new WebSocket('ws://localhost:8080/Projet_DWA/CreationCompte');
    socket.addEventListener('open', function (event) {
        socket.send(pseudo + "/" + password + "/" + dateDeNaissance + "/" + genre);  
    });
    
    socket.addEventListener('message', function (event) {
        
        reponse.textContent = event.data;
    
        if(event.data === "Succes"){   
            //renvoie le client sur la page de login
            const myTimeout = setTimeout(()=>{window.location = "http://localhost:8080/Projet_DWA/login.html";}, 2000);    
        }
    });

    
});