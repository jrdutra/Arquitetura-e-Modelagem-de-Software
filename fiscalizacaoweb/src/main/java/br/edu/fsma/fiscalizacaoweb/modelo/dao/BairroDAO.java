package br.edu.fsma.fiscalizacaoweb.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Bairro;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Municipio;

public class BairroDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<Bairro> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Bairro>(this.em, Bairro.class);
	}
	
	@Inject
	private EntityManager em;
	
	public boolean existe(Bairro bairro) {
		@SuppressWarnings("unused")
		Bairro resultado = new Bairro();
		TypedQuery<Bairro> query = em.createQuery("SELECT b FROM Bairro b WHERE b.nome=:pNome", Bairro.class);
		query.setParameter("pNome", bairro.getNome());
		try {
			resultado = query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}
	
	public Bairro buscaBairroPorNome(Bairro bairro) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select b from Bairro b ");
		jpql.append(" where ");
		jpql.append(" b.nome = :pNome ");
		
		TypedQuery<Bairro> query = em.createQuery(jpql.toString() , Bairro.class);
		
		query.setParameter("pNome", bairro.getNome());
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public void adiciona(Bairro bairro) {
		this.dao.adiciona(bairro);
	}

	public void atualiza(Bairro bairro){
		this.dao.atualiza(bairro);
	}

	public void remove(Bairro bairro) {
		this.dao.remove(bairro);
	}

	public Bairro buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<Bairro> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<Bairro>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}
	
	public ArrayList<Bairro> buscaListaBairroPorNome(Bairro bairro) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select b from Bairro b ");
		jpql.append(" where ");
		jpql.append(" b.nome like :pNome");
		TypedQuery<Bairro> query = em.createQuery(jpql.toString() , Bairro.class);
		query.setParameter("pNome", bairro.getNome());
		try {
			return (ArrayList<Bairro>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
}
