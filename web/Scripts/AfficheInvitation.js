function AfficheInvitation(hote){
    
    //****************************//
    //Crée une alerte d'invitation//
    //****************************//
    
    var zonematch = document.querySelector("#matchmaking");
    zonematch.style.display = "none";
    
    var main = document.querySelector("#main");
    
    var fenetre_notif = document.createElement("form");
    fenetre_notif.setAttribute("action","reglagepartie");
    fenetre_notif.setAttribute("method","post");
    
    var label = document.createElement("label");
    label.textContent = hote + " vous invite a rejoindre sa partie !";
    fenetre_notif.appendChild(label);
    
    
    var hoteParam = document.createElement("input");
    hoteParam.setAttribute("name","hote");
    hoteParam.value = hote;
    hoteParam.style.display = "none";
    fenetre_notif.appendChild(hoteParam);
    
    var roleParam = document.createElement("input");
    roleParam.setAttribute("name","role");
    roleParam.value = "Invit";
    roleParam.style.display = "none";
    fenetre_notif.appendChild(roleParam);
    
    var div_bouton = document.createElement("div");
    
    var bouton_submit = document.createElement("button");
    bouton_submit.setAttribute("type","submit");
    bouton_submit.textContent = "Rejoindre";
    div_bouton.appendChild(bouton_submit);
    
    var bouton_refus = document.createElement("button");
    bouton_refus.textContent = "Refuser";
    bouton_refus.addEventListener("click", function(){
        zonematch.style.display = "block";
        fenetre_notif.remove();
        
    });
    
    div_bouton.appendChild(bouton_refus);
    
    fenetre_notif.appendChild(div_bouton);
    
    
    main.appendChild(fenetre_notif);
    
    /*
    var resultat = window.confirm(hote + " vous invite a rejoindre sa partie !");
    if(resultat){
        window.location = "http://localhost:8080/Projet_DWA/reglagepartie?role=Invit&hote=" + hote;
    }
     * 
     */

}