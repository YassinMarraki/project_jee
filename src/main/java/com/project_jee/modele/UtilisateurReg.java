package com.project_jee.modele;

public class UtilisateurReg extends Utilisateur {
	
	private String conf_mot_de_passe;
	private String conf_email;
	private boolean regle;
	
	
	
	public String getConf_mot_de_passe() {
		return conf_mot_de_passe;
	}
	public void setConf_mot_de_passe(String conf_mot_de_passe) {
		this.conf_mot_de_passe = conf_mot_de_passe;
	}
	public String getConf_email() {
		return conf_email;
	}
	public void setConf_email(String conf_email) {
		this.conf_email = conf_email;
	}
	public boolean getRegle() {
		return regle;
	}
	public void setRegle(boolean regle) {
		this.regle = regle;
	}


}