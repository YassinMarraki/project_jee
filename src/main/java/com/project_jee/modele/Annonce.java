package com.project_jee.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.Part;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.util.Base64;
import java.util.Set;

@Entity
@Table(name = "annonce")
public class Annonce {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long annonce_id;
	private String designation;
	private String description;
	@Column(updatable = false)
	private Date date_annonce;
	private float prix;
	private String categorie;
	@Lob
	@Column(name = "photo", updatable = false)
	private byte[] photo;
	@Transient
	private CommonsMultipartFile photoPart;
	@Transient
	private String base64Image;
	@Column(updatable = false)
	private String etat="e";
	@ManyToOne
    @JoinColumn(name="id", nullable=false,updatable = false)
    private Utilisateur utilisateur;
	
	
	public Annonce() {

	}
	public Annonce(long annonce_id, String designation, String description, Date date_annonce, float prix,
			String categorie, long utilisateur_id) {
		super();
		this.annonce_id = annonce_id;
		this.description = designation;
		this.designation = description;
		this.date_annonce = date_annonce;
		this.prix = prix;
		this.categorie = categorie;
		
	}

	public long getAnnonce_id() {
		return annonce_id;
	}

	public void setAnnonce_id(long annonce_id) {
		this.annonce_id = annonce_id;
	}

	public String getDesignation() {
		return description;
	}

	public void setDesignation(String designation) {
		description = designation;
	}

	public String getDescription() {
		return designation;
	}

	public void setDescription(String description) {
		designation = description;
	}

	public Date getDate_annonce() {
		return date_annonce;
	}

	public void setDate_annonce(Date date_annonce) {
		this.date_annonce = date_annonce;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public CommonsMultipartFile getPhotoPart() {
		return photoPart;
	}

	public void setPhotoPart(CommonsMultipartFile photoPart) {
		this.photoPart = photoPart;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


}
