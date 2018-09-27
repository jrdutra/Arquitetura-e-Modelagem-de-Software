package br.edu.fsma.fiscalizacaoweb.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Municipio;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Uf;


public class MunicipioDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<Municipio> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Municipio>(this.em, Municipio.class);
	}
	
	@Inject
	private EntityManager em;
		
	public boolean existe(Uf uf, String nomeMunicipio) {
		Municipio municipio = buscaMunicipioPorNome(uf, nomeMunicipio);
		return (municipio != null);
	}
	
	public Municipio buscaMunicipioPorNome(Uf uf, String nomeMunicipio) {

		StringBuilder jpql = new StringBuilder();
		jpql.append(" select m from Municipio m ");
		jpql.append(" where ");
		jpql.append("       m.nome = :pNome ");
		jpql.append("       and m.uf = :pUf ");
		
		TypedQuery<Municipio> query = em.createQuery(jpql.toString() , Municipio.class);
		
		query.setParameter("pNome", nomeMunicipio);
		query.setParameter("pUf", uf);
		
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public void adiciona(Municipio municipio) {
		this.dao.adiciona(municipio);
	}

	public void atualiza(Municipio municipio){
		this.dao.atualiza(municipio);
	}
	
	public void persiste(Municipio municipio) {
		if (municipio.getId() == null) {
			adiciona(municipio);
		} else {
			atualiza(municipio);
		}
	}

	public void remove(Municipio municipio) {
		this.dao.remove(municipio);
	}

	public Municipio buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<Municipio> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<Municipio>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}
	
	public ArrayList<Municipio> buscaListaMunicipioPorNome(Municipio municipio) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select u from Municipio u ");
		jpql.append(" where ");
		jpql.append(" u.nome like :pNome");
		TypedQuery<Municipio> query = em.createQuery(jpql.toString() , Municipio.class);
		query.setParameter("pNome", municipio.getNome());
		try {
			return (ArrayList<Municipio>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public ArrayList<Municipio> listaTodos() {
		return (ArrayList<Municipio>) this.dao.listaTodos();
	}
	
}