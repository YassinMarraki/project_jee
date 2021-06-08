package com.project_jee.dao;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project_jee.modele.Annonce;
import com.project_jee.modele.ShowToAdminAnnonce;
import com.project_jee.modele.Utilisateur;

@Service
@Transactional
public class AnnonceService {
	@Autowired
	private AnnonceDAO anDAO;

	public List<Annonce> listAll() {
		return anDAO.findAll();
	}

	public Annonce get(long id) {
		return anDAO.findById(id).get();
	}

	public void delete(long id) {
		anDAO.deleteById(id);
	}

	@Autowired
	private SessionFactory sessionFactory;

	public AnnonceService() {
	}

	public AnnonceService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(Annonce annonce) {
		sessionFactory.openSession().save(annonce);
		anDAO.save(annonce);
	}

	public List<ShowToAdminAnnonce> afficheAdminAnnonce() {

		List<ShowToAdminAnnonce> adminAnnonces = new ArrayList<ShowToAdminAnnonce>();
		List<Annonce> annonces = null;

		annonces = anDAO.AnnoncesValide();

		for (Annonce annonce : annonces) {
			Utilisateur u = annonce.getUtilisateur();

			ShowToAdminAnnonce adminAnnonce = new ShowToAdminAnnonce();
			try {
				annonce.setBase64Image(Base64.getEncoder().encodeToString(annonce.getPhoto()));
			} catch (Exception e) {

				e.printStackTrace();
			}
			adminAnnonce.setAnnonce(annonce);
			adminAnnonce.setNom(u.getNom());
			adminAnnonce.setPrenom(u.getPrenom());
			adminAnnonce.setEmail(u.getEmail());
			adminAnnonce.setNumero_telephone(u.getNumero_telephone());
			adminAnnonce.setVille(u.getVille());
			adminAnnonces.add(adminAnnonce);

		}
		return adminAnnonces;

	}

	public List<ShowToAdminAnnonce> afficheVisitorAnnonce(String cat) {

		List<ShowToAdminAnnonce> adminAnnonces = new ArrayList<ShowToAdminAnnonce>();
		List<Annonce> annonces = null;

		annonces = anDAO.annoncesCat(cat);

		for (Annonce annonce : annonces) {
			Utilisateur u = annonce.getUtilisateur();

			ShowToAdminAnnonce adminAnnonce = new ShowToAdminAnnonce();
			try {
				annonce.setBase64Image(Base64.getEncoder().encodeToString(annonce.getPhoto()));
			} catch (Exception e) {

				e.printStackTrace();
			}
			adminAnnonce.setAnnonce(annonce);
			adminAnnonce.setNom(u.getNom());
			adminAnnonce.setPrenom(u.getPrenom());
			adminAnnonce.setEmail(u.getEmail());
			adminAnnonce.setNumero_telephone(u.getNumero_telephone());
			adminAnnonce.setVille(u.getVille());
			adminAnnonces.add(adminAnnonce);

		}
		return adminAnnonces;

	}

	public List<ShowToAdminAnnonce> afficheAdminAnnonce(String s) {

		List<ShowToAdminAnnonce> adminAnnonces = new ArrayList<ShowToAdminAnnonce>();
		List<Annonce> annonces = null;

		if (s.equals("a traiter")) {
			annonces = anDAO.AnnoncesATraiter();

		} else if (s.equals("valide")) {
			annonces = anDAO.AnnoncesValide();
		} else if (s.equals("refuse")) {
			annonces = anDAO.AnnoncesRefuse();
		}

		for (Annonce annonce : annonces) {
			Utilisateur u = annonce.getUtilisateur();

			ShowToAdminAnnonce adminAnnonce = new ShowToAdminAnnonce();
			try {
				annonce.setBase64Image(Base64.getEncoder().encodeToString(annonce.getPhoto()));
			} catch (Exception e) {

				e.printStackTrace();
			}
			adminAnnonce.setAnnonce(annonce);
			adminAnnonce.setNom(u.getNom());
			adminAnnonce.setPrenom(u.getPrenom());
			adminAnnonce.setEmail(u.getEmail());
			adminAnnonce.setNumero_telephone(u.getNumero_telephone());
			adminAnnonce.setVille(u.getVille());
			adminAnnonces.add(adminAnnonce);

		}
		return adminAnnonces;

	}

}
