package br.edu.fsma.projetofiscalizacao.dao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.projetofiscalizacao.modelo.Uf;

public class UfDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<Uf> dao;
	//@Inject
	private EntityManager em;
	//no lugar do inject e postconstruct fiz o construtor instanciando o em e dao
	
	public UfDAO(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<Uf>(this.em, Uf.class);
	}
	
	public boolean existe(Uf uf) {
		@SuppressWarnings("unused")
		Uf resultado = new Uf();
		TypedQuery<Uf> query = em.createQuery(
				  " select u from tbuf u "
				+ " where u.Nome = :pNome", Uf.class);
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
		jpql.append(" select u from tbuf u ");
		jpql.append(" where ");
		jpql.append("       u.nome = :pNome ");
		
		TypedQuery<Uf> query = em.createQuery(jpql.toString() , Uf.class);
		
		query.setParameter("pNome", uf.getNome());
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
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
	
}
