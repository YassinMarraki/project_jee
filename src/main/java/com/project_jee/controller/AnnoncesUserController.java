package com.project_jee.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project_jee.modele.Annonce;
import com.project_jee.modele.Utilisateur;
import com.project_jee.repository.AnnonceRepository;
import com.project_jee.repository.UtilisateurRepository;
import com.project_jee.service.AnnonceService;

@Controller
public class AnnoncesUserController {

	@Autowired
	private AnnonceService serviceAnnonce;
	@Autowired
	AnnonceRepository annonceDAO;
	@Autowired
	UtilisateurRepository uDAO;

	@RequestMapping("/utilisateur/ajoute-annonce")
	public String addAnnoncePage(Model model) {

		Annonce annonce = new Annonce();
		model.addAttribute("annonce", annonce);

		return "/user/addAnnonce";
	}
	
	
	

	@RequestMapping(value = "/utilisateur/ajoute-annonce", method = RequestMethod.POST)
	public String saveAddAnnonce(@ModelAttribute("annonce") Annonce annonce, RedirectAttributes redirectAttributes) {
		
		Utilisateur u = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		
		
		byte[] byt = annonce.getPhotoPart().getBytes();
		annonce.setPhoto(byt);
		Date date = Date.valueOf(LocalDate.now());
		annonce.setDate_annonce(date);
		annonce.setUtilisateur(u);
		serviceAnnonce.save(annonce);

		redirectAttributes.addFlashAttribute("msgAjouter", "Votre demande d'annonce a été bien prise en compte et elle "
				+ "est en cours de validation. Veuillez attendre la validation du modérateur");

		return "redirect:/utilisateur/liste-annonce";
	}

	
	@GetMapping("/utilisateur/liste-annonce")
	public String listAnnonceUserPage(Model model) {

		List<Annonce> annonces = new ArrayList<Annonce>();

		Utilisateur u = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		
		for (Annonce annonce : u.getAnnonces()) {
			try {
				annonce.setBase64Image(Base64.getEncoder().encodeToString(annonce.getPhoto()));
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			if ( annonce.getEtat().equals("v") )
				annonces.add(annonce);

		}
		model.addAttribute("listAnnonce", annonces);

		return "/user/listAnnonce";
	}

	@GetMapping("/utilisateur")
	public String AnnonceUserPage(Model model) {

		List<Annonce> annonces = new ArrayList<Annonce>();

		Utilisateur u = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		for (Annonce annonce : u.getAnnonces()) {
			try {
				annonce.setBase64Image(Base64.getEncoder().encodeToString(annonce.getPhoto()));
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			if ( annonce.getEtat().equals("v") )
				annonces.add(annonce);

		}
		model.addAttribute("listAnnonce", annonces);

		return "/user/listAnnonce";
	}
	
	
	
	
	@RequestMapping("/utilisateur/liste-annonce/supprimmer/{annonce_id}")
	public String deleteAnnonce(@PathVariable(name = "annonce_id") int id,
			final RedirectAttributes redirectAttributes) {
		serviceAnnonce.delete(id);
		redirectAttributes.addFlashAttribute("msgSupprimer", "Votre annonce a été supprimmée avec succées.");
		return "redirect:/utilisateur/liste-annonce";
	}

	@GetMapping("/utilisateur/modifie-annonce/{annonce_id}")
	public ModelAndView editAnnonce(@PathVariable(name = "annonce_id") int id) {

		ModelAndView mav = new ModelAndView("user/editAnnonce");
		Annonce annonce = serviceAnnonce.get(id);

		mav.addObject("annonce", annonce);
		return mav;
	}

	@RequestMapping(value = "/utilisateur/liste-annonce", method = RequestMethod.POST)
	public String saveEditAnnonce(@ModelAttribute("annonce") Annonce annEdit,
			final RedirectAttributes redirectAttributes) {
		if (annEdit != null) {
			annonceDAO.save(annEdit);
		}

		redirectAttributes.addFlashAttribute("msgMdodifer", "Votre modification a été bien prise en compte.");

		return "redirect:/utilisateur/liste-annonce";
	}

}
