function LancementPartie(hote){
    
    //*******************************************//
    //Envoie l'invité sur le servlet de la partie//
    //*******************************************//
    
    var form = document.createElement("form");
    form.style.display = "none";
    form.setAttribute("action","JEU");
    form.setAttribute("method","post");
    
    var hoteParam = document.createElement("input");
    hoteParam.setAttribute("name","hote");
    hoteParam.value = hote;
    
    form.appendChild(hoteParam);
    
    var bouton_submit = document.createElement("button");
    bouton_submit.setAttribute("type","submit");
    
    form.appendChild(bouton_submit);
    
    document.querySelector("main").appendChild(form);
    setTimeout(()=>{ bouton_submit.click();}, 500);
   
    
    //window.location = "http://localhost:8080/Projet_DWA/JEU?hote=" + hote;
   

}