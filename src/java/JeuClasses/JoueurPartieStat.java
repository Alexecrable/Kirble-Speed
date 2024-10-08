package JeuClasses;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "JOUEUR_PARTIE_STAT")

public class JoueurPartieStat implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JoueurPartieStatPK joueurPartieStatPK;
    @Column(name = "POINTS")
    private int points;
    @Column(name = "NBCLICS")
    private int nbclics;
    @Column(name = "NBCLICREUSSI")
    private int nbclicreussi;
    @Column(name = "NBCLICRAPIDE")
    private int nbclicrapide;
    @Column(name = "VICTOIRE")
    private int victoire;
    @JoinColumn(name = "PSEUDO", referencedColumnName = "PSEUDO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Joueur joueur;
    @JoinColumn(name = "NUMPARTIE", referencedColumnName = "NUMPARTIE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Partie partie;

    public JoueurPartieStat() {
   
    }

    public JoueurPartieStat(JoueurPartieStatPK joueurPartieStatPK) {
        this.joueurPartieStatPK = joueurPartieStatPK;
    }

    public JoueurPartieStat(String pseudo, int numpartie) {
        this.joueurPartieStatPK = new JoueurPartieStatPK(pseudo, numpartie);
        this.points = 0;
        this.nbclicreussi = 0;
        this.nbclicrapide = 0;
        this.nbclics = 0;
        this.victoire = 0;
    }

    public JoueurPartieStatPK getJoueurPartieStatPK() {
        return joueurPartieStatPK;
    }

    public void setJoueurPartieStatPK(JoueurPartieStatPK joueurPartieStatPK) {
        this.joueurPartieStatPK = joueurPartieStatPK;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getNbclicreussi() {
        return nbclicreussi;
    }

    public void setNbclicreussi(int nbclicreussi) {
        this.nbclicreussi = nbclicreussi;
    }

    public int getNbclicrapide() {
        return nbclicrapide;
    }

    public void setNbclicrapide(int nbclicrapide) {
        this.nbclicrapide = nbclicrapide;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
    
    public int getNbclics(){
        return this.nbclics;
    }
    
    public void setNbclics(int nbclics){
        this.nbclics = nbclics;
    }
    
    public int getVictoire(){
        return this.victoire;
    }
    
    public void setVictoire(int victoire){
        this.victoire = victoire;
    }
    
    public void addClic(){
        this.nbclics++;
    }
    
    public void addPoints(int point){
        
        if(point > 1){
            this.nbclicreussi++;
        }
        if(point == 3){
            this.nbclicrapide++;
        }
        
        this.points = this.points + point;
         
        if (this.points < 0) this.points = 0;
       
        
    }
    
   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (joueurPartieStatPK != null ? joueurPartieStatPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
       
        if (!(object instanceof JoueurPartieStat)) {
            return false;
        }
        JoueurPartieStat other = (JoueurPartieStat) object;
        if ((this.joueurPartieStatPK == null && other.joueurPartieStatPK != null) || (this.joueurPartieStatPK != null && !this.joueurPartieStatPK.equals(other.joueurPartieStatPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return 
        joueurPartieStatPK.getPseudo() + "<br>" +
        "score :" + points + "<br>" +
        "nbclicsreussis :" + nbclicreussi + "<br>" +
        "nbclicsrapides :" + nbclicrapide + "<br>";
    }
    
}
