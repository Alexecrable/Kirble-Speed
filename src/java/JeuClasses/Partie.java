package JeuClasses;

import DAOJungleSpeed.DAO;
import DAOJungleSpeed.DAOException;
import DAOJungleSpeed.JungleSpeedDAOFactory;
import DAOJungleSpeed.JungleSpeed_JPA_DAOFactory;
import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.ArrayList;
import java.util.Collections;

@Entity
@Table(name = "PARTIE")
@NamedQuery(name = "Partie.findAll", query = "SELECT p FROM Partie p")
@NamedQuery(name = "Partie.getLastNum", query = "SELECT MAX(p.numpartie) FROM Partie p")

public class Partie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUMPARTIE")
    private int numpartie;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partie", fetch = FetchType.EAGER)
    private List<JoueurPartieStat> joueurPartieStatList = new ArrayList<>();
    
    @Transient
    private List<Joueur> joueurs;
    @Transient
    private Paquet paquet;
    @Transient
    private ArrayList<Paquet> paquetsJoueur;
    
    @Transient
    private int nbJoueurs;
    @Transient
    private int nbJoueursFinRound;
    @Transient
    private int nbRounds;
    @Transient
    private int timer;
    @Transient
    private ArrayList<String> joueursNoms;
    
    @Transient
    private boolean rapideFait;
    
    
    
    public Partie() throws DAOException {
        this.joueursNoms = new ArrayList<>();
        this.paquetsJoueur = new ArrayList<Paquet>();
        this.joueurs = new ArrayList<>();
        
    }

    public Partie(int numpartie) throws DAOException {
        this.joueursNoms = new ArrayList<>();
        
        this.numpartie = numpartie;
        
        
        
        this.paquetsJoueur = new ArrayList<Paquet>();
        this.joueurs = new ArrayList<>();
        
        
        
    }
    
    public void Initialisation(int nbJoueurs, int nbRounds, int timer, int nbFormes, int nbCouleurs, boolean avance) throws DAOException{
        
        
        
        
        this.paquet = new Paquet(nbFormes,nbCouleurs);
        this.nbRounds = nbRounds;
        this.timer = timer;
        this.nbJoueurs = nbJoueurs;
        
        
        for (int i = 0; i < nbJoueurs; i++){
            Paquet paquetJoueur = new Paquet(new ArrayList<>(this.paquet.getCartes()));
            this.paquetsJoueur.add(paquetJoueur);
        }
        
        Collections.shuffle(this.paquet.getCartes());
    }
    
    public ArrayList<String> Round(){
      
       this.nbJoueursFinRound = 0;
       this.rapideFait = false;
       Carte CarteMilieu = this.paquet.getCarteDessus();
       ArrayList<String> listeReponses = new ArrayList<>();
       for(int i = 0; i < nbJoueurs; i++){
            Carte CarteJoueur = this.paquetsJoueur.get(i).getCarteDessus();
            listeReponses.add("Round/"+CarteJoueur.getCouleur()+"/"+CarteJoueur.getForme()+"/"+CarteMilieu.getCouleur()+"/"+CarteMilieu.getForme());
       }
       return listeReponses;
    }
    
    public void Deconnexion(String pseudo){
        this.nbJoueurs--;
        
        this.paquetsJoueur.remove(this.joueursNoms.indexOf(pseudo));
        
        this.joueurs.remove(this.joueursNoms.indexOf(pseudo));
        this.joueurPartieStatList.remove(this.joueursNoms.indexOf(pseudo));
        this.joueursNoms.remove(pseudo);
       
        
    }
    
    public synchronized void Choix(String decision, int indexJoueur){
       
        Carte carteMilieu = this.paquet.getCarteDessus();
        Carte carteJoueur = this.paquetsJoueur.get(indexJoueur).getCarteDessus();
        int points = 0;
        String milieuCouleur = carteMilieu.getCouleur();
        String milieuForme = carteMilieu.getForme();
        
        String joueurCouleur = carteJoueur.getCouleur();
        String joueurForme = carteJoueur.getForme();
        switch(decision){
            
            case "deux" :
                if (joueurCouleur.equals(milieuCouleur)){
                    if (joueurForme.equals(milieuForme)){ 
                    points = 2;
                    if(!rapideFait) points++;
                    rapideFait = true;
                    
                    
                    } 
                    else{
                        points = 1;
                        
                    }
                    this.paquetsJoueur.get(indexJoueur).retireCarte();
                
                }
                else{
                    if (joueurForme.equals(milieuForme)){
                        points = 1;
                        this.paquetsJoueur.get(indexJoueur).retireCarte();
                    }
                    else{
                        points = -1;
                    }
                    
                }
                
                break;
                
            case "aucun" :
              
                if (!joueurCouleur.equals(milieuCouleur)){
                    if (!joueurForme.equals(milieuForme)){ 
                        
                    points = 2;
                    if(!rapideFait) points++;
                    rapideFait = true;
                    
                    
                    } 
                    else{
                        points = 1;
                        
                    }
                    this.paquetsJoueur.get(indexJoueur).retireCarte();
                
                }
                else{
                    if (!joueurForme.equals(milieuForme)){
                        points = 1;
                        this.paquetsJoueur.get(indexJoueur).retireCarte();
                    }
                    else{
                        points = -1;
                    }
                    
                }
                break;
                
            case "couleur" :
                if (joueurCouleur.equals(milieuCouleur)){
                    if (!joueurForme.equals(milieuForme)){ 
                        
                    points = 2;
                    if(!rapideFait) points++;
                    rapideFait = true;
                    
                    
                    } 
                    else{
                        points = 1;
                        
                    }
                    this.paquetsJoueur.get(indexJoueur).retireCarte();
                
                }
                else{
                    points = -1;
                    
                }
                break;
                
            case "forme" :
                if (joueurForme.equals(milieuForme)){
                    if (!joueurCouleur.equals(milieuCouleur)){ 
                        
                    points = 2;
                    if(!rapideFait) points++;
                    rapideFait = true;
                    
                    
                    } 
                    else{
                        points = 1;
                        
                    }
                    this.paquetsJoueur.get(indexJoueur).retireCarte();
                
                }
                else{
                    points = -1;
                    
                }
                break;
        }
        
        if (points != 0) this.joueurs.get(indexJoueur).getLastJoueurPartieStat().addClic();
        this.joueurs.get(indexJoueur).getLastJoueurPartieStat().addPoints(points);
        this.nbJoueursFinRound++;
        
    }
    
    public void ConnectionJoueur(String pseudo) throws DAOException{
        
        
        
        JungleSpeedDAOFactory factory = new JungleSpeed_JPA_DAOFactory();
        DAO<Joueur> daoJoueurs = factory.getDAOJoueur();
        
        
        
        Joueur joueur = daoJoueurs.find(pseudo);
        JoueurPartieStat jps = new JoueurPartieStat(pseudo,this.numpartie);
        joueur.getJoueurPartieStatList().add(jps);
        this.getJoueurPartieStatList().add(jps);
        this.joueurs.add(joueur);
        this.joueursNoms.add(pseudo);
        
        
        
    }
    
    public String FinRound() throws DAOException{
        this.paquet.retireCarte();
        
        this.nbJoueursFinRound = 0;
        
        this.nbRounds--;
        String scores = "";
        
        int max = 0;
        boolean egalite = false;
        
        for(JoueurPartieStat jps : this.getJoueurPartieStatList()){
            scores += jps.getJoueurPartieStatPK().getPseudo() + "," + jps.getPoints() + "/";
            if (max < jps.getPoints()){
                egalite = false;
                max = jps.getPoints();
            }
            else{
                if(max == jps.getPoints()){
                    egalite = true;
                }
            }
        }
        
        if(this.nbRounds < 1 && !egalite){ //ajouter nbrounds
            
            this.FinPartie();
            scores ="FinPartie/" + scores;
            
        }
        else{
            scores = "FinRound/" + scores;
        }
        
        
        
        
        
        
        
        return scores;
    }
    
    public void FinPartie() throws DAOException{
        
        int max = 0;
        int indiceVainqueur = 0;
        for(Joueur joueur : this.joueurs){
            if (joueur.getLastJoueurPartieStat().getPoints() > max){
                max = joueur.getLastJoueurPartieStat().getPoints();
                indiceVainqueur = this.joueurs.indexOf(joueur);
            }
        }
        this.joueurs.get(indiceVainqueur).getLastJoueurPartieStat().setVictoire(1);
        
        
        
        JungleSpeedDAOFactory factory = new JungleSpeed_JPA_DAOFactory();
        DAO<Joueur> daoJoueur= factory.getDAOJoueur();
        DAO<Partie> daoPartie = factory.getDAOPartie();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROJODWAPU");
        EntityManager em = emf.createEntityManager();
        
        var testnull = em.createNamedQuery("Partie.getLastNum", int.class).getSingleResult();
        int numpartietest;
        if(testnull != null){
            numpartietest = testnull + 1;
        }
        else{
            numpartietest = 1;
        }
                
        if(numpartietest > this.numpartie){
            for(Joueur joueur : this.joueurs){
            joueur.getLastJoueurPartieStat().setJoueurPartieStatPK(new JoueurPartieStatPK(joueur.getPseudo(),numpartietest));
        }
        }
                    
        
        daoPartie.create(this);
        
        
        for(Joueur joueur : this.joueurs){
            daoJoueur.update(joueur);
        }
        
       
        
        

        
    }

    
    
    public int getNumpartie() {
        return numpartie;
    }

    public void setNumpartie(int numpartie) {
        this.numpartie = numpartie;
    }

    public List<JoueurPartieStat> getJoueurPartieStatList() {
        return joueurPartieStatList;
    }

    public void setJoueurPartieStatList(List<JoueurPartieStat> joueurPartieStatList) {
        this.joueurPartieStatList = joueurPartieStatList;
    }
    
    public int getNbJoueursFinRound(){
        return this.nbJoueursFinRound;
    }
    
    public int getNbJoueurs(){
        return this.nbJoueurs;
    }
    
    public List<Joueur> getJoueurs(){
        return this.joueurs;
    }
    //partie gestion de la logique du jeu

    @Override
    public int hashCode() {
        int hash = 31;
        hash = 31 * numpartie;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Partie)) {
            return false;
        }  
        Partie other = (Partie) object;
        return this.numpartie != other.numpartie;
    }

    @Override
    public String toString() {
        String retour = "";
        retour += this.numpartie + "//";

        for(JoueurPartieStat stat : joueurPartieStatList){
            retour += stat.toString();
        }
        return retour;
    }
    
}
