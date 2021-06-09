package com.project_jee.modele;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UtilisateurCon extends Utilisateur implements UserDetails {
	
	public UtilisateurCon(Utilisateur user) {
		
        setNom(user.getNom());
        setPrenom(user.getPrenom());
        setEmail(user.getEmail());
        setId((int) user.getId());
        setDate_naissance(user.getDate_naissance());
        setDate_inscription(user.getDate_inscription());
        setAdresse(user.getAdresse());
        setVille(user.getVille());
        setRegion(user.getRegion());
        setGenre(user.getGenre());
        setNumero_telephone(user.getNumero_telephone());
        setMot_de_passe(user.getMot_de_passe());
        
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
 
    @Override
    public String getPassword() {
        return getMot_de_passe();
    }
 
    @Override
    public String getUsername() {
        return getEmail();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
     
}