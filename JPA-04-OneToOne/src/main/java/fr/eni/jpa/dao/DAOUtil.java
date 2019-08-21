package fr.eni.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOUtil {
	private static EntityManagerFactory emf;

	static{
		emf = Persistence.createEntityManagerFactory("Mysql_UP");
	}
	
	public static EntityManager getEntityManager(){
		return emf.createEntityManager();
	}
	
	public static void close(){
		emf.close();
	}
}
