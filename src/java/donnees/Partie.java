package donnees;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PARTIE")
@NamedQuery(name = "Partie.findAll", query = "SELECT p FROM Partie p")

public class Partie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUMPARTIE")
    private int numpartie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partie", fetch = FetchType.EAGER)
    private List<JoueurPartieStat> joueurPartieStatList;

    public Partie() {
    }

    public Partie(int numpartie) {
        this.numpartie = numpartie;
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

        for(JoueurPartieStat stat : joueurPartieStatList){
            retour += stat.toString();
        }
        return retour;
    }
    
}
