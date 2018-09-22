package br.edu.fsma.fiscalizacaoweb.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Uf;

public class UfDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<Uf> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Uf>(this.em, Uf.class);
	}
	
	@Inject
	private EntityManager em;
	
	public boolean existe(Uf uf) {
		@SuppressWarnings("unused")
		Uf resultado = new Uf();
		TypedQuery<Uf> query = em.createQuery("SELECT u FROM Uf u WHERE u.nome=:pNome", Uf.class);
		query.setParameter("pNome", uf.getNome());
		try {
			resultado = query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}
	public Uf buscaUfPorNome(Uf uf) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select u from Uf u ");
		jpql.append(" where ");
		jpql.append(" u.nome = :pNome ");
		TypedQuery<Uf> query = em.createQuery(jpql.toString() , Uf.class);
		query.setParameter("pNome", uf.getNome());
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	public Uf buscaUfPorNome(String nome) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select u from Uf u ");
		jpql.append(" where ");
		jpql.append(" u.nome = :pNome ");
		TypedQuery<Uf> query = em.createQuery(jpql.toString() , Uf.class);
		query.setParameter("pNome", nome);
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public void escreveUf(Uf uf) {
		
		Uf ufbuscada = buscaUfPorNomeSigla(uf);
		System.out.println("[ESCREVE UF] UF buscada: " + ufbuscada);
		if(ufbuscada != null) {
			ufbuscada.setNome(uf.getNome());
			ufbuscada.setSigla(uf.getSigla());
			System.out.println("Atualizou Uf");
			this.atualiza(ufbuscada);
		}else {
			System.out.println("Adicionou NOVA UF");
			this.adiciona(uf);
		}
	}
	
	public void adiciona(Uf uf) {
		this.dao.adiciona(uf);
	}

	public void atualiza(Uf uf){
		this.dao.atualiza(uf);
	}

	public void remove(Uf uf) {
		this.dao.remove(uf);
	}

	public Uf buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<Uf> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<Uf>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}
	
	public ArrayList<Uf> buscaListaUfPorNomeSigla(Uf uf) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select u from Uf u ");
		if(uf.getNome() != "" || uf.getSigla() != "") {
			jpql.append(" where ");
		}
		if(uf.getNome() != "") {
			jpql.append(" u.nome = :pNome");
		}
		if(uf.getNome() != "" && uf.getSigla() != "") {
			jpql.append(" AND ");
		}
		if(uf.getSigla() != "") {
			jpql.append("u.sigla = :pSigla");
		}
		TypedQuery<Uf> query = em.createQuery(jpql.toString() , Uf.class);
		if(uf.getNome() != "") {
			query.setParameter("pNome", uf.getNome());
		}
		if(uf.getSigla() != "") {
			query.setParameter("pSigla", uf.getSigla());
		}
		try {
			return (ArrayList<Uf>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public Uf buscaUfPorNomeSigla(Uf uf) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select u from Uf u ");
		if(uf.getNome() != "" || uf.getSigla() != "") {
			jpql.append(" where ");
		}
		if(uf.getNome() != "") {
			jpql.append(" u.nome = :pNome");
		}
		if(uf.getNome() != "" && uf.getSigla() != "") {
			jpql.append(" AND ");
		}
		if(uf.getSigla() != "") {
			jpql.append("u.sigla = :pSigla");
		}
		TypedQuery<Uf> query = em.createQuery(jpql.toString() , Uf.class);
		if(uf.getNome() != "") {
			query.setParameter("pNome", uf.getNome());
		}
		if(uf.getSigla() != "") {
			query.setParameter("pSigla", uf.getSigla());
		}
		try {
			return (Uf) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	
}
