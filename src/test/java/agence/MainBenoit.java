/**
 * 
 */
package agence;

import java.io.PrintStream;
import java.util.List;

import agence.dao.ClientDao;
import agence.dao.ClientDaoSql;
import agence.dao.ClientMoralDaoSql;
import agence.dao.ClientPhysiqueDaoSql;
import agence.model.Client;
import agence.model.ClientMoral;
import agence.model.ClientPhysique;
import agence.model.Login;

public class MainBenoit {

	public static void main(String[] args) {
		// Création et affichage d'un objet
		
		ClientDao clientDao = new ClientMoralDaoSql();
		List<Client> listeClientMoral = clientDao.findAll();
        listeClientMoral = clientDao.findAll();
        
        afficherTestEtResultat("Création d'un Client Moral - Avant", listeClientMoral);
       
        ClientMoral newClientMoral=new ClientMoral();
        
        newClientMoral.setNom("ok");
        newClientMoral.setIdCli(3);
        newClientMoral.setNumeroTel("4654");
        newClientMoral.setNumeroFax("4564");
        newClientMoral.setEmail("@gmail");
        newClientMoral.setSiret(456466);
        clientDao.create(newClientMoral);
        
        listeClientMoral = clientDao.findAll();
        afficherTestEtResultat("Création d'un Client Moral - Après", listeClientMoral);
        
        // Mise à jour d'un objet
        ClientMoral clientMoralModif = (ClientMoral) clientDao.findById(3);	
        clientMoralModif=newClientMoral;
        clientMoralModif.setNom("modification");
        ClientMoral clientMoralMAJ = clientDao.update(clientMoralModif);
        listeClientMoral = clientDao.findAll();
        afficherTestEtResultat("Mise à jour d'un Client Moral - Après", listeClientMoral);
        
        // Suppression d'un objet
        ClientMoral clientMoralASupprimer = (ClientMoral) clientDao.findById(3);
        clientDao.delete(clientMoralASupprimer);
        listeClientMoral = clientDao.findAll();
        afficherTestEtResultat("Suppression d'un Client Moral - Après", listeClientMoral);
        System.out.println("\n\n");
        
        
        /////////////
        ClientDao clientDao2 = new ClientPhysiqueDaoSql();
		List<Client> listeClientPhysique = clientDao2.findAll();
        listeClientPhysique = clientDao.findAll();
        
        afficherTestEtResultat("Création d'un Login - Avant", listeClientPhysique);
       // ClientPhysique newClientPhysique = new ClientPhysique(5, "num", "1225", "2565", "io@sf.com", "ok");
        
        ClientPhysique newClientPhysique= new ClientPhysique();
        
        newClientPhysique.setNom("ok");
        newClientPhysique.setIdCli(3);
        newClientPhysique.setNumeroTel("4654");
        newClientPhysique.setNumeroFax("4564");
        newClientPhysique.setEmail("@gmail");
        newClientPhysique.setPrenom("pre");
        clientDao.create(newClientPhysique);
        
        clientDao2.create(newClientPhysique);
        listeClientPhysique = clientDao2.findAll();
        afficherTestEtResultat("Création d'un Client Physique - Après", listeClientPhysique);
        
        
        
        
        
        // Mise à jour d'un objet
        ClientPhysique clientPhysiqueModif = (ClientPhysique) clientDao2.findById(5);	
        clientPhysiqueModif.setIdCli(5);
        ClientPhysique clientPhysiqueMAJ = clientDao2.update(clientPhysiqueModif);
        listeClientPhysique = clientDao2.findAll();
        afficherTestEtResultat("Mise à jour d'un Client Physique - Après", listeClientPhysique);
        
        // Suppression d'un objet
        ClientPhysique clientPhysiqueASupprimer = (ClientPhysique) clientDao2.findById(5);
        clientDao.delete(clientPhysiqueASupprimer);
        listeClientPhysique = clientDao2.findAll();
        afficherTestEtResultat("Suppression d'un Client Physique - Après", listeClientPhysique);
        System.out.println("\n\n");

	}
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
