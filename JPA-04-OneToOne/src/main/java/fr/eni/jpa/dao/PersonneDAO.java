package fr.eni.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.eni.jpa.entity.Personne;

public class PersonneDAO {

	public Personne findById(int id){
		return DAOUtil.getEntityManager().find(Personne.class, id);
	}

	public List<Personne> findAll(){
		String requete = "select Object(p) from Personne p";
		return DAOUtil.getEntityManager().createQuery(requete).getResultList();
	}

	public void add(Personne p) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(p);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
	}
	public void delete(Personne p) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		p = em.find(Personne.class, p.getId());
		try {
			et.begin();
			em.remove(p);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
	}

	
	public void update(Personne p) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		Personne aModif = findById(p.getId());
		
		aModif.setNom(p.getNom());
		aModif.setPrenom(p.getPrenom());
		aModif.setAdresse(p.getAdresse());
		try {
			et.begin();
			em.merge(aModif);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
		
	}
}
