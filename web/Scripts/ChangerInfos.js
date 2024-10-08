var changement = false;

function ChangerInfos(){
    
    //**************************************************************************//
    //Affiche un formulaire permettant de changer les informations du compte    //
    //**************************************************************************//
    
    if (!changement){
        changement = true;
        document.querySelector("#options>button").textContent = "Fermer";
        var options = document.querySelector("#options");
        
    
    var formulaire = document.createElement("form");
    
    var divmdp = document.createElement("div");
    var divnaissance = document.createElement("div");
    var divgenre = document.createElement("div");
    var divsubmit = document.createElement("div");
    
    
    var infos = document.createElement("label");
    infos.setAttribute("id","info");
    
    var labelmdp = document.createElement("label");
    labelmdp.textContent = "mdp";
    var labelnaissance = document.createElement("label");
    labelnaissance.textContent = "datenaissance";
    var labelgenre = document.createElement("label");
    labelgenre.textContent = "genre";
    
    var inputmdp = document.createElement("input");
    inputmdp.setAttribute("id","mdp");
    
    var inputnaissance = document.createElement("input");
    inputnaissance.setAttribute("id","age");
    inputnaissance.setAttribute("type","date");
    
    var inputgenre = document.createElement("select");
    inputgenre.setAttribute("id","genre");
    var optionHomme = document.createElement("option");
    var optionFemme = document.createElement("option");
    var optionAutre = document.createElement("option");
    
    optionHomme.setAttribute("value","Homme");
    optionHomme.textContent = "Homme";
    optionFemme.setAttribute("value","Femme");
    optionFemme.textContent = "Femme";
    optionAutre.setAttribute("value","Autre");
    optionAutre.textContent = "Autre";
    
    var buttonSubmit = document.createElement("button");
    buttonSubmit.setAttribute("type","submit");
    buttonSubmit.textContent = "Envoyer";
    var buttonReset = document.createElement("button");
    buttonReset.setAttribute("type","reset");
    buttonReset.textContent = "Reset";
    
    inputgenre.appendChild(optionHomme);
    inputgenre.appendChild(optionFemme);
    inputgenre.appendChild(optionAutre);
    
    divmdp.appendChild(labelmdp);
    divmdp.appendChild(inputmdp);
    
    divnaissance.appendChild(labelnaissance);
    divnaissance.appendChild(inputnaissance);
    
    divgenre.appendChild(labelgenre);
    divgenre.appendChild(inputgenre);
    
    divsubmit.appendChild(buttonSubmit);
    divsubmit.appendChild(buttonReset);
    
    formulaire.appendChild(divmdp);
    formulaire.appendChild(divnaissance);
    formulaire.appendChild(divgenre);
    formulaire.appendChild(divsubmit);
    formulaire.appendChild(infos);
    
    
    
    options.appendChild(formulaire);
    
    formulaire.addEventListener("submit", function(event) {
        event.preventDefault(); 
    
        var pseudo = GetCookie("pseudo_courant");
        var password = document.querySelector("#mdp").value;
        var dateDeNaissance = document.querySelector("#age").value;
        var genre = document.querySelector("#genre").value;
 
        var reponse = document.querySelector("#info");
        var socket = new WebSocket('ws://localhost:8080/Projet_DWA/ModifCompte');
    
   
        socket.addEventListener('open', function (event) {
            socket.send(pseudo + "/" + password + "/" + dateDeNaissance + "/" + genre);
        });
    
        socket.addEventListener('message', function (event) {
        console.log('Message from server: ' + event.data);
        reponse.textContent = event.data;
     
        if(event.data === "Succes"){
            formulaire.remove();
        }
      
        });

    
    });
    
    }
    else{
        changement = false;
        document.querySelector("#options>button").textContent = "Changer";
        document.querySelector("form").remove();
    }
    
    
    
    
}