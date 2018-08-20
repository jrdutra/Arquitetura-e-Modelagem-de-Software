package com.fsma.projetoempresa.conexao;

//import javax.enterprise.inject.Disposes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres-local");

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void close(EntityManager em) {
		em.close();
	}

}