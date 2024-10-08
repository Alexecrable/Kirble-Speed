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
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;



@ServerEndpoint(value = "/CreationCompte")
public class CreationCompte {

    @OnOpen
    public void onOpen(Session session) {
        
    }
//session.getBasicRemote().sendText("mauvais mdp");
   @OnMessage
    public void onMessage(String message, Session session) throws DAOException, IOException {
            
        JungleSpeedDAOFactory factory = new JungleSpeed_JPA_DAOFactory();
        String[] info_compte = message.split("/");
        DAO<Joueur> daoJoueurs = factory.getDAOJoueur();
        
        
        try{
            Joueur joueur = new Joueur();
            joueur.setPseudo(info_compte[0]);
            joueur.setMotdepasse(info_compte[1]);
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = formatter.parse(info_compte[2]);
            
            joueur.setDatedenaissance(date);
            joueur.setGenre(info_compte[3]);
            daoJoueurs.create(joueur);
            session.getBasicRemote().sendText("Succes");

        } catch (Exception e) {
            session.getBasicRemote().sendText("Pseudo deja pris lol");
            e.printStackTrace();
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
