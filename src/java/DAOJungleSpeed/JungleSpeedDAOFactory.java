package DAOJungleSpeed;

import JeuClasses.Joueur;
import JeuClasses.Partie;
import JeuClasses.JoueurPartieStat;



public abstract class JungleSpeedDAOFactory {
   
    public abstract DAO<Joueur> getDAOJoueur() throws DAOException;

    public abstract DAO<Partie> getDAOPartie() throws DAOException;
   
    public abstract DAO<JoueurPartieStat> getDAOStats() throws DAOException;
}
