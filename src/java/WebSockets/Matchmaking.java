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



@ServerEndpoint(value="/matchmaking/{username}")
public class Matchmaking {
    private String message;
    private String action;
    private Basic remote;
    private static ArrayList<Basic> listeRemotes = new ArrayList<>();
    private static ArrayList<String> listeUtilisateurs = new ArrayList<>();
    private Boolean dejaCo = false;

    @OnOpen
    public void onOpen(
      Session session, 
      @PathParam("username") String username) throws IOException {
        
        
        System.out.println(username);
        System.out.println(Matchmaking.listeUtilisateurs);
        this.remote = session.getBasicRemote();
        
        if(listeUtilisateurs.contains(username)){
            
            this.dejaCo = true;
            
            this.remote.sendText("dejaCo");
            session.close();
        }
        else{
            this.remote.sendText("recup/" + listeUtilisateurs);
           
            Matchmaking.listeRemotes.add(this.remote);
            Matchmaking.listeUtilisateurs.add(username);
        
            this.action = "connection";
            this.message = this.action + "/" + username;
        
            broadcast(message);
        }
        
    }

    @OnMessage
    public void onMessage(Session session, String message, @PathParam("username") String username) 
      throws IOException {
        String[] testaction = message.split("/");
        
        
            String[] paramessage = message.split(",");
        ArrayList<Basic> notifRemotes = new ArrayList<Basic>();
        int index;
        for (int i = 1; i < paramessage.length; i++){
            index = listeUtilisateurs.indexOf(paramessage[i]);
            notifRemotes.add(listeRemotes.get(index));
        }
        this.message = notifRemotes.toString();
        
        envoiNotif(username, notifRemotes);
        
       
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) throws IOException {
        if(!dejaCo){
            Matchmaking.listeRemotes.remove(this.remote);
            Matchmaking.listeUtilisateurs.remove(username);
            this.action = "fermeture";
            this.message = this.action + "/" + username;
           
            broadcast(message);
        }
        
    }

    private void envoiNotif(String envoyeur, ArrayList<Basic> notifRemotes){
        
        
        
        for(Basic ws : notifRemotes){
          try{
            
              if(ws != this.remote){
                  ws.sendText("invitation/"+ envoyeur);
              }
              
          }
          catch (IOException ex) { 
              System.err.println("Erreur de communication"); 
          }
      }
    }
    
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Erreur WebSocket : " + throwable.getMessage());
    }

    private void broadcast(String message) {
        
      for(Basic ws : Matchmaking.listeRemotes){
          try{
             
              if(ws != this.remote){
                  ws.sendText(message);
              }
              
          }
          catch (IOException ex) { 
              System.err.println("Erreur de communication"); 
          }
      }
        
    }
}