package donnees;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "JOUEUR")
@NamedQuery(name = "Joueur.findAll", query = "SELECT j FROM Joueur j")
 
public class Joueur implements Serializable {

    private static final long serialVersionUID = 1L; // à quoi sert ce truc 
    
    @Id
    @Basic(optional = false)
    @Column(name = "PSEUDO")
    private String pseudo;
    @Column(name = "MOTDEPASSE")
    private String motdepasse;
    @Column(name = "DATEDENAISSANCE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedenaissance;
    @Column(name = "GENRE")
    private String genre;
    @Column(name = "NBPARTIES")
    private int nbparties;
    @Column(name = "NBVICTOIRES")
    private int nbvictoires;
    @Column(name = "NBCLICS")
    private int nbclics;
    @Column(name = "NBCLICSREUSSIS")
    private int nbclicsreussis;
    @Column(name = "NBCLICSRAPIDES")
    private int nbclicsrapides;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "joueur", fetch = FetchType.EAGER)
    private List<JoueurPartieStat> joueurPartieStatList;

    public Joueur() {
    }

    public Joueur(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public Date getDatedenaissance() {
        return datedenaissance;
    }

    public void setDatedenaissance(Date datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNbparties() {
        return nbparties;
    }

    public void setNbparties(int nbparties) {
        this.nbparties = nbparties;
    }

    public int getNbvictoires() {
        return nbvictoires;
    }

    public void setNbvictoires(int nbvictoires) {
        this.nbvictoires = nbvictoires;
    }

    public int getNbclics() {
        return nbclics;
    }

    public void setNbclics(int nbclics) {
        this.nbclics = nbclics;
    }

    public int getNbclicsreussis() {
        return nbclicsreussis;
    }

    public void setNbclicsreussis(int nbclicsreussis) {
        this.nbclicsreussis = nbclicsreussis;
    }

    public int getNbclicsrapides() {
        return nbclicsrapides;
    }

    public void setNbclicsrapides(int nbclicsrapides) {
        this.nbclicsrapides = nbclicsrapides;
    }

    public List<JoueurPartieStat> getJoueurPartieStatList() {
        return joueurPartieStatList;
    }

    public void setJoueurPartieStatList(List<JoueurPartieStat> joueurPartieStatList) {
        this.joueurPartieStatList = joueurPartieStatList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pseudo != null ? pseudo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
       
        if (!(object instanceof Joueur)) {
            return false;
        }
        Joueur other = (Joueur) object;
        if ((this.pseudo == null && other.pseudo != null) || (this.pseudo != null && !this.pseudo.equals(other.pseudo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        float scoreMoyen = 0;
        for(JoueurPartieStat stat : joueurPartieStatList){
            scoreMoyen += stat.getPoints();
        }
        scoreMoyen = scoreMoyen / nbparties;
        
        String retour = "";
        if(nbparties > 0){
            return "pseudo=" + pseudo + "\n" +
               "dateDeNaissance=" + datedenaissance +"\n" +
                "genre=" + genre +"\n" +
                "nbParties=" + nbparties + "\n" +
                "nbVictoires=" + nbvictoires + "\n" +
                "ratioVictoire=" + nbvictoires/nbparties + "\n" +
                "scoreMoyen=" + scoreMoyen + "\n" +
                "ratioClicsreussi=" + nbclicsreussis / nbclics + "\n" +
                "ratioClicsRapide=" + nbclicsrapides / nbclics + "\n" +
                "nbclicrapide=" + nbclicsrapides + "\n"
                ;
        }
        else{
            return "pseudo=" + pseudo + "\n" +
               "dateDeNaissance=" + datedenaissance +"\n" +
                "genre=" + genre +"\n"
                ;
        }

        
    }
    
}
