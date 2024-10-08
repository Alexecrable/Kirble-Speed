package DAOJungleSpeed;



import JeuClasses.Partie;
import java.math.BigDecimal;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

//DAO Pour la classe/table Partie
public class DAO_JPA_Partie extends DAO<Partie> {




    @Override
    public Partie find(String id) throws DAOException {
       
        try {
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROJODWAPU");
            EntityManager em = emf.createEntityManager();
            Partie partie = em.find(Partie.class, Integer.parseInt(id));
            em.close();
            emf.close();
            if (partie != null) {  
                return partie;
            } else {
                throw new DAOException("Le joueur de pseudo t " + id + " n'existe pas");
            }
        } catch (Exception e) {
            throw new DAOException("Problème technique à find (" + e.getMessage() + ")");
        }
    }
    
    
    @Override
    public List<Partie> findAll() throws DAOException {
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROJODWAPU");
            EntityManager em = emf.createEntityManager();
            List<Partie> parties = em.createNamedQuery("Partie.findAll", Partie.class).getResultList();
            return parties;
        }
        catch (Exception e) {
            throw new DAOException("Problème technique à find (" + e.getMessage() + ")");
        }
    }
    
   

    @Override
    public void create(Partie partie) throws DAOException {
       
        try {
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROJODWAPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transac = em.getTransaction();
            transac.begin();
            em.persist(partie);
            transac.commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }

    @Override
    public void update(Partie partie) throws DAOException {
        try {
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROJODWAPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transac = em.getTransaction();
            transac.begin();
            partie = em.merge(partie);
            em.flush();
            transac.commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            throw new DAOException("Problèle technique (" + e.getMessage() + ")");
        }
    }

    @Override
    public void delete(Partie partie) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROJODWAPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transac = em.getTransaction();
            transac.begin();
            partie = em.merge(partie);
            em.remove(partie);
            transac.commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }

    public DAO_JPA_Partie() throws DAOException {
  
    }
}
