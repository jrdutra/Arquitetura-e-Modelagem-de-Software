package br.edu.fsma.banconucleoweb.modelo.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;


public class DAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Class<T> classe;
	private EntityManager em;

	public DAO(EntityManager em, Class<T> classe) {
		this.classe = classe;
		this.em = em;
	}

	public void adiciona(T empresa) {
		em.persist(empresa);
	}

	public void remove(T empresa) {
		em.remove(em.merge(empresa));
	}

	public void atualiza(T empresa) {
		em.merge(empresa);
	}

	public List<T> listaTodos() {

		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		return lista;
	}

	public T buscaPorId(Long id) {
		T instancia = em.find(classe, id);
		return instancia;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {

		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

		return lista;
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
	
	
}
