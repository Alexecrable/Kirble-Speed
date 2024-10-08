package WebSockets;

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



@ServerEndpoint(value="/reglagepartie/{hote}/{username}")
public class ReglagePartie {
    private String message;
    private String action;
    private Basic remote;
    private Basic remoteHote;
    private String role;
    private boolean partielancee = false;
    private static ArrayList<Basic> listeRemotes = new ArrayList<>();
    private static ArrayList<String> listeHotes = new ArrayList<>();
    
    private static ArrayList<Basic> remotesPH = new ArrayList<>();
    private static ArrayList<String> usersPH = new ArrayList<>();
    
    private ArrayList<Basic> joueursRemotes = new ArrayList<>();
    private ArrayList<String> joueursNoms = new ArrayList<>();
    private Boolean dejaCo = false;

    @OnOpen
    public void onOpen(
      Session session, 
      @PathParam("username") String username,
      @PathParam("hote") String hote)      throws IOException {
        
       
        
        this.remote = session.getBasicRemote();
        
        if(listeHotes.contains(username)){
            this.dejaCo = true;
            
            this.remote.sendText("dejaCo");
            session.close();
        }
        else{
            if(username.equals(hote)){
                this.role = "hote";
            
            
            
            ReglagePartie.listeRemotes.add(this.remote);
            ReglagePartie.listeHotes.add(username);
            }
            else{
                if(ReglagePartie.listeHotes.contains(hote)){
                     this.role = "invit";
                this.action = "connection";
                this.message = this.action + "/" + username;
                ReglagePartie.remotesPH.add(this.remote);
                ReglagePartie.usersPH.add(username);
                
                this.remoteHote = 
                        ReglagePartie.listeRemotes.get(
                                ReglagePartie.listeHotes.indexOf(hote)
                        );
                this.remoteHote.sendText(this.message);
                }
                else{
                    this.remote.sendText("Sessionferme");
                    
                }
               
                
               
            }
            
           
            
        }
        
    }

    @OnMessage
    public void onMessage(Session session, String message, @PathParam("username") String username) 
      throws IOException {
        
     
        String[] messageParam = message.split("/");
       
        
        
            
            switch(messageParam[0]){
                case "connection" : 
                    String pseudo = messageParam[1];
                    System.out.println("omg" + ReglagePartie.remotesPH + ReglagePartie.usersPH);
                    if(this.joueursNoms.contains(pseudo)){
                        ReglagePartie.remotesPH.get(ReglagePartie.usersPH.indexOf(pseudo)).sendText("dejaCo");
                        ReglagePartie.remotesPH.remove(ReglagePartie.usersPH.indexOf(pseudo));
                        ReglagePartie.usersPH.remove(pseudo);
                    }
                    else{
                        
                    ReglagePartie.remotesPH.get(ReglagePartie.usersPH.indexOf(pseudo)).sendText("recup/" + this.joueursNoms);

                    this.joueursRemotes.add(ReglagePartie.remotesPH.get(ReglagePartie.usersPH.indexOf(pseudo)));
                    
                    
                
                    this.joueursNoms.add(pseudo);

                    ReglagePartie.remotesPH.remove(ReglagePartie.usersPH.indexOf(pseudo));
                    ReglagePartie.usersPH.remove(pseudo);
                    
                    
                    broadcast(message);
                  
                    
               
                    }
                    break;
                
                case "fermeture" :
                    System.out.println("hihi" + message);
                    System.out.println(this.joueursRemotes);
                    this.joueursRemotes.remove(this.joueursNoms.indexOf(messageParam[1]));
                    
                    this.joueursNoms.remove(messageParam[1]);
                    System.out.println(this.joueursRemotes);
                    broadcast(message);
                    break;
                    
                case "LANCEMENT" : 
                    this.partielancee = true;
                    broadcast(message);
                    break;
                    
                default :
                    broadcast(message);
                    break;
                    
            }
            
            
            
        
        
       
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) throws IOException {
        if(!dejaCo){
            if(this.role.equals("hote")){
                ReglagePartie.listeRemotes.remove(this.remote);
                ReglagePartie.listeHotes.remove(username);
                this.action = "HoteParti";
                this.message = this.action;
                
                if(!this.partielancee){
                    broadcast(this.message);
                }
                
            }
            else{
                this.action = "fermeture";
                this.message = this.action + "/" + username;
                this.remoteHote.sendText(this.message);
            }
            
            
        }
        session.close();
        
    }

    
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Erreur WebSocket : " + throwable.getMessage());
    }

    private void broadcast(String message) {
      
      for(Basic ws : this.joueursRemotes){
          System.out.println("miaou");
          try{
              
             
                  ws.sendText(message);
              
              
          }
          catch (IOException ex) { 
              System.err.println("Erreur de communication"); 
          }
      }
        
    }
}