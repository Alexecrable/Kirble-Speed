var inaction;
var Milieu = document.querySelector("#Milieu");
var Joueur = document.querySelector("#Joueur");


var choix_deux = document.querySelector("#deux");


var choix_couleur = document.querySelector("#couleur");


var choix_forme = document.querySelector("#forme");

var choix_aucun = document.querySelector("#aucun");

var choix_fait;

var choix;

var CarteJ = document.createElement("img");
CarteJ.width = 60;
CarteJ.height = 120;
   
var CarteM = document.createElement("img");
CarteM.width = 60;
CarteJ.height = 120;
   
Joueur.appendChild(CarteJ);
    
    
    
Milieu.appendChild(CarteM);

var couronne = document.createElement("td");
couronne.setAttribute("class","couronne");

var couronneimg = document.createElement("img");
    couronneimg.src = "images/kirbcrown.png";
    couronneimg.setAttribute("style","width : 50px; height 50px;");
   
  couronne.appendChild(couronneimg);
   
   
    
    
var tableauScores = document.querySelector("#scores");


var timer;


ConnexionJoueurJeu(GetCookie("pseudo_courant"));

socket.addEventListener('message', function (event) {
    console.log(event.data);
    var infosmessage = event.data.split("/");
    
    if (event.data === "dejaCo"){
        
        document.querySelector('main').remove();
        
        
        alert("vous avez deja cette page ouverte dans un autre onglet");
        
    }
    if (infosmessage[1] !== "[]"){
        
        
        
        switch(infosmessage[0]){
            
            case "Annulation" :
                alert(infosmessage[1]);
                window.location = "http://localhost:8080/Projet_DWA/";
            
            case "timer" :
                timer = parseInt(infosmessage[1]);
                break;
            
            case "PartieInit" :
                
                setTimeout(() => {document.querySelector("#bouton_lance").click();}, 2000);
                break
                
            
            case "Round" : 
                
                setTimeout(()=>{AffichageCartes(infosmessage[1],infosmessage[2],infosmessage[3],infosmessage[4]);},2000);
                break;
                
            case "connection": ConnexionJoueurJeu(infosmessage[1]);
                break;
            
            case "FinRound" :
                
                var listecour = document.querySelectorAll(".couronne");
   console.log(listecour);
   for(let i =0; i < listecour.length; i++){
       listecour[i].remove();
   }
                
                var max = 0;
                

                for(let i = 1; i < infosmessage.length - 1 ; i++){
                    var scoreUpdate = infosmessage[i].split(",");
                    if (parseInt(scoreUpdate[1]) > max){
                        max = scoreUpdate[1];
                    }
                    UpdateScore(scoreUpdate[0],scoreUpdate[1],max);
                }
                break;
                
            case "FinPartie" :
                
                var max = 0;
                var Vainqueur;
                for(let i = 1; i < infosmessage.length - 1 ; i++){
                    var scoreUpdate = infosmessage[i].split(",");
                    if (parseInt(scoreUpdate[1]) > max){
                        max = scoreUpdate[1];
                        Vainqueur = scoreUpdate[0];
                    }
                    UpdateScore(scoreUpdate[0],scoreUpdate[1],max);
                }
                FinPartie(Vainqueur);
                break;
                
            case "fermeture" : FermetureJoueur(infosmessage[1]);
                
                break;
                
            default :
                
                break;           
                
            
             
        }
        socket.send(event.data);
    }
});






