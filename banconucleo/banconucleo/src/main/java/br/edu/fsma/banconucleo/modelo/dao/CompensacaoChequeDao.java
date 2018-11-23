package br.edu.fsma.banconucleo.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.banconucleo.modelo.negocio.CompensacaoCheque;
import br.edu.fsma.banconucleo.modelo.negocio.Conta;

public class CompensacaoChequeDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<CompensacaoCheque> dao;
	
	private EntityManager em;
	
	public CompensacaoChequeDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<CompensacaoCheque>(this.em, CompensacaoCheque.class);
	}
	
	public void adiciona(CompensacaoCheque compensacaoCheque) {
		this.dao.adiciona(compensacaoCheque);
	}

	public void atualiza(CompensacaoCheque compensacaoCheque){
		this.dao.atualiza(compensacaoCheque);
	}

	public void remove(CompensacaoCheque compensacaoCheque) {
		this.dao.remove(compensacaoCheque);
	}

	public CompensacaoCheque buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<CompensacaoCheque> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<CompensacaoCheque>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<CompensacaoCheque> listaTodos() {
		return (ArrayList<CompensacaoCheque>) this.dao.listaTodos();
	}
	
	public List<CompensacaoCheque> listaTodosPorConta(Conta conta) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select c from CompensacaoCheque c ");
		jpql.append(" where ");
		jpql.append(" c.conta like :pConta");
		TypedQuery<CompensacaoCheque> query = em.createQuery(jpql.toString() , CompensacaoCheque.class);
		query.setParameter("pConta", conta);
		try {
			return (ArrayList<CompensacaoCheque>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}

	public void excluiLista(List<CompensacaoCheque> listaCompensacaoCheque) {
		for(int i = 0; i < listaCompensacaoCheque.size(); i++) {
			this.dao.remove(listaCompensacaoCheque.get(i));
		}
	}
	
}
