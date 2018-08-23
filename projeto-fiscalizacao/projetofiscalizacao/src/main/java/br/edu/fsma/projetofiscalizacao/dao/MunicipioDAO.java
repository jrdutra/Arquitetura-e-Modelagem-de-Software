package br.edu.fsma.projetofiscalizacao.dao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.projetofiscalizacao.modelo.Municipio;
import br.edu.fsma.projetofiscalizacao.modelo.Uf;

public class MunicipioDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<Municipio> dao;
	//@Inject
	private EntityManager em;
	//no lugar do inject e postconstruct fiz o construtor instanciando o em e dao
	
	public MunicipioDAO(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<Municipio>(this.em, Municipio.class);
	}
	
	public boolean existe(Municipio municipio) {
		@SuppressWarnings("unused")
		Municipio resultado = new Municipio();
		TypedQuery<Municipio> query = em.createQuery(
				  " select m from tbmunicipio m "
				+ " where m.Nome = :pNome", Municipio.class);
		query.setParameter("pNome", municipio.getNome());
		try {
			resultado = query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}
	
	public Municipio buscaMunicipioPorNome(Municipio municipio) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select m from tbmunicipio m ");
		jpql.append(" where ");
		jpql.append("       m.nome = :pNome ");
		
		TypedQuery<Municipio> query = em.createQuery(jpql.toString() , Municipio.class);
		
		query.setParameter("pNome", municipio.getNome());
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

	public void remove(Municipio municipio) {
		this.dao.remove(municipio);
	}

	public Municipio buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<Municipio> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<Municipio>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}
	
}