/**
 * 
 */
package agence;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import agence.dao.LoginDao;
import agence.dao.LoginDaoSql;
import agence.model.Login;

/**
 * @author ajc
 *
 */
public class MainFinal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		Scanner sc = new Scanner(System.in);
		
		Integer maxClasse = 6;
		Integer classeAModifier = 0;
		Integer actionARealiser = 0;
		
		do {
			System.out.println("Quelle classe voulez-vous modifier ?");
			System.out.println("\t 1 - Adresse");
			System.out.println("\t 2 - Passager");
			System.out.println("\t 3 - Login");
			System.out.println("\t 4 - Reservation");
			System.out.println("\t 5 - Client physique");
			System.out.println("\t 6 - Client moral");
			
			classeAModifier = sc.nextInt();
		} while ((classeAModifier<1) || (classeAModifier>maxClasse));
		
		do {
			System.out.println("Quelle action voulez-vous faire sur la classe sélectionnée ?");
			System.out.println("\t 1 - Afficher");
			System.out.println("\t 2 - Créer");
			System.out.println("\t 3 - Mettre à jour");
			System.out.println("\t 4 - Supprimer");
			
			actionARealiser = sc.nextInt();
		} while ((actionARealiser<0) || (actionARealiser>4));
		
		
		
		switch (classeAModifier) {
		case 3:
			switch (actionARealiser) {
			case 2: 
				LoginDao loginDao = new LoginDaoSql();
		        List<Login> listeLogins = loginDao.findAll();

		        listeLogins = loginDao.findAll();
		        afficherTestEtResultat("Création d'un Login - Avant", listeLogins);
		        Login newLogin = new Login(3, "tata@gmail.com", "motdepasse", false);
		        loginDao.create(newLogin);
		        listeLogins = loginDao.findAll();
		        afficherTestEtResultat("Création d'un Login - Après", listeLogins);
				break;

			default:
				break;
			}
			
			break;

		default:
			System.out.println("Erreur de programmation.");
			break;
		}
		
		System.out.println("Toto");
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
