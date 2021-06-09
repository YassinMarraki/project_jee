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

import com.project_jee.Mail;
import com.project_jee.modele.Annonce;
import com.project_jee.modele.ShowToAdminAnnonce;
import com.project_jee.modele.Utilisateur;
import com.project_jee.repository.AnnonceRepository;
import com.project_jee.repository.UtilisateurRepository;
import com.project_jee.service.AnnonceService;

@Controller
public class AnnoncesAdminControler {

	@Autowired
	UtilisateurRepository uDAO;
	@Autowired
	AnnonceRepository anDAO;
	@Autowired
	AnnonceService anserv;

	@Autowired
	private Mail mail;

	@RequestMapping("/admin")
	String adminHome(Model model) {
        
		model.addAttribute("liste", anserv.afficheAdminAnnonce("a traiter"));

		return "/admin/adminHome";
	}

	@RequestMapping("/admin/valide/{annonce_id}")
	public String valideAnnonce(@PathVariable(name = "annonce_id") long id,
			final RedirectAttributes redirectAttributes) {

		Long l = Long.valueOf(id);
		anDAO.valideAnnonceAdmin(l);
		Utilisateur u = anDAO.findById(id).get().getUtilisateur();
		
		mail.envoyer(u.getEmail(), "Validation de votre demande d'annonce", 
				
				
				"Bonjour " + u.getPrenom() + ",\n" + 
					"Après l'analyse de votre annonce de la part du modérateur, ce dernier a décider de valider votre demande "
					+ "d'annonce. Vous pouvez toujours visualiser votre annonce dans la rubrique : Découvrir."
					+ "\n\nCordialement.\n"+
					"L'équipe Mon Annonce.");
		
		
		
		
		
		
		redirectAttributes.addFlashAttribute("msgValide",
				"Votre demande d'approuver l'annonce a été effectué avec succés.");
		
		return "redirect:/admin";
	}

	@RequestMapping("/admin/refuse/{annonce_id}")
	public String refuseAnnonce(@PathVariable(name = "annonce_id") long id,
			final RedirectAttributes redirectAttributes) {

		Long l = Long.valueOf(id);
		anDAO.refuseAnnonceAdmin(l);
		
		Utilisateur u = anDAO.findById(id).get().getUtilisateur();

		mail.envoyer(u.getEmail(), "Refus de votre demande d'annonce", 

				"Bonjour " + u.getPrenom() + ",\n" + 
					"Après l'analyse de votre annonce de la part du modérateur, ce dernier a décider de rejeter votre demande"
					+ " d'annonce vu qu'elle ne respecte pas nos règles d'utilisation."
					+ "\n\nCordialement.\n"+
					"L'équipe Mon Annonce.");
		
		
		
		redirectAttributes.addFlashAttribute("msgRefuse",
				"Votre demande de rejeter l'annonce a été effectué avec succés.");
		return "redirect:/admin";
	}

	@RequestMapping("/admin/annoncesValide")
	String adminAnnoncesValide(Model model) {
		model.addAttribute("liste", anserv.afficheAdminAnnonce("valide"));
		

		return "admin/annoncesValide";
	}

	@RequestMapping("/admin/annoncesRefuse")
	String annoncesRefuseAdminPage(Model model) {

		model.addAttribute("liste",anserv. afficheAdminAnnonce("refuse"));

		return "admin/annoncesRefuse";
	}

	@RequestMapping("/admin/suppirmer/{annonce_id}")
	public String deleteAnnonceAdmin(@PathVariable(name = "annonce_id") long id,
			final RedirectAttributes redirectAttributes) {
		Annonce an = anDAO.findByAnnonce_Id(id);
		anDAO.delete(an);
		redirectAttributes.addFlashAttribute("msgDelete", " L'annonce a été supprimmée avec succées");
		return "redirect:/admin/annoncesRefuse";
	}

	

}
