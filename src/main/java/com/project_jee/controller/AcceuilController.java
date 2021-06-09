package com.project_jee.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project_jee.Mail;
import com.project_jee.modele.Utilisateur;
import com.project_jee.modele.UtilisateurReg;
import com.project_jee.service.UtilisateurService;




@Controller
public class AcceuilController {
	
	@Autowired
	private UtilisateurService us;
	
	@Autowired
	private Mail mail;
	
	
	
	@GetMapping("/")
    public String home() {
        return "home";
    }
	
	@GetMapping("/presentation")
    public String presentation() {
        return "presentation";
    }
	
	
	@GetMapping("/contact")
    public String contact() {
        return "contact";
    }
	
	
	@GetMapping("/inscription")
    public String inscription() {
		
		
		
		
		
        return "ins";
    }
	
	
	
	
	@PostMapping("/inscription")
    public String inscriptionPost(@ModelAttribute UtilisateurReg ureg, Model model) {
		
		
		
		/*System.out.print("\nLe resultat " + " " + ureg.getEmail() + " " + ureg.getNom() + " " + ureg.getPrenom() + " " + ureg.getAdresse() + " " + ureg.getMot_de_passe()+
				" " + ureg.getConf_mot_de_passe() + " " + ureg.getConf_email() + " Le genre : " + ureg.getGenre() + " " + ureg.getRegion() +
				" " + ureg.getVille() + " " + ureg.getRegle() + " " + ureg.getDate_naissance());*/
		
		
		ArrayList<String> T = new ArrayList<String>();
		
		
		if ( 
				(ureg.getNom().equals("")) || (ureg.getPrenom().equals("")) || 
				(ureg.getEmail().equals("")) || (ureg.getConf_email().equals("")) || 
				(ureg.getMot_de_passe().equals("")) || (ureg.getConf_mot_de_passe().equals("")) || 
				(ureg.getNumero_telephone().equals("")) || (ureg.getDate_naissance().equals("")) || 
				(ureg.getGenre() == null) || (ureg.getRegion().equals("")) || 
				(ureg.getAdresse().equals("")) || (ureg.getVille().equals("")) 
			)
			
			T.add("Veuillez remplir tous les champs.");
		
		else {
			
			if ( !ureg.getEmail().equals(ureg.getConf_email()) )
				T.add("Les deux emails que vous avez saisi ne sont pas identiques.");
			
			if ( !ureg.getMot_de_passe().equals(ureg.getConf_mot_de_passe()) )
				T.add("Les deux mots de passe que vous avez saisi ne sont pas identiques.");
			
			if (ureg.getRegle() == false)
				T.add("Veuillez accepter les conditions d'utilisation.");
			
			if (T.isEmpty()) {
				if (us.findByEmail(ureg.getEmail()) != null )
					T.add("Cet email est déjà inscrit dans notre plateforme.");
			}
			
		}
		
		
		if (T.isEmpty()) {
			
			ureg.setDate_inscription(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			
			
			us.ajouterU(ureg);
			
			mail.envoyer(ureg.getEmail(), "Inscription dans la plateforme Mon Annonce", 
					
					
					"Bonjour " + ureg.getPrenom() + ",\n" + 
						"Votre inscription a été bien prise en compte et nous sommes très ravis de vous compter parmi nous."
						+ " Nous espérons que notre plateforme vous sera utile.\n\n" + 
						"Cordialement.\n"+
						"L'équipe Mon Annonce.");
			
			return "mailins";
			
			
			
			
		}
			
			
		model.addAttribute("erreur", T);
	    return "ins";
		
		
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/connecter")
    public String connecter(@RequestParam("error") Optional<Integer> error, Model model) {
		
		if (error.isPresent()) {
			model.addAttribute("erreur", "Les identifiants que vous avez saisi sont incorrects.");
			return "login";
		}
		
        return "login";
    }
	
	
	@GetMapping("/loginsuccess")
    public String loginsucess() {
		
		Utilisateur u = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (u.getEmail().equals("admin@e"))
			return "redirect:/admin";
		
        return "redirect:/utilisateur/ajoute-annonce";
    }
	

	
	
	
	
	
}