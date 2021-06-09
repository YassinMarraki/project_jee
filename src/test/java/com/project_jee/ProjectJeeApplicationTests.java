package com.project_jee;

import java.sql.Date;
import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project_jee.modele.Annonce;
import com.project_jee.modele.Utilisateur;
import com.project_jee.repository.AnnonceRepository;
import com.project_jee.repository.UtilisateurRepository;
import com.project_jee.service.AnnonceService;

@SpringBootTest
class ProjectJeeApplicationTests {

	@Autowired
	UtilisateurRepository uRepository;
	@Autowired
	AnnonceRepository annonceuRepository;
	@Autowired
	AnnonceService annonceService;



	@Transactional
	@Test
	void ajouteAnnonceTest() {
		long l=59;
		Utilisateur u=uRepository.findById(l);
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
		Annonce ann= annonceuRepository.findByAnnonce_Id(99);
		ann.setDescription("description");
		ann.setDesignation("Designation");
		ann.setPrix(3000);
		annonceuRepository.save(ann);
	}

	@Transactional
	@Test
	void supprimerAnnonce()
	{
		Annonce ann= annonceuRepository.findByAnnonce_Id(99);
		annonceuRepository .delete(ann);
	}



}
