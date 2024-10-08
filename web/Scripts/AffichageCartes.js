function AffichageCartes(JoueurCouleur, JoueurForme, MilieuCouleur, MilieuForme){
    
    //****************************************//
    //Affiche les cartes sur l'ecran du joueur//
    //****************************************//
   
    console.log(timer);
   choix = "timeout";
   choixfait = false;
   inaction = setTimeout(()=>{
       
           FaireChoix("timeout");

   },timer);
   
   
    
    
    CarteJ.src = "cartes/"+JoueurForme + "," + JoueurCouleur + ".jpg";
    CarteM.src = "cartes/"+MilieuForme + "," + MilieuCouleur + ".jpg";
    

    
    
    //reactive les boutons de choix
    choix_deux.disabled = false;
    choix_couleur.disabled = false;
    choix_forme.disabled = false;
    choix_aucun.disabled = false;
}