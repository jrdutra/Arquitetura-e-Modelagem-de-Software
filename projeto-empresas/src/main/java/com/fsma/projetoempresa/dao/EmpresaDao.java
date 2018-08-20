package com.fsma.projetoempresa.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
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
	//no lugar do inject fiz o construtor recebendo o em
	public EmpresaDao(EntityManager em2) {
		this.em = em2;
	}

	@PostConstruct
	void init() {
		this.dao = new DAO<Empresa>(this.em, Empresa.class);
	}


	
	public boolean existe(Empresa empresa) {
		
		TypedQuery<Empresa> query = em.createQuery(
				  " select e from Empresa e "
				+ " where e.Cnpj = :pCnpj", Empresa.class);
		
		query.setParameter("pLogin", empresa.getCnpj());
		try {
			@SuppressWarnings("unused")
			Empresa resultado = query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}
	
	public Empresa buscaUsuarioPelaAutenticacao(Empresa empresa) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select u from Empresa e ");
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
		dao.adiciona(empresa);
	}

	public void atualiza(Empresa empresa){
		em.merge(empresa);
	}

	public void remove(Empresa empresa) {
		dao.remove(empresa);
	}

	public Empresa buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public List<Empresa> listaTodosPaginada(int firstResult, int maxResults) {
		return dao.listaTodosPaginada(firstResult, maxResults);
	}

	public Empresa buscaPorEmail(String cnpj) {
		String jpql = " select e from tb_empresa e where e.cnpj = :pCnpj";
		TypedQuery<Empresa> query = em.createQuery(jpql, Empresa.class);
		query.setParameter("pCnpj", cnpj.trim().toLowerCase());
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

}
