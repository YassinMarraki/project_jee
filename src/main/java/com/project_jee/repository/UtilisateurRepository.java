package com.project_jee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project_jee.modele.Utilisateur;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
	
	
	
	@Query("select u from Utilisateur u where u.email = ?1")
    List<Utilisateur> findByEmail(String email);
	
	@Query( "SELECT S FROM Utilisateur S WHERE S.id = ?1 ")
	Utilisateur findUser(long  id);
	
	@Query( "SELECT S FROM Utilisateur S WHERE S.id = ?1 ")
	Utilisateur findById(long id);

}