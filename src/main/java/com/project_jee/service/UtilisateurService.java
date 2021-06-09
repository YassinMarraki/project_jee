package com.project_jee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project_jee.modele.Utilisateur;
import com.project_jee.modele.UtilisateurReg;
import com.project_jee.repository.UtilisateurRepository;


@Service
public class UtilisateurService {
	
	@Autowired
	private UtilisateurRepository ur;
	
	@Autowired 
	private PasswordEncoder passwordEncoder; 
	
	public void ajouterU(UtilisateurReg ureg) {
		
		Utilisateur u = new Utilisateur();
		
		u.setNom(ureg.getNom());
		u.setPrenom(ureg.getPrenom());
		u.setMot_de_passe(passwordEncoder.encode(ureg.getMot_de_passe()));
		u.setDate_naissance(ureg.getDate_naissance());
		u.setGenre(ureg.getGenre());
		u.setNumero_telephone(ureg.getNumero_telephone());
		u.setRegion(ureg.getRegion());
		u.setAdresse(ureg.getAdresse());
		u.setVille(ureg.getVille());
		u.setDate_inscription(ureg.getDate_inscription());
		u.setEmail(ureg.getEmail());
		
		
		
		ur.save(u);
	}
	
	
	public Utilisateur findByEmail(String email) {
		
		List<Utilisateur> l = ur.findByEmail(email);
		
		if(l.size() == 0)
			return null;
		
		return l.get(0);
	}
	
	
	
	
}