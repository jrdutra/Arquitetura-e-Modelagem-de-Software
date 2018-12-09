package br.edu.fsma.banconucleoweb.conexao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.edu.fsma.banconucleoweb.gerenciador.GerenciadorLogin2;

public class JPAUtil {
	
	private static EntityManagerFactory emf = Persistence
		.createEntityManagerFactory("mysql-local");

	
	
	@Produces
	@RequestScoped
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	@Produces
	@RequestScoped
	public static GerenciadorLogin2 getGerenciadorLogin2() {
		return new GerenciadorLogin2();
	}

	public void close(@Disposes EntityManager em) {
		em.close();
	}

}