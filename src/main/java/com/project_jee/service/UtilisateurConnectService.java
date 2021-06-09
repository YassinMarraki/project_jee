package com.project_jee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project_jee.modele.Utilisateur;
import com.project_jee.modele.UtilisateurCon;



@Service
public class UtilisateurConnectService implements UserDetailsService {
	
	@Autowired
    private UtilisateurService us;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur u = us.findByEmail(username);
        if (u == null) {
            throw new UsernameNotFoundException("L'adresse électronique que vous avez saisi n'est pas inscrite.");
        }
        return new UtilisateurCon(u);
    }
}
