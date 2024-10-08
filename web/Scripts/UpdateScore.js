function UpdateScore(pseudo,score,max){
    
    //****************************************************//
    //Créé une ligne dans le tableau en fonction du pseudo//
    //****************************************************//
    console.log(pseudo,score);
    
    
   if (score === max){
       document.querySelector("#"+pseudo).appendChild(couronne);
       
   }
   
  
   var scoreColonne = document.querySelector("#" + pseudo + "_Score");
   
   scoreColonne.value = parseInt(score);
   scoreColonne.textContent = scoreColonne.value;
}