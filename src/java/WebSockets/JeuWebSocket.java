package WebSockets;


import JeuClasses.Partie;


import DAOJungleSpeed.DAOException;


import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.RemoteEndpoint.Basic;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.server.PathParam;

import java.io.IOException;

import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;



@ServerEndpoint(value="/jeu/{hote}/{username}")
public class JeuWebSocket {
    private String message;
    private String action;
    private Basic remote;
    private Basic remoteHote;
    private String role;
    
    
    
    private static ArrayList<Basic> listeRemotes = new ArrayList<>();
    private static ArrayList<String> listeHotes = new ArrayList<>();
    
    private static ArrayList<Basic> remotesPH = new ArrayList<>();
    private static ArrayList<String> usersPH = new ArrayList<>();
    
    private ArrayList<Basic> joueursRemotes = new ArrayList<>();
    private ArrayList<String> joueursNoms = new ArrayList<>();
    private Boolean dejaCo = false;
    
    private Partie partie;
  
   
   

    @OnOpen
    public synchronized void onOpen(
      Session session, 
      @PathParam("username") String username,
      @PathParam("hote") String hote){
       
      
        
        this.remote = session.getBasicRemote();
        
        if(listeHotes.contains(username)){
            
            try {
                this.dejaCo = true;
                
                this.remote.sendText("dejaCo");
                session.close();
            } catch (IOException ex) {
                Logger.getLogger(JeuWebSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            if(username.equals(hote)){
                System.out.println("verif hotes" + JeuWebSocket.listeHotes);
                try {
                    
                    this.role = "hote";
                    this.joueursRemotes.add(this.remote);
                    this.joueursNoms.add(username);
                    
                    JeuWebSocket.listeRemotes.add(this.remote);
                    JeuWebSocket.listeHotes.add(username);
                    
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROJODWAPU");
                    EntityManager em = emf.createEntityManager();
                    
                    var testnull = em.createNamedQuery("Partie.getLastNum", int.class).getSingleResult();
                    int numpartie;
                    if(testnull != null){
                        numpartie = testnull + 1;
                    }
                    else{
                        numpartie = 1;
                    }
                      
                    
                    this.partie = new Partie(numpartie);
                    this.partie.ConnectionJoueur(username);
                    this.remote.sendText("PartieInit");
                   
                } catch (DAOException | IOException ex) {
                    Logger.getLogger(JeuWebSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                
                
       
            }
            else{
              
                try {
                    this.role = "invit";
                    this.action = "connection";
                    this.message = this.action + "/" + username;
                    JeuWebSocket.remotesPH.add(this.remote);
                    JeuWebSocket.usersPH.add(username);
                    
                    
                    
                    this.remoteHote =
                            JeuWebSocket.listeRemotes.get(JeuWebSocket.listeHotes.indexOf(hote)
                            );
                  
                    this.remoteHote.sendText(this.message);
                } catch (IOException ex) {
                    Logger.getLogger(JeuWebSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
            
            
            
            
        }
        
    }

    @OnMessage
    public void onMessage(Session session, String message, @PathParam("username") String username) throws IOException
    {
     
        
        
        String[] messageParam = message.split("/");
        int index;
        if(role.equals("invit")){
            try {
                
                this.remoteHote.sendText(message);
            } catch (IOException ex) {
                Logger.getLogger(JeuWebSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
          
           
            switch(messageParam[0]){
                
                case "fermeture" :
                 
                    this.partie.Deconnexion(messageParam[1]);
                    
                    this.joueursRemotes.remove(this.joueursNoms.indexOf(messageParam[1]));
                    this.joueursNoms.remove(messageParam[1]);
                   
                    broadcast(message);
                    if(this.joueursNoms.size() < 2){
                        this.remote.sendText("Annulation/Tout le monde est parti :'(");
                    }
                    break;
                
                case "connection" : 
                    
                    
                    
                    String pseudo = messageParam[1];
                    
                    if(this.joueursNoms.contains(pseudo)){
                      
                        JeuWebSocket.remotesPH.get(JeuWebSocket.usersPH.indexOf(pseudo)).sendText("dejaCo");
                        JeuWebSocket.remotesPH.remove(JeuWebSocket.usersPH.indexOf(pseudo));
                        JeuWebSocket.usersPH.remove(pseudo);
                    }
                    else{
                        
                    
                try {
                   
                    this.joueursRemotes.add(JeuWebSocket.remotesPH.get(JeuWebSocket.usersPH.indexOf(pseudo)));
                    
                    JeuWebSocket.remotesPH.get(JeuWebSocket.usersPH.indexOf(pseudo)).sendText("recup/" + this.joueursNoms);
                    
                
                    this.joueursNoms.add(pseudo);

                    JeuWebSocket.remotesPH.remove(JeuWebSocket.usersPH.indexOf(pseudo));
                    JeuWebSocket.usersPH.remove(pseudo);
                    
                    
                    this.partie.ConnectionJoueur(pseudo);
                
                   
                    broadcast(message);
                } catch (DAOException ex) {
                    Logger.getLogger(JeuWebSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }
                    break;


                
                case "Initialisation" : 
                
                    int nbFormes = Integer.parseInt(messageParam[3]);
                    int nbCouleurs = Integer.parseInt(messageParam[4]);
                    int nbRounds = Integer.parseInt(messageParam[1]);
                    int timer = Integer.parseInt(messageParam[2]);
                    int nbJoueurs = this.joueursNoms.size();
                    boolean avance = (messageParam[5].equals("true"));
                    
                    if(nbJoueurs < 2){
                        this.remote.sendText("Annulation/Pas de Joueurs :'(");
                    }
                    else{
                        
                    
        
                {
                    try {
                        this.partie.Initialisation(nbJoueurs, nbRounds, timer, nbFormes, nbCouleurs, avance);
                        //this.partielancee = true;
                    } catch (DAOException ex) {
                        Logger.getLogger(JeuWebSocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    
                {
                    try {
                        this.remote.sendText("timer/" + messageParam[2]);
                    } catch (IOException ex) {
                        Logger.getLogger(JeuWebSocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    broadcast("timer/" + messageParam[2]);
                {
                    try {
                        this.remote.sendText("DebutRound");
                    } catch (IOException ex) {
                        Logger.getLogger(JeuWebSocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    }
                    
                
                    break;



                
            
                case "DebutRound" :
                    
                    ArrayList<String> reponses = this.partie.Round();
                    
                    for(int i = 0; i < reponses.size(); i++){
                       
                    
                    Basic remoteJoueur = this.joueursRemotes.get(i);
                    remoteJoueur.sendText(reponses.get(i));
                
            
                    }
                    break;
                    
                
                
                case "choix" : 
                    
                    index = this.joueursNoms.indexOf(messageParam[2]);
                    this.partie.Choix(messageParam[1], index);
                    
                    if(this.joueursNoms.size() == this.partie.getNbJoueursFinRound()){
                        
                        
                        try{
                            
                            
                            String scores = this.partie.FinRound();
                            System.out.println(scores);
                            try {
                                this.remote.sendText(scores);
                            } catch (IOException ex) {
                                Logger.getLogger(JeuWebSocket.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            broadcast(scores);
                            try{
                                if(!scores.contains("FinPartie")){
                                    this.remote.sendText("DebutRound");
                                }
                            }
                            catch(IOException e){
                                e.printStackTrace();
                            }
                            
                            
                        }
                        catch(DAOException ex){
                            Logger.getLogger(JeuWebSocket.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                    }
                   
                
                    break;
            
               
            }
        }
            
            //broadcast(message);
        
        
       
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username){
        if(!dejaCo){
            if(this.role.equals("hote")){
                JeuWebSocket.listeRemotes.remove(this.remote);
                JeuWebSocket.listeHotes.remove(username);
                
                
                
                broadcast("HoteParti");
                if(session.isOpen()){
                    try {
                        session.close();
                    } catch (IOException ex) {
                        Logger.getLogger(JeuWebSocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            else{
                try {
                    this.action = "fermeture";
                    this.message = this.action + "/" + username;
                    this.remoteHote.sendText(this.message);
                    JeuWebSocket.remotesPH.remove(JeuWebSocket.usersPH.indexOf(username));
                    JeuWebSocket.usersPH.remove(username);
                    
                } catch (IOException ex) {
                    Logger.getLogger(JeuWebSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        }
        try {
            session.close();
        } catch (IOException ex) {
            Logger.getLogger(JeuWebSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    @OnError
    public void onError(Session session, Throwable throwable) {
        
    }

    private void broadcast(String message) {
        
   
      for(Basic ws : this.joueursRemotes){
          try{
              if(!ws.equals(this.remote)){
                  ws.sendText(message);
              }
              
              
              
                
              
              
          }
          catch (IOException ex) { 
              System.err.println("Erreur de communication"); 
          }
      }
        
    }
}