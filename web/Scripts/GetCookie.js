function GetCookie(id) {
    
    //**************************************************************************//
    //Renvoie la valeur associée au cookie demandé par rapport a son identifiant//
    //**************************************************************************//
    
    let listeCookie = document.cookie.split(";");
    
    for(let i = 0; i < listeCookie.length; i++) {
        
        let infoCookie = listeCookie[i].split("=");
        if(id === infoCookie[0].trim()) {
            return decodeURIComponent(infoCookie[1]);
        }
    }  
    return null;
}