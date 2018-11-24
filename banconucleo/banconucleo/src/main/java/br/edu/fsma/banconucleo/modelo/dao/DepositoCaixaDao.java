package br.edu.fsma.banconucleo.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.banconucleo.modelo.negocio.Conta;
import br.edu.fsma.banconucleo.modelo.negocio.DepositoCaixa;

public class DepositoCaixaDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<DepositoCaixa> dao;
	
	private EntityManager em;
	
	public DepositoCaixaDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<DepositoCaixa>(this.em, DepositoCaixa.class);
	}
	
	public void adiciona(DepositoCaixa depositoCaixa) {
		this.dao.adiciona(depositoCaixa);
	}

	public void atualiza(DepositoCaixa depositoCaixa){
		this.dao.atualiza(depositoCaixa);
	}

	public void remove(DepositoCaixa depositoCaixa) {
		this.dao.remove(depositoCaixa);
	}

	public DepositoCaixa buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<DepositoCaixa> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<DepositoCaixa>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<DepositoCaixa> listaTodos() {
		return (ArrayList<DepositoCaixa>) this.dao.listaTodos();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}

	public List<DepositoCaixa> listaTodosPorConta(Conta conta) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select d from DepositoCaixa d ");
		jpql.append(" where ");
		jpql.append(" d.conta like :pConta");
		TypedQuery<DepositoCaixa> query = em.createQuery(jpql.toString() , DepositoCaixa.class);
		query.setParameter("pConta", conta);
		try {
			return (ArrayList<DepositoCaixa>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public void excluiLista(List<DepositoCaixa> listaDepositoCaixa) {
		for(int i = 0; i < listaDepositoCaixa.size(); i++) {
			this.dao.remove(listaDepositoCaixa.get(i));
		}		
	}
}
