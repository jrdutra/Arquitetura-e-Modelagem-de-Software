package br.edu.fsma.banconucleo.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.banconucleo.modelo.negocio.Conta;

public class ContaDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<Conta> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Conta>(this.em, Conta.class);
	}
	
	@Inject
	private EntityManager em;
	
	public boolean existe(Conta conta) {
		@SuppressWarnings("unused")
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
	
	public ArrayList<Conta> buscaListaContaPorNome(String agencia, String numero) {
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
		return this.dao.buscaPorId(id);
	}

	public ArrayList<Conta> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<Conta>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<Conta> listaTodos() {
		return (ArrayList<Conta>) this.dao.listaTodos();
	}
	
}