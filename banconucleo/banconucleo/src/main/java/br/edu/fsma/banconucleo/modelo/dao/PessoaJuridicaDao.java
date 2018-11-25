package br.edu.fsma.banconucleo.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.banconucleo.modelo.negocio.PessoaJuridica;

public class PessoaJuridicaDao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private DAO<PessoaJuridica> dao;
	
	private EntityManager em;
	
	public PessoaJuridicaDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<PessoaJuridica>(this.em, PessoaJuridica.class);
	}
	
	public boolean existe(PessoaJuridica empresa) {
		@SuppressWarnings("unused")
		PessoaJuridica resultado = new PessoaJuridica();
		System.out.println(empresa.getCnpj());
		TypedQuery<PessoaJuridica> query = em.createQuery("SELECT e FROM PessoaJuridica e WHERE e.cnpj=:pCnpj", PessoaJuridica.class);
		query.setParameter("pCnpj", empresa.getCnpj());

		try {
			resultado = query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}
	
	public PessoaJuridica buscaPessoaPeloCnpj(String cnpj) {
		String jpql = "select p from PessoaJuridica p where p.cnpj like :pCnpj";
		TypedQuery<PessoaJuridica> query = em.createQuery(jpql, PessoaJuridica.class);
		query.setParameter("pCnpj", cnpj);
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public PessoaJuridica buscaEmpresaPeloCNPJ(PessoaJuridica empresa) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select e from PessoaJuridica e ");
		jpql.append(" where ");
		jpql.append("       e.cnpj = :pCnpj ");
		
		TypedQuery<PessoaJuridica> query = em.createQuery(jpql.toString() , PessoaJuridica.class);
		
		query.setParameter("pCnpj", empresa.getCnpj());
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public PessoaJuridica buscaEmpresaPeloCNPJ(String cnpj) {
		String jpql = "select e from PessoaJuridica e where e.cnpj = :pCnpj";
		TypedQuery<PessoaJuridica> query = em.createQuery(jpql, PessoaJuridica.class);
		query.setParameter("pCnpj", cnpj);
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public List<PessoaJuridica> buscaListaPessoaJuridicaPorCnpj(PessoaJuridica pessoaJuridica) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select p from PessoaJuridica p ");
		jpql.append(" where ");
		jpql.append(" p.cnpj like :pCnpj");
		TypedQuery<PessoaJuridica> query = em.createQuery(jpql.toString() , PessoaJuridica.class);
		query.setParameter("pCnpj", pessoaJuridica.getCnpj());
		try {
			return (ArrayList<PessoaJuridica>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public void adiciona(PessoaJuridica empresa) {
		this.dao.adiciona(empresa);
	}

	public void atualiza(PessoaJuridica empresa){
		this.dao.atualiza(empresa);
	}

	public void remove(PessoaJuridica empresa) {
		this.dao.remove(empresa);
	}

	public PessoaJuridica buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<PessoaJuridica> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<PessoaJuridica>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}
	
	public List<PessoaJuridica> listaTodos() {
		return (ArrayList<PessoaJuridica>) this.dao.listaTodos();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}

	public PessoaJuridica getPessoaJuridica(Long idPessoaJuridica) {
		String jpql = "select p from PessoaJuridica p where p.id like :pId";
		TypedQuery<PessoaJuridica> query = em.createQuery(jpql.toString(), PessoaJuridica.class);
		query.setParameter("pId", idPessoaJuridica);
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
}
