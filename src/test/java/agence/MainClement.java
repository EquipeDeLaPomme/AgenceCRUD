/**
 * 
 */
package agence;

import agence.dao.AdresseDao;
import agence.dao.AdresseDaoSql;
import agence.model.Adresse;
import formation.model.Eleve;


/**
 * @author ajc
 *
 */
public class MainClement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		AdresseDao adresseDao = new AdresseDaoSql();
		/*
		Adresse addres = new Adresse();
			addres.setIdAdd(23);
			addres.setAdresse("5, allée des tourterelles");
			addres.setCodePostal("33610");
			addres.setVille("Canéjan");
			addres.setPays("France");
			
			adresseDao.create(addres);
			
			System.out.println(addres);
			
			
			Adresse addresmodif = adresseDao.findById(12);
			addresmodif.setAdresse("4 allée des tourterelles");
			Adresse adresseMAJ = adresseDao.update(addresmodif);
			System.out.println(adresseMAJ);
			*/

			
			Adresse adresseASupprimer = adresseDao.findById(10000);
			adresseDao.delete(adresseASupprimer);
	        
		}
		
	}


