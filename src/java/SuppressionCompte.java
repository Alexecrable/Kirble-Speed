import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import donnees.Joueur;
import DAOJungleSpeed.DAO;
import DAOJungleSpeed.DAOException;
import DAOJungleSpeed.JungleSpeedDAOFactory;
import jakarta.persistence.*;
import DAOJungleSpeed.JungleSpeed_JPA_DAOFactory;

import donnees.*;
import DAOJungleSpeed.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;



@ServerEndpoint(value = "/SuppressionCompte")
public class SuppressionCompte {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket opened: " + session.getId());
    }

   @OnMessage
    public void onMessage(String message, Session session) throws DAOException, IOException {
            
        
        
        try{
            
            //verifier si le joueur existe ???
            //pas forcement car on va d'abord passer
            //par un proc de verification avant de faire confirmer la suppression au joueur
            //a mediter
            
            JungleSpeedDAOFactory factory = new JungleSpeed_JPA_DAOFactory();
            DAO<Joueur> daoJoueurs = factory.getDAOJoueur();
            daoJoueurs.delete(daoJoueurs.find(message));
            session.getBasicRemote().sendText("Suppression reussie");
          
            
        } catch (Exception e) {
            session.getBasicRemote().sendText("autre probleme");
            e.printStackTrace();
        }
    }
    
    
    @OnClose
    public void onClose(Session session) {
        System.out.println("WebSocket closed: " + session.getId());
    }

    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }
}
