package com.project_jee;

import com.project_jee.dao.AnnonceDAO;
import com.project_jee.dao.AnnonceService;
import com.project_jee.dao.UtilisateurDAO;
import com.project_jee.modele.Annonce;
import com.project_jee.modele.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;

@SpringBootTest
class ProjectJeeApplicationTests {

	@Autowired
	UtilisateurDAO uDAO;
	@Autowired
	AnnonceDAO annonceDAO;
	@Autowired
	AnnonceService annonceService;



	@Transactional
	@Test
	void ajouteAnnonceTest() {
		Utilisateur u=uDAO.findById(59);
		Annonce  ann=new Annonce();
		ann.setDesignation("Designation");
		ann.setCategorie("Autres");
		ann.setDescription("description");
		ann.setDate_annonce(Date.valueOf(LocalDate.now()));
		ann.setUtilisateur(u);
		u.getAnnonces().add(ann);
		annonceService.save(ann);
	}

	@Transactional
	@Test
	void modifierAnnonceTest()
	{
		Annonce ann= annonceDAO.findByAnnonce_Id(99);
		ann.setDescription("description");
		annonceDAO.save(ann);
	}

	@Transactional
	@Test
	void supprimerAnnonce()
	{
		Annonce ann= annonceDAO.findByAnnonce_Id(99);
        annonceDAO .delete(ann);
	}



}
