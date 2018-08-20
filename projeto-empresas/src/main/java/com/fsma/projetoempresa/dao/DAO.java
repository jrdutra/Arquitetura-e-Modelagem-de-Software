package com.fsma.projetoempresa.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import com.fsma.projetoempresa.modelo.Empresa;

@SuppressWarnings("hiding")
public class DAO<Empresa> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Class<Empresa> classe;
	private EntityManager em;

	public DAO(EntityManager em, Class<Empresa> classe) {
		this.classe = classe;
		this.em = em;
	}

	public void adiciona(Empresa empresa) {
		em.persist(empresa);
	}

	public void remove(Empresa empresa) {
		em.remove(em.merge(empresa));
	}

	public void atualiza(Empresa empresa) {
		em.merge(empresa);
	}

	public List<Empresa> listaTodos() {

		CriteriaQuery<Empresa> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<Empresa> lista = em.createQuery(query).getResultList();

		return lista;
	}

	public Empresa buscaPorId(Long id) {
		Empresa instancia = em.find(classe, id);
		return instancia;
	}

	public List<Empresa> listaTodosPaginada(int firstResult, int maxResults) {

		CriteriaQuery<Empresa> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<Empresa> lista = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

		return lista;
	}

}
