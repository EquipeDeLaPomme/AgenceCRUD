/**
 * 
 */
package agence;

import java.io.PrintStream;
import java.util.List;

import agence.dao.LoginDao;
import agence.dao.LoginDaoSql;
import agence.model.Login;
import formation.model.Eleve;

/**
 * @author ajc
 *
 */
public class MainDamien {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		
		


		LoginDao loginDaoSql = new LoginDaoSql();
        List<Login> listeLogins = loginDaoSql.findAll();
        afficherTestEtResultat("Liste des logins", listeLogins);
        Login login = loginDaoSql.findById(2);
        afficherTestEtResultat("Un seul login", login);
        
        
        afficherTestEtResultat("Création d'un Login - Avant", listeLogins);
        Login newLogin = new Login(3, "tata@gmail.com", "motdepasse", false);
        loginDaoSql.create(newLogin);
        afficherTestEtResultat("Création d'un Login - Après", listeLogins);
        Login loginASupprimer = loginDaoSql.findById(3);
        loginDaoSql.delete(loginASupprimer);

        /*
        // mettre à jour l'élève n°4, je change sa note
        Login loginAChanger = loginDaoSql.findById(2);
        // je mets à jour au niveau de l'OBJET la note
        loginAChanger.setMotDePasse("TotoTata");
        System.out.println("tata");
        // je mets à jour au niveau BDD !!
        Login eleveMisAJour = loginDaoSql.update(loginAChanger);
        System.out.println("toto");
        afficherTestEtResultat("Update d'un Login", eleveMisAJour);
        System.out.println("toto");
        // Je supprime l'élève n°5
        Login loginASupprimer = loginDaoSql.findById(5);
        loginDaoSql.delete(loginASupprimer);
        */
	}
	
	
	
	
	///////////      Differentes fonctions permettant d'afficher les resutlats /////////////
	
	
        /**
         * @param <T>
         * @param test
         * @param t
         */
        private static <T> void afficherTestEtResultat(String test, T t)
        {
            afficherTest(test);
            afficherResultat(t);
        }

        /**
         * @param <T>
         * @param test
         * @param liste
         */
        private static <T> void afficherTestEtResultat(String test, List<T> liste)
        {
            afficherTest(test);
            afficherListe(liste);

        }

        /**
         * @param <T>
         * @param adresse
         */
        private static <T> void afficherResultat(T objet)
        {
            System.out.println(objet.toString());
        }

        /**
         * @param string
         */
        private static void afficherTest(String string)
        {
            String fmtEntete = "|";
            String separateur = "+-----------------------------------------------------------------------------------------------------------+\n";
            // String separateur = "+----------+\n";
            int avant = (separateur.length() - 2 - string.length()) / 2;
            int apres = separateur.length() - avant - 4;
            for (int i = 0; i < avant; i++)
            {
                fmtEntete += " ";
            }
            fmtEntete += "%1$-" + Integer.toString(apres) + "s |%n";

            PrintStream console = System.out;
            console.printf("%s", separateur);
            console.printf(fmtEntete, string);
            console.printf("%s", separateur);

        }

        /**
         * @param <T>
         * @param listeAdresses
         */
        private static <T> void afficherListe(List<T> liste)
        {
            for (T t : liste)
            {
                System.out.println(t.toString());
            }

        }

		
	}