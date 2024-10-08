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
import java.util.Locale;



@ServerEndpoint(value = "/CompteCreate")
public class CreationCompte {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket opened: " + session.getId());
    }
//session.getBasicRemote().sendText("mauvais mdp");
   @OnMessage
    public void onMessage(String message, Session session) throws DAOException, IOException {
            
        JungleSpeedDAOFactory factory = new JungleSpeed_JPA_DAOFactory();
        String[] info_compte = message.split("/");
        DAO<Joueur> daoJoueurs = factory.getDAOJoueur();
        
        try{
            daoJoueurs.find(info_compte[1]);
        } catch (Exception e){
            session.getBasicRemote().sendText("Pseudo deja pris lol");
            return;
        }
        try{
            Joueur joueur = new Joueur();
            joueur.setPseudo(info_compte[0]);
            joueur.setMotdepasse(info_compte[1]);
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = formatter.parse(info_compte[2]);
            System.out.println("cringe");
            joueur.setDatedenaissance(date);
            joueur.setGenre(info_compte[3]);
            daoJoueurs.create(joueur);

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
