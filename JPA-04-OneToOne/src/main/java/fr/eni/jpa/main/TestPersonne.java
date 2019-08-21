package fr.eni.jpa.main;

import java.util.List;

import fr.eni.jpa.dao.DAOUtil;
import fr.eni.jpa.dao.PersonneDAO;
import fr.eni.jpa.entity.Adresse;
import fr.eni.jpa.entity.Personne;

public class TestPersonne {

	public static void main(String[] args) {

		Adresse a1 = new Adresse("44000", "Nantes");
		Adresse a2 = new Adresse("33000", "Bordeaux");
		Personne p1 = new Personne("Lebrun", "Jeanne", a1);
		Personne p2 = new Personne("Leblond", "Annie", a2);
		
		PersonneDAO dao = new PersonneDAO();
		try {
			dao.add(p1);
			dao.add(p2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		p1.getAdresse().setCodePostal("44100");
		try {
			dao.update(p1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Personne> listeP = dao.findAll();
		System.out.println("Liste des personnes (nom) : ");
		
		for (Personne personne : listeP) {
			System.out.println(personne.getNom());
		}
		System.out.println("Liste des personnes (adresse) : ");
		
		for (Personne personne : listeP) {
			System.out.println(personne.getAdresse());
		}
		
		System.out.println("suppression de p1 : ");
		try {
			dao.delete(p1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DAOUtil.close();
		
	}

}
