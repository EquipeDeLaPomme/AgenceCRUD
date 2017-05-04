package agence.dao;

import java.util.List;

import agence.model.ClientMoral;
import agence.model.ClientPhysique;

/**
 * Contrat que tous les DAOs devront respecter.
 * 
 * @author seme
 * @param <T>
 * @param <PK>
 */
public interface Dao<T, PK>
{
    /**
     * Retourne la liste de tous les objets métiers de la source de données
     * 
     * @return Liste des objets métiers de la source de données
     */
    List<T> findAll();

    /**
     * Retourne un objet métier en fonction de sa clé primaire
     * 
     * @param id
     *            Clé primaire
     * @return L'objet métier trouvé
     */
    T findById(PK id);
    
    /**
	 * Crée un nouvel objet métier afin de le persister
	 * @param obj L'objet à persister
	 * INSERT
	 */
	

	void create(ClientMoral clientMoral);

	ClientMoral update(ClientMoral clientMoral);

	void delete(ClientMoral clientMoral);

	void create(ClientPhysique clientPhysique);

	ClientPhysique update(ClientPhysique clientPhysique);

	void delete(ClientPhysique clientPhysique);

}
