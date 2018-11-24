package br.edu.fsma.banconucleo.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import br.edu.fsma.banconucleo.modelo.negocio.Conta;
import br.edu.fsma.banconucleo.modelo.negocio.TransferenciaCaixa;

public class TransferenciaCaixaDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<TransferenciaCaixa> dao;
	
	private EntityManager em;
	
	public TransferenciaCaixaDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<TransferenciaCaixa>(this.em, TransferenciaCaixa.class);
	}
	
	public void adiciona(TransferenciaCaixa transferenciaCaixa) {
		this.dao.adiciona(transferenciaCaixa);
	}

	public void atualiza(TransferenciaCaixa transferenciaCaixa){
		this.dao.atualiza(transferenciaCaixa);
	}

	public void remove(TransferenciaCaixa transferenciaCaixa) {
		this.dao.remove(transferenciaCaixa);
	}

	public TransferenciaCaixa buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<TransferenciaCaixa> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<TransferenciaCaixa>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<TransferenciaCaixa> listaTodos() {
		return (ArrayList<TransferenciaCaixa>) this.dao.listaTodos();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}

	public List<TransferenciaCaixa> listaTodosPorConta(Conta conta) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select t from TransferenciaCaixa t ");
		jpql.append(" where ");
		jpql.append(" t.conta like :pConta");
		TypedQuery<TransferenciaCaixa> query = em.createQuery(jpql.toString() , TransferenciaCaixa.class);
		query.setParameter("pConta", conta);
		try {
			return (ArrayList<TransferenciaCaixa>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public void excluiLista(List<TransferenciaCaixa> listaTransferenciaCaixa) {
		for(int i = 0; i < listaTransferenciaCaixa.size(); i++) {
			this.dao.remove(listaTransferenciaCaixa.get(i));
		}	
	}
}
