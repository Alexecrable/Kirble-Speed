package DAOJungleSpeed;

import JeuClasses.Joueur;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

//DAO Pour la classe/table Joueur
public class DAO_JPA_Joueur extends DAO<Joueur> {

    @Override
    public Joueur find(String id) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROJODWAPU");
            EntityManager em = emf.createEntityManager();
            Joueur joueur = em.find(Joueur.class, id);
            em.close();
            emf.close();
            if (joueur != null) {  
                return joueur;
            } else {
                throw new DAOException("Le joueur de pseudo " + id + " n'existe pas");
            }
        } catch (Exception e) {
            throw new DAOException("Problème technique à find (" + e.getMessage() + ")");
        }
    }
    
    
    @Override
    public List<Joueur> findAll() throws DAOException {
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROJODWAPU");
            EntityManager em = emf.createEntityManager();
            List<Joueur> joueurs = em.createNamedQuery("Joueur.findAll", Joueur.class).getResultList();
            return joueurs;
        }
        catch (Exception e) {
            throw new DAOException("Problème technique à find (" + e.getMessage() + ")");
        }
    }
   

    @Override
    public void create(Joueur joueur) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROJODWAPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transac = em.getTransaction();
            transac.begin();
            em.persist(joueur);
            transac.commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }

    @Override
    public void update(Joueur joueur) throws DAOException {
        try {
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROJODWAPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transac = em.getTransaction();
            transac.begin();
            joueur = em.merge(joueur);
            em.flush();
            transac.commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            throw new DAOException("Problèle technique (" + e.getMessage() + ")");
        }
    }

    @Override
    public void delete(Joueur joueur) throws DAOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROJODWAPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transac = em.getTransaction();
            transac.begin(); 
            joueur = em.merge(joueur);
            em.remove(joueur);
            transac.commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }

    public DAO_JPA_Joueur() throws DAOException {
  
    }
}
