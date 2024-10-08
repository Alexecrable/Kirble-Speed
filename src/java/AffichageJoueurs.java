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



@ServerEndpoint(value = "/AffichageJoueurs")
public class AffichageJoueurs {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket opened: " + session.getId());
    }
//session.getBasicRemote().sendText("mauvais mdp");
   @OnMessage
    public void onMessage(String message, Session session) throws DAOException, IOException {
            
        try{
            //Création DAOJoueur pour recuperer la liste des joueurs
            
            JungleSpeedDAOFactory factory = new JungleSpeed_JPA_DAOFactory();
            DAO<Joueur> daoJoueurs = factory.getDAOJoueur();
            List<Joueur> joueurs = daoJoueurs.findAll();
            
            //pour chaque joueur : Envoie ses infos a travers la websocket
            //afin que le client puisse afficher un tableau
            for (Joueur joueur : joueurs){
                session.getBasicRemote().sendText(joueur.toString());
            }
            
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
