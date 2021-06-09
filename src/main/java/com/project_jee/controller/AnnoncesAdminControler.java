package com.project_jee.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project_jee.dao.AnnonceDAO;
import com.project_jee.dao.UtilisateurDAO;
import com.project_jee.modele.Annonce;
import com.project_jee.modele.ShowToAdminAnnonce;
import com.project_jee.modele.Utilisateur;

@Controller
public class AnnoncesAdminControler {

	@Autowired
	UtilisateurDAO uDAO;
	@Autowired
	AnnonceDAO anDAO;

	@RequestMapping("/admin")
	String adminHome(Model model) {

		model.addAttribute("liste", afficheAdminAnnonce("a traiter"));

		return "/admin/adminHome";
	}

	@RequestMapping("/admin/valide/{annonce_id}")
	public String valideAnnonce(@PathVariable(name = "annonce_id") long id,
			final RedirectAttributes redirectAttributes) {

		Long l = Long.valueOf(id);
		anDAO.valideAnnonceAdmin(l);
		redirectAttributes.addFlashAttribute("msgValide",
				"Votre demande d'approuver l'annonce a été effectué avec succés.");
		return "redirect:/admin";
	}

	@RequestMapping("/admin/refuse/{annonce_id}")
	public String refuseAnnonce(@PathVariable(name = "annonce_id") long id,
			final RedirectAttributes redirectAttributes) {

		Long l = Long.valueOf(id);
		anDAO.refuseAnnonceAdmin(l);
		redirectAttributes.addFlashAttribute("msgRefuse",
				"Votre demande de rejeter l'annonce a été effectué avec succés.");
		return "redirect:/admin";
	}

	@RequestMapping("/admin/annoncesValide")
	String adminAnnoncesValide(Model model) {
		model.addAttribute("liste", afficheAdminAnnonce("valide"));

		return "admin/annoncesValide";
	}

	@RequestMapping("/admin/annoncesRefuse")
	String annoncesRefuseAdminPage(Model model) {

		model.addAttribute("liste", afficheAdminAnnonce("refuse"));

		return "admin/annoncesRefuse";
	}

	@RequestMapping("/admin/suppirmer/{annonce_id}")
	public String deleteAnnonceAdmin(@PathVariable(name = "annonce_id") long id,
			final RedirectAttributes redirectAttributes) {
		Annonce an = anDAO.findByAnnonce_Id(id);
            anDAO.delete(an);
            redirectAttributes.addFlashAttribute("msgDelete",
    				" L'annonce a été supprimmée avec succées");
		return "redirect:/admin/annoncesRefuse";
	}

	List<ShowToAdminAnnonce> afficheAdminAnnonce(String s) {

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
