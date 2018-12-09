package br.edu.fsma.banconucleoweb.excluido.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.banconucleoweb.modelo.dao.DAO;
import br.edu.fsma.banconucleoweb.excluido.negocio.ContaExcluido;

public class ContaExcluidoDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<ContaExcluido> dao;
	
	private EntityManager em;
	
	public ContaExcluidoDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<ContaExcluido>(this.em, ContaExcluido.class);
	}

	public boolean existe(ContaExcluido conta) {
		ContaExcluido resultado = new ContaExcluido();
		TypedQuery<ContaExcluido> query = em.createQuery("SELECT c FROM ContaExcluido c "
												+ "WHERE c.numero=:pNumero and "
												+ "c.agencia=:pAgencia", ContaExcluido.class);
	
		query.setParameter("pNumero", conta.getNumero());
		query.setParameter("pAgencia", conta.getAgencia());
		try {
			resultado = query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}
	
	public ContaExcluido buscaContaPorAngenciaNumero(String agencia, String numero) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select c from ContaExcluido c ");
		jpql.append(" where ");
		jpql.append(" c.agencia = :pAgencia and ");
		jpql.append(" c.numero = :pNumero");
		TypedQuery<ContaExcluido> query = em.createQuery(jpql.toString() , ContaExcluido.class);
		query.setParameter("pNumero", numero);
		query.setParameter("pAgencia", agencia);
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public ArrayList<ContaExcluido> buscaListaContaPorAngenciaNumero(String agencia, String numero) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select c from ContaExcluidoDao c ");
		jpql.append(" where ");
		jpql.append(" c.agencia = :pAgencia and ");
		jpql.append(" c.numero = :pNumero");
		TypedQuery<ContaExcluido> query = em.createQuery(jpql.toString() , ContaExcluido.class);
		query.setParameter("pNumero", numero);
		query.setParameter("pAgencia", agencia);
		try {
			return (ArrayList<ContaExcluido>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public void adiciona(ContaExcluido conta) {
		this.dao.adiciona(conta);
	}

	public void atualiza(ContaExcluido conta){
		this.dao.atualiza(conta);
	}

	public void remove(ContaExcluido conta) {
		this.dao.remove(conta);
	}

	public ContaExcluido buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<ContaExcluido> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<ContaExcluido>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<ContaExcluido> listaTodos() {
		return (ArrayList<ContaExcluido>) this.dao.listaTodos();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
}
