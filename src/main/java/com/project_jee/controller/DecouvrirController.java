package com.project_jee.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project_jee.dao.AnnonceService;
import com.project_jee.dao.UtilisateurDAO;
import com.project_jee.modele.Annonce;
import com.project_jee.modele.Utilisateur;

import javassist.bytecode.annotation.LongMemberValue;

@Controller
public class DecouvrirController {

	@Autowired
	AnnonceService anserv;
	@Autowired
	UtilisateurDAO uDAO;

	@GetMapping("/decouvrir")
	public String decouvrir(Model model) {

		return "decouvrir";
	}

	@RequestMapping(value = "/decouvrir", method = RequestMethod.POST)
	public String afficheAnnoncesVisitor(@ModelAttribute("categorie") String cat, Model model,
			final RedirectAttributes redirectAttributes) {
		if (cat != null) {

			redirectAttributes.addFlashAttribute("liste", anserv.afficheVisitorAnnonce(cat));
		}
		return "redirect:/decouvrir";
	}
	
	

	@RequestMapping("/plusdinfos/{id}")
	String infosAnnonceur(@PathVariable(name = "id") long id, final  Model model) {
		Utilisateur u = uDAO.findUser(id);
		model.addAttribute("a", u);
		return "infosAnnonceur";
	}
}
