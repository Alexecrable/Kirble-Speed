

var tableauJoueurs = document.querySelector('#joueursCo');
var nbJoueurs = 1;
document.querySelector("#hote_ligne").textContent = GetCookie("pseudo_courant");

socket.addEventListener('message', function (event) {
    
    var infosmessage = event.data.split("/");
    
    if (event.data === "dejaCo"){
        
        document.querySelector('main').remove();
        
        
        alert("vous avez deja cette page ouverte dans un autre onglet");
        
    }
    if (infosmessage[1] !== "[]"){
        
    
        switch(infosmessage[0]){
            case "connection" : ConnexionJoueurReglages(infosmessage[1]);
                
                nbJoueurs++;
               
                if(nbJoueurs === 2){
                    document.querySelector("#submitter").disabled = false;
                }
                
                break;
            case "fermeture" : FermetureJoueur(infosmessage[1]);
                nbJoueurs--;
                if(nbJoueurs === 1){
                    document.querySelector("#submitter").disabled = true;
                }
                break;
             
        }
        socket.send(event.data);
    }
});



document.querySelector("#reglage").addEventListener("submit", function(event) {
    
    
    socket.send("LANCEMENT/"+GetCookie("pseudo_courant"));
    

    
});

document.querySelector("#timeslide").addEventListener("change",function(event){
    
    document.querySelector("#timespan").value = this.value;
});



document.querySelector("#timespan").addEventListener("change",function(event){
    
    document.querySelector("#timeslide").value = this.value;
});