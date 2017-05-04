/**
 * 
 */
package agence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


import agence.model.Client;
import agence.model.ClientMoral;
import agence.model.ClientPhysique;



/**
 * @author Seme
 */
public class ClientMoralDaoSql extends ClientDaoSql //implements ClientDao
{
    public List<Client> findAll()
    {
        // Liste des clients que l'on va retourner
        List<Client> ListClients = new ArrayList<Client>();
        AdresseDaoSql adresseDAO = new AdresseDaoSql();
        LoginDaoSql loginDAO = new LoginDaoSql();

        try
        {

            /*
             * Connexion à la BDD
             */
            PreparedStatement ps = connexion.prepareStatement(
                    "SELECT * FROM client WHERE siret IS NOT NULL");

            // 4. Execution de la requête
            ResultSet tuple = ps.executeQuery();
            // 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
            // récupérer les valeutuple des colonnes du tuple qui correspondent
            // aux
            // valeur des attributs de l'objet
            while (tuple.next())
            {
                // Creation d'un objet Client
                Client objClient = new ClientMoral(tuple.getInt("idClient"));

                objClient.setNom(tuple.getString("nom"));
                objClient.setNumeroTel(tuple.getString("numTel"));
                objClient.setNumeroFax(tuple.getString("numFax"));
                objClient.setEmail(tuple.getString("eMail"));
                ((ClientMoral) objClient).setSiret(tuple.getLong("siret"));

                objClient
                        .setAdresse(adresseDAO.findById(tuple.getInt("idAdd")));
                objClient.setLogin(loginDAO.findById(tuple.getInt("idLog")));

                // Ajout du nouvel objet Client créé à la liste des clients
                ListClients.add(objClient);
            } // fin de la boucle de parcoutuple de l'ensemble des résultats

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // Retourne la liste de toutes les clients
        return ListClients;
    }

    @Override
    public Client findById(Integer idCli)
    {
        // Déclaration d'un objet Client
        Client objClient = null;
        AdresseDaoSql adresseDAO = new AdresseDaoSql();
        LoginDaoSql loginDAO = new LoginDaoSql();

        try
        {
            // Connexion à la BDD
            PreparedStatement ps = connexion.prepareStatement(
                    "SELECT * FROM client WHERE idClient=? AND siret IS NOT NULL");
            // Cherche l'idVill voulu dans la BDD
            ps.setInt(1, idCli);

            // Récupération des résultats de la requête
            ResultSet tuple = ps.executeQuery();

            if (tuple.next())
            {
                objClient = new ClientMoral(tuple.getInt("idClient"));
                objClient.setNom(tuple.getString("nom"));
                objClient.setNumeroTel(tuple.getString("numTel"));
                objClient.setNumeroFax(tuple.getString("numFax"));
                objClient.setEmail(tuple.getString("eMail"));
                ((ClientMoral) objClient).setSiret(tuple.getLong("siret"));

                objClient
                        .setAdresse(adresseDAO.findById(tuple.getInt("idAdd")));
                objClient.setLogin(loginDAO.findById(tuple.getInt("idLog")));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return objClient;
    }
   
    @Override
    public void create(ClientMoral clientMoral)
    {
    	 try
         {
             PreparedStatement ps = connexion
                     .prepareStatement("insert into client (idClient, nom, numTel, numFax, eMail, siret, idAdd) VALUES(?,?,?,?,?,?,?)");
             
             
             ps.setLong(1, clientMoral.getIdCli());
             ps.setString(2, clientMoral.getNom()); 
             ps.setString(3, clientMoral.getNumeroTel());
             ps.setString(4, clientMoral.getNumeroFax());
             ps.setString(5, clientMoral.getEmail());
             ps.setLong(6, clientMoral.getSiret());
             ps.setLong(7, 2L);
             ps.executeUpdate();
             

         }
         catch (SQLException e)
         {
             e.printStackTrace();
         }
         finally
         {/*
            try
             {
                connexion.close();
             }
             catch (SQLException e)
             {
                 e.printStackTrace();
             }*/
         }
    }
    
    @Override
    public ClientMoral update(ClientMoral clientMoral)
    {
    	try
        {
    		PreparedStatement ps = connexion.prepareStatement("update client set  nom=?, numTel=?, numFax=?, eMail=?, siret=?, idAdd=? WHERE idClient=? ");
    		
    		ps.setLong(7, clientMoral.getIdCli());
            ps.setString(2, clientMoral.getNom());
            ps.setString(3, clientMoral.getNumeroTel());
            ps.setString(4, clientMoral.getNumeroFax());
            ps.setString(5, clientMoral.getEmail());
            ps.setLong(6, clientMoral.getSiret());
            ps.setLong(7, 3L);
            

            ps.executeUpdate();

        }
        
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            /*try
            {
                connexion.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }*/
        }
    	return clientMoral;
    }
    
@Override
public void delete(ClientMoral clientMoral)
{
	try
    {
		PreparedStatement ps = connexion
        .prepareStatement("delete from client where id = ?");
        ps.setLong(1, clientMoral.getIdCli());

        ps.executeUpdate();

    }
    
    catch (SQLException e)
    {
        e.printStackTrace();
    }
    finally
    {
      /*  try
        {
            connexion.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }*/
    }
}

@Override
public void create(ClientPhysique clientPhysique) {
	// TODO Auto-generated method stub
	
}

@Override
public ClientPhysique update(ClientPhysique clientPhysique) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void delete(ClientPhysique clientPhysique) {
	// TODO Auto-generated method stub
	
}   

}
