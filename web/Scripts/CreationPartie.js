function CreationPartie(listepseudos){
    
    //*****************************//
    //Prend les joueurs choisis    //
    //et les invite pour la partie //
    //*****************************//
    
    var test = document.querySelectorAll('input[name=checkBox]:checked');
    var listePseudos = [];
    
    for(let i = 0; i < test.length; i++){
        listePseudos[i] = test[i].id.slice(0,-3); //recupere l'id de la checkbox en lui enlevant le "_CB"
    }
    
    //envoi notif aux joueurs
    socket.send("Invitation,"+listePseudos);
    
    
    var form = document.createElement("form");
    form.style.display = "none";
    form.setAttribute("action","reglagepartie");
    form.setAttribute("method","post");
    
    var roleParam = document.createElement("input");
    roleParam.setAttribute("name","role");
    roleParam.value = "Hote";
    
    form.appendChild(roleParam);
    
    var bouton_submit = document.createElement("button");
    bouton_submit.setAttribute("type","submit");
    
    form.appendChild(bouton_submit);
    
    document.querySelector("main").appendChild(form);
    bouton_submit.click();
    //deplacement dans la page de reglages en attendant les joueurs
    //window.location = "http://localhost:8080/Projet_DWA/reglagepartie?role=Hote";
    
}