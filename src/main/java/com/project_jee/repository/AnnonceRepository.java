package com.project_jee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project_jee.modele.Annonce;
import com.project_jee.modele.Utilisateur;


@Transactional
@Repository
public interface AnnonceRepository   extends JpaRepository<Annonce,Long>
{
	
	@Modifying
	@Query( "UPDATE  Annonce a SET etat='v' WHERE a.annonce_id=?1")
	void  valideAnnonceAdmin(Long id);
	
	
	@Modifying
	@Query( "UPDATE  Annonce a SET etat='n' WHERE a.annonce_id=?1")
	void refuseAnnonceAdmin(Long id);
	
	@Query( "SELECT a FROM Annonce a WHERE etat='v'")
	List<Annonce>  AnnoncesValide();
	
	@Query( "SELECT a FROM Annonce a WHERE  etat='n'")
	List<Annonce>   AnnoncesRefuse();
	
	@Query( "SELECT a FROM Annonce a WHERE  a.etat='e'")
	List<Annonce>   AnnoncesATraiter();
	
	@Query( "SELECT a FROM Annonce a WHERE  a.etat='v' AND a.categorie=?1")
	List<Annonce>   annoncesCat(String s);
	
	@Query( "SELECT a FROM Annonce a WHERE  a.annonce_id=?1")
	Annonce   findByAnnonce_Id(long id);
	
}
