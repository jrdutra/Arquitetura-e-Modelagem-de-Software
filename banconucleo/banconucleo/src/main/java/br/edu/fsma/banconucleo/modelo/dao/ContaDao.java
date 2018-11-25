package br.edu.fsma.banconucleo.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import br.edu.fsma.banconucleo.modelo.negocio.Conta;
import br.edu.fsma.banconucleo.modelo.negocio.PessoaFisica;

public class ContaDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<Conta> dao;
	
	private EntityManager em;
	
	public ContaDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<Conta>(this.em, Conta.class);
	}

	public boolean existe(Conta conta) {
		Conta resultado = new Conta();
		TypedQuery<Conta> query = em.createQuery("SELECT c FROM Conta c "
												+ "WHERE c.numero=:pNumero and "
												+ "c.agencia=:pAgencia", Conta.class);
	
		query.setParameter("pNumero", conta.getNumero());
		query.setParameter("pAgencia", conta.getAgencia());
		try {
			resultado = query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}
	
	public Conta buscaContaPorAngenciaNumero(String agencia, String numero) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select c from Conta c ");
		jpql.append(" where ");
		jpql.append(" c.agencia = :pAgencia and ");
		jpql.append(" c.numero = :pNumero");
		TypedQuery<Conta> query = em.createQuery(jpql.toString() , Conta.class);
		query.setParameter("pNumero", numero);
		query.setParameter("pAgencia", agencia);
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public ArrayList<Conta> buscaListaContaPorAngenciaNumero(String agencia, String numero) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select c from Conta c ");
		jpql.append(" where ");
		jpql.append(" c.agencia = :pAgencia and ");
		jpql.append(" c.numero = :pNumero");
		TypedQuery<Conta> query = em.createQuery(jpql.toString() , Conta.class);
		query.setParameter("pNumero", numero);
		query.setParameter("pAgencia", agencia);
		try {
			return (ArrayList<Conta>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public void adiciona(Conta conta) {
		this.dao.adiciona(conta);
	}

	public void atualiza(Conta conta){
		this.dao.atualiza(conta);
	}

	public void remove(Conta conta) {
		this.dao.remove(conta);
	}

	public Conta buscaPorId(Long id) {
		String jpql = "select c from Conta c where c.id like :pId";
		TypedQuery<Conta> query = em.createQuery(jpql.toString(), Conta.class);
		query.setParameter("pId", id);
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public ArrayList<Conta> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<Conta>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<Conta> listaTodos() {
		return (ArrayList<Conta>) this.dao.listaTodos();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}

	public Conta getConta(Long idConta) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select c from Conta c ");
		jpql.append(" where ");
		jpql.append(" c.id = :pId");
		TypedQuery<Conta> query = em.createQuery(jpql.toString() , Conta.class);
		query.setParameter("pNumero", idConta);
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
}
