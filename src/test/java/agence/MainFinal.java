/**
 * 
 */
package agence;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import agence.dao.AdresseDao;
import agence.dao.AdresseDaoSql;
import agence.dao.ClientDaoSql;
import agence.dao.ClientMoralDaoSql;
import agence.dao.ClientPhysiqueDaoSql;
import agence.dao.LoginDao;
import agence.dao.LoginDaoSql;
import agence.dao.PassagerDao;
import agence.dao.PassagerDaoSql;
import agence.dao.ReservationDao;
import agence.dao.ReservationDaoSql;
import agence.dao.VolDaoSql;
import agence.model.Adresse;
import agence.model.Client;
import agence.model.Login;
import agence.model.Passager;
import agence.model.Reservation;
import agence.model.Vol;

/**
 * @author ajc
 *
 */
public class MainFinal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Scanner sc = new Scanner(System.in);
			
			Integer maxClasse = 6;
			Integer classeAModifier = 0;
			Integer actionARealiser = 0;
			Integer affichageARealiser = 0;
			
			// Condition pour savoir quelle classe modifier
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
			
			// Condition pour savoir quelle action réaliser
			do {
				System.out.println("Quelle action voulez-vous faire sur la classe sélectionnée ?");
				System.out.println("\t 1 - Afficher");
				System.out.println("\t 2 - Créer");
				System.out.println("\t 3 - Mettre à jour");
				System.out.println("\t 4 - Supprimer");
				
				actionARealiser = sc.nextInt();
			} while ((actionARealiser<0) || (actionARealiser>4));
			
			// Condition pour savoir quel affichage faire
			if (actionARealiser == 1) {
				do {
					System.out.println("Quel affichage souhaitez-vous ?");
					System.out.println("\t 1 - Afficher toute la liste");
					System.out.println("\t 2 - Afficher à partir d'un ID");
					
					affichageARealiser = sc.nextInt();
				} while ((affichageARealiser<0) || (affichageARealiser>2));
			}
			
			
			
			
			// Switch en fonction de la classe à afficher
			switch (classeAModifier) {
			case 1:
				AdresseDao adresseDao = new AdresseDaoSql();
		        List<Adresse> listeAdresses = adresseDao.findAll();
		        Adresse adresse = adresseDao.findById(1);
		        
				switch (actionARealiser) {
				case 1:
					switch (affichageARealiser) {
					case 1:
				        afficherTestEtResultat("Liste des adresses", listeAdresses);
						break;
						
					case 2:
				        adresse = adresseDao.findById(2);
				        afficherTestEtResultat("Une seule adresse", adresse);
						break;

					default:
						System.out.println("Erreur de programmation.");
						break;
					} break;
					
				case 2: 
					listeAdresses = adresseDao.findAll();
			        afficherTestEtResultat("Création d'une Adresse - Avant", listeAdresses);
					Adresse addres = new Adresse();
					addres.setIdAdd(55);
					addres.setAdresse("5, allée des tourterelles");
					addres.setCodePostal("33610");
					addres.setVille("Canéjan");
					addres.setPays("France");
					adresseDao.create(addres);

			        adresse = adresseDao.findById(55);
			        afficherTestEtResultat("Affichage de l'adresse créée", adresse);
			        
			        listeAdresses = adresseDao.findAll();
			        afficherTestEtResultat("Création d'une Adresse - Après", listeAdresses);
					break;
					
				case 3:
			        afficherTestEtResultat("Mise à jour d'une Adresse - Avant", adresse);
			        Adresse loginModif = adresseDao.findById(10);
			        loginModif.setAdresse("3 place de etoile");
			        Adresse loginMAJ = adresseDao.update(loginModif);
			        adresse = adresseDao.findById(10);
			        afficherTestEtResultat("Mise à jour d'une Adresse - Après", adresse);
					break;
						
				case 4:
			        afficherTestEtResultat("Suppression d'une Adresse - Avant", listeAdresses);
			        Adresse loginASupprimer = adresseDao.findById(55);
			        adresseDao.delete(loginASupprimer);

			        afficherTestEtResultat("Affichage de l'adresse supprimée", loginASupprimer);
			        
			        listeAdresses = adresseDao.findAll();
			        afficherTestEtResultat("Suppression d'une Adresse - Après", listeAdresses);
			        System.out.println("\n\n");
					break;

				default:
					System.out.println("Erreur de programmation.");
					break;
				}
				
				break;

			default:
				System.out.println("Erreur de programmation.");
				break;
				
			
			
			case 3:
				LoginDao loginDao = new LoginDaoSql();
		        List<Login> listeLogins = loginDao.findAll();
		        Login logins = loginDao.findById(1);
		        
				switch (actionARealiser) {
				case 1:
					switch (affichageARealiser) {
					case 1:
				        afficherTestEtResultat("Liste des logins", listeLogins);
						break;
						
					case 2:
				        Login login = loginDao.findById(2);
				        afficherTestEtResultat("Un seul login", login);
						break;

					default:
						System.out.println("Erreur de programmation.");
						break;
					} break;
					
				case 2: 
			        listeLogins = loginDao.findAll();
			        afficherTestEtResultat("Création d'un Login - Avant", listeLogins);
			        Login newLogin = new Login(3, "tata@gmail.com", "motdepasse", false);
			        loginDao.create(newLogin);
			        listeLogins = loginDao.findAll();
			        afficherTestEtResultat("Création d'un Login - Après", listeLogins);
					break;
					
				case 3:
			        afficherTestEtResultat("Mise à jour d'un Login - Avant", logins);
			        Login loginModif = loginDao.findById(1);
			        loginModif.setMotDePasse("TataToto");
			        Login loginMAJ = loginDao.update(loginModif);
			        logins = loginDao.findById(1);
			        afficherTestEtResultat("Mise à jour d'un Login - Après", logins);
					break;
						
				case 4:
			        afficherTestEtResultat("Suppression d'un Login - Avant", listeLogins);
			        Login loginASupprimer = loginDao.findById(3);
			        loginDao.delete(loginASupprimer);
			        listeLogins = loginDao.findAll();
			        afficherTestEtResultat("Suppression d'un Login - Après", listeLogins);
			        System.out.println("\n\n");
					break;

				default:
					System.out.println("Erreur de programmation.");
					break;
				}
				
				break;
				
			case 4:
				VolDaoSql volDao = new VolDaoSql();
		        List<Vol> listeVols = volDao.findAll();
		        Vol vol = volDao.findById(1);
		        volDao.fermetureConnexion();
		        
		        PassagerDao passagerDao = new PassagerDaoSql();
		        List<Passager> listePassagers = passagerDao.findAll();
		        Passager passager = passagerDao.findById(1);
		        
		        
		        ClientDaoSql clientDaoSql = new ClientMoralDaoSql();
		        List<Client> listeClientsMoraux = clientDaoSql.findAll();
		        Client clientMoral = clientDaoSql.findById(30);


		        clientDaoSql = new ClientPhysiqueDaoSql();
		        List<Client> listeClientsPhysiques = clientDaoSql.findAll();
		        Client clientPhysique = clientDaoSql.findById(10);
			        
				ReservationDao reservationDao = new ReservationDaoSql();
		        List<Reservation> listeReservations = reservationDao.findAll();
		        Reservation reservation = reservationDao.findById(1);
		        
				switch (actionARealiser) {
				case 1:
					do {
						System.out.println("Quel affichage souhaitez-vous ?");
						System.out.println("\t 1 - Afficher toute la liste");
						System.out.println("\t 2 - Afficher à partir d'un ID");
						System.out.println("\t 3 - Afficher à partir de l'ID d'un passager");
						
						affichageARealiser = sc.nextInt();
					} while ((affichageARealiser<0) || (affichageARealiser>3));
					
					switch (affichageARealiser) {
					case 1:
				        afficherTestEtResultat("Liste des Réservations", listeReservations);
						break;
						
					case 2:
						reservation = reservationDao.findById(21);
				        afficherTestEtResultat("Une seule réservation", reservation);
						break;
						
					case 3:
						reservation = reservationDao.findById(21);
				        afficherTestEtResultat("Une seule réservation pour le passager", reservation);
						break;

					default:
						System.out.println("Erreur de programmation.");
						break;
					} break;
					
				case 2: /*
			        listeReservations = reservationDao.findAll();
			        afficherTestEtResultat("Création d'un Login - Avant", listeReservations);
			        Reservation new = new Login(3, "tata@gmail.com", "motdepasse", false);
			        loginDao.create(newLogin);
			        listeLogins = loginDao.findAll();
			        afficherTestEtResultat("Création d'un Login - Après", listeReservations);*/
					break;
					
				case 3:
					/*
			        afficherTestEtResultat("Mise à jour d'un Login - Avant", logins);
			        Login loginModif = loginDao.findById(1);
			        loginModif.setMotDePasse("TataToto");
			        Login loginMAJ = loginDao.update(loginModif);
			        logins = loginDao.findById(1);
			        afficherTestEtResultat("Mise à jour d'un Login - Après", logins); */
					break;
				
						
				case 4:
			        afficherTestEtResultat("Suppression d'une Réservation - Avant", listeReservations);
			        Reservation ReservationASupprimer = reservationDao.findById(40);
			        reservationDao.delete(ReservationASupprimer);
			        listeReservations = reservationDao.findAll();
			        afficherTestEtResultat("Suppression d'une Réservation - Après", listeReservations);
			        System.out.println("\n\n");
					break;

				default:
					System.out.println("Erreur de programmation.");
					break;
				}
				
				
				// String test2 = sc.nextLine();
				break;

				/*
			default:
				System.out.println("Erreur de programmation.");
				break;
				*/
				
				
				

				
				
			}
		} catch (NullPointerException e) {
			System.err.println("L'index renseigné est incorrect !");
			e.printStackTrace();
		}
		
		
		
		
		

		
		
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
