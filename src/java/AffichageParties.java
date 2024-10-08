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



@ServerEndpoint(value = "/AffichageParties")
public class AffichageParties {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket opened: " + session.getId());
    }

   @OnMessage
    public void onMessage(String message, Session session) throws DAOException, IOException {
            
        
        
        try{
            JungleSpeedDAOFactory factory = new JungleSpeed_JPA_DAOFactory();
            DAO<Partie> daoParties = factory.getDAOPartie();
            List<Partie> parties = daoParties.findAll();
            
            for (Partie partie : parties){
                session.getBasicRemote().sendText(partie.toString());
            }
            /*
            faire ça ou voir si je fais passer tt le bloc d'un coup si possible
            en mode
            session.getBasicRemote().sendText(parties.toString());
            */
            
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
