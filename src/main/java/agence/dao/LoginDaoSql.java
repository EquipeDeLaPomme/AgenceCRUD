package agence.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agence.model.Login;

public class LoginDaoSql extends DaoSQL implements LoginDao
{

    @Override
    public List<Login> findAll()
    {
        // Liste des clients que l'on va retourner
        List<Login> ListLogin = new ArrayList<Login>();

        try
        {

            /*
             * Connexion à la BDD
             */
            PreparedStatement ps = connexion
                    .prepareStatement("SELECT * FROM login");

            // 4. Execution de la requête
            ResultSet tuple = ps.executeQuery();
            // 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
            // récupérer les valeutuple des colonnes du tuple qui correspondent
            // aux
            // valeur des attributs de l'objet
            while (tuple.next())
            {
                // Creation d'un objet Client
                Login objLogin = new Login(tuple.getInt("id"));

                objLogin.setLogin(tuple.getString("login"));
                objLogin.setMotDePasse(tuple.getString("motDePasse"));
                objLogin.setAdmin(tuple.getBoolean("admin"));

                // Ajout du nouvel objet Client créé à la liste des clients
                ListLogin.add(objLogin);
            } // fin de la boucle de parcoutuple de l'ensemble des résultats

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // Retourne la liste de toutes les clients
        return ListLogin;
    }

    @Override
    public Login findById(Integer id)
    {
        // Déclaration d'un objet Client
        Login objLogin = null;

        try
        {
            // Connexion à la BDD
            PreparedStatement ps = connexion
                    .prepareStatement("SELECT * FROM login WHERE id=?");
            // Cherche l'idVill voulu dans la BDD
            ps.setInt(1, id);

            // Récupération des résultats de la requête
            ResultSet tuple = ps.executeQuery();

            if (tuple.next())
            {
                objLogin = new Login(tuple.getInt("id"));
                objLogin.setLogin(tuple.getString("login"));
                objLogin.setMotDePasse(tuple.getString("motDePasse"));
                objLogin.setAdmin(tuple.getBoolean("admin"));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return objLogin;
    }
    
    
    
    /**
     * Permet de créer un Login dans la BDD
     * @param login
     */
    @Override
    public void create(Login login){
    	
	    try
	        {
	    	// Créer ma requête d'insertion INSERT INTO
	        PreparedStatement requete;
	
	        requete = connexion.prepareStatement(
	                "insert into login (id,login,motDePasse,admin)" + " VALUES(?,?,?,?)");
	        requete.setInt(1, login.getIdLog());	
	        requete.setString(2, login.getLogin());	
	        requete.setString(3, login.getMotDePasse());	        
	        requete.setBoolean(4, login.isAdmin());
	
	        // je l'exécute
	        requete.executeUpdate();
	
	    }
	    catch (SQLException e)
	    {
	        e.printStackTrace();
	    }
	    catch (NullPointerException e)
	    {
	        e.printStackTrace();
	    }
	    finally
	    { /*
	        try
	        {
	            connexion.close();
	        }
	        catch (SQLException e)
	        {
	            e.printStackTrace();
	        } */
	    }
	    	
	    }

    
    
    
	@Override
	public Login update(Login login) {
		try
        {
            PreparedStatement ps = connexion
                    .prepareStatement("update login set id=?,login=?,motDePasse=?,admin=?");

            ps.setLong(1, login.getIdLog());
            ps.setString(2, login.getLogin());
            ps.setString(3, login.getMotDePasse());
            ps.setBoolean(4, login.isAdmin());

            ps.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                connexion.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        return login;
	}

	
	
	
	@Override
	public void delete(Login login) {
		try
        {
            PreparedStatement ps = connexion.prepareStatement("delete from login where id = ?");
            ps.setLong(1, login.getIdLog());

            ps.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                connexion.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }		
	}
    }
