package JeuClasses;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class JoueurPartieStatPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "PSEUDO")
    private String pseudo;
    @Basic(optional = false)
    @Column(name = "NUMPARTIE")
    private int numpartie;

    public JoueurPartieStatPK() {
    }

    public JoueurPartieStatPK(String pseudo, int numpartie) {
        this.pseudo = pseudo;
        this.numpartie = numpartie;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getNumpartie() {
        return numpartie;
    }

    public void setNumpartie(int numpartie) {
        this.numpartie = numpartie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pseudo != null ? pseudo.hashCode() : 0);
        hash =  31*numpartie;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof JoueurPartieStatPK)) {
            return false;
        }
        JoueurPartieStatPK other = (JoueurPartieStatPK) object;
        if ((this.pseudo == null && other.pseudo != null) || (this.pseudo != null && !this.pseudo.equals(other.pseudo))) {
            return false;
        }
        if (this.numpartie == other.numpartie) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "donnees.JoueurPartieStatPK[ pseudo=" + pseudo + ", numpartie=" + numpartie + " ]";
    }
    
}
