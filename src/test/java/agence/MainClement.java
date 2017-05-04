/**
 * 
 */
package agence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import agence.dao.AdresseDao;
import agence.dao.AdresseDaoSql;
import agence.dao.ClientDaoSql;
import agence.dao.ClientMoralDaoSql;
import agence.dao.ClientPhysiqueDaoSql;
import agence.dao.PassagerDao;
import agence.dao.PassagerDaoSql;
import agence.dao.ReservationDao;
import agence.dao.ReservationDaoSql;
import agence.dao.VolDaoSql;
import agence.model.Adresse;
import agence.model.Client;
import agence.model.EtatReservation;
import agence.model.Passager;
import agence.model.Reservation;
import agence.model.Vol;

/**
 * @author ajc
 *
 */
public class MainClement {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
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

		AdresseDao adresseDao = new AdresseDaoSql();
		ReservationDao resadao = new ReservationDaoSql();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		
		
		  Adresse addres = new Adresse(); 
		  addres.setIdAdd(100);
		  addres.setAdresse("5, allée des tourterelles");
		  addres.setCodePostal("33610"); addres.setVille("Canéjan");
		  addres.setPays("France"); 
		  adresseDao.create(addres);
		  
		  
		  Passager pass = new Passager();
		  pass.setIdPas(19);
		  pass.setNom("Boineau");
		  pass.setPrenom("Clement");
		  pass.setAdresse(addres);
		  passagerDao.create(pass);

		  Passager passmodif = passagerDao.findById(19);
		  passmodif.setNom("Griffon");
		  Passager passmaj = passagerDao.update(passmodif);
		  
		  Passager passsupp = passagerDao.findById(19);
				  passagerDao.delete(passsupp);
		  
		  
		  /*
		  Reservation resa = new Reservation();
			resa.setIdRes(10101);
			resa.setDate(simpleDateFormat.parse("04/01/1978"));
			resa.setNumero("1532489");
			resa.setEtat(EtatReservation.CONFIRMEE);
			resa.setVol(vol);
			resa.setPassager(passager);
			resa.setClient(clientPhysique);
			resadao.create(resa);
			System.out.println(resa.toString());
			 */
/*
		
		  Adresse addresmodif = adresseDao.findById(1);
		  addresmodif.setAdresse("3 place de l'étoile"); 
		  
		  Adresse adresseMAJ = adresseDao.update(addresmodif);
		 
			
			Reservation resamodif = resadao.findById(101);
			resamodif.setNumero("123212");
			Reservation resaMAJ = resadao.update(resamodif);
			
		
		

		 Adresse adresseASupprimer = adresseDao.findById(10000);
		 adresseDao.delete(adresseASupprimer);

		Reservation resadelete = resadao.findById(101);
		resadao.delete(resadelete);

		*/
		  
		  
		
	}

}
