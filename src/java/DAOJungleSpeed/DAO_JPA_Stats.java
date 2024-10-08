package DAOJungleSpeed;

import JeuClasses.JoueurPartieStat;
import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

//DAO Pour la classe/table JoueurPartieStatistique
public class DAO_JPA_Stats extends DAO<JoueurPartieStat> {

    @Override
    public JoueurPartieStat find(String id) throws DAOException {
        return null;
    }
    
    @Override
    public List<JoueurPartieStat> findAll() throws DAOException {
        return null;
    }
   

    @Override
    public void create(JoueurPartieStat stats) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROJODWAPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transac = em.getTransaction();
            transac.begin();
            em.persist(stats);
            transac.commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }

    @Override
    public void update(JoueurPartieStat stats) throws DAOException {
        try {
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROJODWAPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transac = em.getTransaction();
            transac.begin();
            stats = em.merge(stats);
            em.flush();
            transac.commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            throw new DAOException("Problèle technique (" + e.getMessage() + ")");
        }
    }

    @Override
    public void delete(JoueurPartieStat stats) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROJODWAPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transac = em.getTransaction();
            transac.begin(); 
            stats = em.merge(stats);
            em.remove(stats);
            transac.commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }

    public DAO_JPA_Stats() throws DAOException {
  
    }
}
