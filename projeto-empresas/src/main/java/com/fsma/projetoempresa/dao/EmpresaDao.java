package com.fsma.projetoempresa.dao;

import java.io.Serializable;
import java.util.List;

//import javax.annotation.PostConstruct;
//import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


import com.fsma.projetoempresa.dao.DAO;
import com.fsma.projetoempresa.modelo.Empresa;

public class EmpresaDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<Empresa> dao;
	//@Inject
	private EntityManager em;
	//no lugar do inject e postconstruct fiz o construtor instanciando o em e dao
	public EmpresaDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<Empresa>(this.em, Empresa.class);
	}

	/*@PostConstruct
	void init() {
		this.dao = new DAO<Empresa>(this.em, Empresa.class);
	}*/

	public boolean existe(Empresa empresa) {
		@SuppressWarnings("unused")
		Empresa resultado = new Empresa();
		TypedQuery<Empresa> query = em.createQuery(
				  " select e from tbempresa e "
				+ " where e.Cnpj = :pCnpj", Empresa.class);
		
		query.setParameter("pCnpj", empresa.getCnpj());
		try {
			
			resultado = query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}
	
	public Empresa buscaEmpresaPeloCNPJ(Empresa empresa) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select e from tbempresa e ");
		jpql.append(" where ");
		jpql.append("       e.cnpj = :pCnpj ");
		
		TypedQuery<Empresa> query = em.createQuery(jpql.toString() , Empresa.class);
		
		query.setParameter("pCnpj", empresa.getCnpj());
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public void adiciona(Empresa empresa) {
		this.dao.adiciona(empresa);
	}

	public void atualiza(Empresa empresa){
		this.dao.atualiza(empresa);
	}

	public void remove(Empresa empresa) {
		this.dao.remove(empresa);
	}

	public Empresa buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public List<Empresa> listaTodosPaginada(int firstResult, int maxResults) {
		return this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public Empresa buscaPorCNPJ(String cnpj) {
		String jpql = "select e from Empresa e where e.cnpj = :pCnpj";
		TypedQuery<Empresa> query = em.createQuery(jpql, Empresa.class);
		query.setParameter("pCnpj", cnpj);
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	public EntityManager getEntityManager() {
		return this.em;
	}

}
