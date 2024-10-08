package DAOJungleSpeed;

import JeuClasses.Joueur;
import JeuClasses.Partie;
import JeuClasses.JoueurPartieStat;

public class JungleSpeed_JPA_DAOFactory extends JungleSpeedDAOFactory {
    
    private DAO_JPA_Joueur daoJoueur = null;
    private DAO_JPA_Partie daoPartie = null;
    private DAO_JPA_Stats daoStats = null;

    @Override
    public DAO<Joueur> getDAOJoueur() throws DAOException {
        if (daoJoueur == null) daoJoueur = new DAO_JPA_Joueur();
        return daoJoueur;
    }
    
    @Override
    public DAO<Partie> getDAOPartie() throws DAOException {
        if (daoPartie == null) daoPartie = new DAO_JPA_Partie();
        return daoPartie;
    }
    
    @Override
    public DAO<JoueurPartieStat> getDAOStats() throws DAOException {
        if (daoStats == null) daoStats = new DAO_JPA_Stats();
        return daoStats;
    }  
    
}
