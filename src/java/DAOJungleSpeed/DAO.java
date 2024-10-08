package DAOJungleSpeed;

import java.util.List;

public abstract class DAO<D> {
    /*
    Recupere un objet dans la base de données
    a partir de son identifiant
    */
    public abstract D find(String id) throws DAOException;
    
    /*
    Recupere toutes les iterations d'un certain type d'objet de la BDD
    et les stocke dans une liste
    */
    public abstract List<D> findAll() throws DAOException;
    
    /*
    Enregistre un objet dans la bdd
    */
    public abstract void create(D data) throws DAOException;
    
    
    /*
    met a jour l'objet dans la bdd
    */
    public abstract void update(D data) throws DAOException;
    
    /*
    Supprime un objet de la BDD
    */
    public abstract void delete(D data) throws DAOException;
    
    public void DAO() throws DAOException {
    }
}
