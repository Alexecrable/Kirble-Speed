package WebSockets;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import JeuClasses.Joueur;
import DAOJungleSpeed.DAO;
import DAOJungleSpeed.DAOException;
import DAOJungleSpeed.JungleSpeedDAOFactory;

import DAOJungleSpeed.JungleSpeed_JPA_DAOFactory;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;




@ServerEndpoint(value = "/Connexion")
public class Connexion {
   
   
    
    @OnOpen
    public void onOpen(Session session) {
       
        
    }
//session.getBasicRemote().sendText("mauvais mdp");
   @OnMessage
    public void onMessage(String message, Session session) {
            
        try {
            JungleSpeedDAOFactory factory = new JungleSpeed_JPA_DAOFactory();
            String[] info_connection = message.split("/");
            DAO<Joueur> daoJoueurs = factory.getDAOJoueur();
            
            
            
            
            
            
            Joueur joueur = daoJoueurs.find(info_connection[0]);
            
            
            
            if(joueur.getMotdepasse().equals(info_connection[1])){
                
                session.getBasicRemote().sendText("Succes");
            }
            else{
                session.getBasicRemote().sendText("Mauvais mot de passe");
            }
        } catch (DAOException | IOException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    @OnClose
    public void onClose(Session session) {
       
       
    }

    @OnError
    public void onError(Throwable throwable) {
        System.out.println("Erreur WebSocket : " + throwable.getMessage());
    }
}
