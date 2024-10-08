function RecupServletPartieStat(numpartie) { 
    
    //*******************************************************//
    //Ouvre la servlet infojoueur avec le pseudo en parametre//
    //*******************************************************//
    
    var url = "http://localhost:8080/Projet_DWA/infostat?numpartie=" + numpartie;
    var win = window.open(url);
    win.focus();
                
    }