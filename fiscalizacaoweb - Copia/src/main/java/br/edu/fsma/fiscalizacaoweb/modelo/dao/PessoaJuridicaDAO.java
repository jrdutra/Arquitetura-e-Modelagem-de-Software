package br.edu.fsma.fiscalizacaoweb.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.fiscalizacaoweb.modelo.dao.DAO;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.PessoaJuridica;

public class PessoaJuridicaDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private DAO<PessoaJuridica> dao;
	//@Inject
	private EntityManager em;
	//no lugar do inject e postconstruct fiz o construtor instanciando o em e dao
	
	public PessoaJuridicaDAO(EntityManager em2) {
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

}
