function RecupServletJoueur(pseudo) { 
    
    //*******************************************************//
    //Ouvre la servlet infojoueur avec le pseudo en parametre//
    //*******************************************************//
    
    var url = "http://localhost:8080/Projet_DWA/infojoueur?pseudo=" + pseudo;
    var win = window.open(url);
    win.focus();
                
    }