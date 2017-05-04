/**
 * 
 */
package agence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import agence.model.Adresse;
import agence.model.Passager;
import agence.model.Reservation;

/**
 * @author Seme
 */
public class PassagerDaoSql extends DaoSQL implements PassagerDao
{
    AdresseDao adresseDao = new AdresseDaoSql();
    ReservationDao reservationDao = new ReservationDaoSql();
    /*
     * (non-Javadoc)
     * @see agence.dao.Dao#findAll()
     */
    @Override
    public List<Passager> findAll()
    {
        // Initialiser ma liste de passagers
        List<Passager> listePassagers = new ArrayList<>();
        try
        {
            /*
             * Etape 2 : Création du statement
             */
            Statement statement = connexion.createStatement();

            /*
             * Etape 3 : Exécution de la requête SQL
             */
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM passager");

            /*
             * Etape 4 : Parcours des résultats
             */
            while (resultSet.next())
            {
                // Chaque ligne du tableau de résultat peut être exploitée
                // cad, on va récupérer chaque valeur de chaque colonne
                // je crée l'objet passager
                Passager passager = new Passager();
                // appel des mutateurs
                passager.setIdPas(resultSet.getInt("idPassager"));
                passager.setNom(resultSet.getString("nom"));
                passager.setPrenom(resultSet.getString("prenom"));
                passager.setAdresse(
                        adresseDao.findById(resultSet.getInt("idAdd")));
                // j'ajoute l'objet passager ainsi muté à la liste des passagers
                listePassagers.add(passager);
            }

        }
        catch (SQLException e)
        {
            System.err.println("Impossible de se connecter à la BDD.");
            e.printStackTrace();
        }
        // Je retourne la liste des passagers de la BDDonnéys
        return listePassagers;
    }

    /*
     * (non-Javadoc)
     * @see agence.dao.Dao#findById(java.lang.Object)
     */
    @Override
    public Passager findById(Integer id)
    {
        // Initialiser mon passager
        Passager passager = null;
        try
        {
            /*
             * Etape 2 : Création du statement
             */
            Statement statement = connexion.createStatement();

            /*
             * Etape 3 : Exécution de la requête SQL
             */
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM passager WHERE idPassager = " + id);

            /*
             * Etape 4 : Parcours des résultats
             */
            if (resultSet.next())
            {
                // Chaque ligne du tableau de résultat peut être exploitée
                // cad, on va récupérer chaque valeur de chaque colonne
                // je crée l'objet métier
                passager = new Passager();
                // appel des mutateurs
                passager.setIdPas(resultSet.getInt("idPassager"));
                passager.setNom(resultSet.getString("nom"));
                passager.setPrenom(resultSet.getString("prenom"));
                passager.setAdresse(
                        adresseDao.findById(resultSet.getInt("idAdd")));
            }

        }
        catch (SQLException e)
        {
            System.err.println("Impossible de se connecter à la BDD.");
            e.printStackTrace();
        }
        // Je retourne l'objet métier
        return passager;
    }

	@Override
	public void create(Passager pass) {
		try
        {

            // Créer ma requête d'insertion INSERT INTO
            PreparedStatement requete;
            // je teste si l'élève est lié à un formateur
            {
                requete = connexion.prepareStatement(
                        "insert into passager (idPassager,nom,prenom,idAdd)" + " VALUES(?,?,?,?)");

            // requete.setLong(1, eleve.getId());
            requete.setInt(1, pass.getIdPas());
            requete.setString(2, pass.getNom());
            // Je convertis une java.util.Date en java.sql.Date
            requete.setString(3, pass.getPrenom());
            requete.setInt(4, pass.getAdresse().getIdAdd());

            //je l'exécute
            requete.executeUpdate();
            }
           
        }catch(

	SQLException e)
	{
		e.printStackTrace();
	}catch(
	NullPointerException e)
	{
		e.printStackTrace();
	}finally
	{
		/*
		 * try { connexion.close(); } catch (SQLException e) {
		 * e.printStackTrace(); }
		 */
	}
	}

	@Override
	public Passager update(Passager pass) {
		try {

			PreparedStatement requete = connexion.prepareStatement(
					"update passager set idPassager=?,nom=?,prenom=?,idAdd=? where idPassager = ?");

			requete.setLong(5, pass.getIdPas());

			requete.setInt(1, pass.getIdPas());
            requete.setString(2, pass.getNom());
            requete.setString(3, pass.getPrenom());
            requete.setInt(4, pass.getAdresse().getIdAdd());

            requete.executeUpdate();

		}

		catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}

		return pass;
	}

	@Override
	public void delete(Passager pass) {
		
		try
        {

            PreparedStatement ps = connexion.prepareStatement("delete from passager where idPassager = ?");
            ps.setLong(1, pass.getIdPas());
            Scanner lecteur = new Scanner(System.in);
            System.out.println("Souhaitez vous également supprimer l'adresse et la/les reservations(s) liée à ce passager ? ");
            System.out.println("OUI : 1, NON : 0");
            int reponse = lecteur.nextInt();
            if (reponse == 1)
            {
            ps.executeUpdate();
            Adresse adresseASupprimer = adresseDao.findById(pass.getAdresse().getIdAdd());
            Reservation reservationASupprimer = reservationDao.findById(pass.getIdPas());
            adresseDao.delete(adresseASupprimer);
            reservationDao.delete(reservationASupprimer);
            }
            else{
            	ps.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
        }
	}		
}


