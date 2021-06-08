package com.project_jee.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project_jee.modele.Annonce;

@Service
@Transactional
public class AnnonceService {
	@Autowired
	private AnnonceDAO annonceDAO;

	public List<Annonce> listAll() {
		return annonceDAO.findAll();
	}

	

	public Annonce get(long id) {
		return annonceDAO.findById(id).get();
	}

	public void delete(long id) {
		annonceDAO.deleteById(id);
	}

	@Autowired
	private SessionFactory sessionFactory;

	public AnnonceService() {
	}

	public AnnonceService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(Annonce annonce) {
		sessionFactory.openSession().save(annonce);
		annonceDAO.save(annonce);
	}

}
