package com.project_jee.modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Utilisateur {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id ;
	
	private String nom;
	private String prenom;
	private String email;
	private String numero_telephone;
	private String mot_de_passe;
	private String date_naissance;
	private String date_inscription;
	private String adresse;
	private String region;
	private String genre;
	private String ville;
	
	
	@OneToMany()
    @JoinColumn(name="id")
	private List<Annonce> annonces=new ArrayList<Annonce>();
	
	
	
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumero_telephone() {
		return numero_telephone;
	}
	public void setNumero_telephone(String numero_telephone) {
		this.numero_telephone = numero_telephone;
	}
	public String getMot_de_passe() {
		return mot_de_passe;
	}
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	public String getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}
	public String getDate_inscription() {
		return date_inscription;
	}
	public void setDate_inscription(String date_inscription) {
		this.date_inscription = date_inscription;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public List<Annonce> getAnnonces() {
		return annonces;
	}

	public void setAnnonces(List<Annonce> annonces) {
		this.annonces = annonces;
	}
	
}