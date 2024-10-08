package JeuClasses;

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
import java.util.ArrayList;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "joueur", fetch = FetchType.EAGER)
    private List<JoueurPartieStat> joueurPartieStatList = new ArrayList<>();

    
    
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

    

    

    public List<JoueurPartieStat> getJoueurPartieStatList() {
        return joueurPartieStatList;
    }
    
    public JoueurPartieStat getLastJoueurPartieStat() {
        
        return joueurPartieStatList.get(joueurPartieStatList.size() - 1);
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
        
        int nbvictoires = 0;
        int nbclics = 0;
        int nbclicsreussis = 0;
        int nbclicsrapides = 0;
        
        int nbparties = this.joueurPartieStatList.size();
        float scoreMoyen = 0;
        
        for(JoueurPartieStat stat : this.joueurPartieStatList){
            System.out.println(stat.getNbclicrapide());
            scoreMoyen += stat.getPoints();
            nbvictoires += stat.getVictoire();
            nbclics += stat.getNbclics();
            nbclicsreussis += stat.getNbclicreussi();
            nbclicsrapides += stat.getNbclicrapide();
        }
        scoreMoyen = scoreMoyen / nbparties;
        
        
        
        if(nbparties > 0){
            return "pseudo=" + pseudo + "<br>" +
               "dateDeNaissance=" + datedenaissance +"<br>" +
                "genre=" + genre +"<br>" +
                "nbParties=" + nbparties + "<br>" +
                "nbVictoires=" + nbvictoires + "<br>" +
                "ratioVictoire=" + (float)(nbvictoires/nbparties) + "<br>" +
                "scoreMoyen=" + scoreMoyen + "<br>" +
                "ratioClicsreussi=" + (float)nbclicsreussis / nbclics + "<br>" +
                "ratioClicsRapide=" + (float)nbclicsrapides / nbclics + "<br>" +
                "nbclicrapide=" + nbclicsrapides 
                ;
        }
        else{
            return "pseudo=" + pseudo + "<br>" +
               "dateDeNaissance=" + datedenaissance +"<br>" +
                "genre=" + genre +"<br>" +
                    "Toujours pas de partie....";
                
        }

        
    }
    
}
