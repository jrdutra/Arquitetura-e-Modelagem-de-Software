package br.edu.fsma.projetofiscalizacao.dao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.projetofiscalizacao.dao.DAO;
import br.edu.fsma.projetofiscalizacao.modelo.Empresa;

public class EmpresaDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private DAO<Empresa> dao;
	//@Inject
	private EntityManager em;
	//no lugar do inject e postconstruct fiz o construtor instanciando o em e dao
	
	public EmpresaDAO(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<Empresa>(this.em, Empresa.class);
	}
	
	public boolean existe(Empresa empresa) {
		@SuppressWarnings("unused")
		Empresa resultado = new Empresa();
		System.out.println(empresa.getCnpj());
		TypedQuery<Empresa> query = em.createQuery("SELECT e FROM Empresa e WHERE e.cnpj=:pCnpj", Empresa.class);
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
		jpql.append(" select e from Empresa e ");
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
	
	public Empresa buscaEmpresaPeloCNPJ(String cnpj) {
		String jpql = "select e from Empresa e where e.cnpj = :pCnpj";
		TypedQuery<Empresa> query = em.createQuery(jpql, Empresa.class);
		query.setParameter("pCnpj", cnpj);
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

	public ArrayList<Empresa> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<Empresa>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

}
