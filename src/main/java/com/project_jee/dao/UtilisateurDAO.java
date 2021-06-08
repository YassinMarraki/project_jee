package com.project_jee.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project_jee.modele.Utilisateur;

public interface UtilisateurDAO extends JpaRepository<Utilisateur, Long> {

	
	@Query( "SELECT S FROM Utilisateur S WHERE S.id = ?1 ")
	Utilisateur findUser(long  id);
	
	@Query( "SELECT S FROM Utilisateur S WHERE S.id = ?1 ")
	Utilisateur findById(long id);

}
