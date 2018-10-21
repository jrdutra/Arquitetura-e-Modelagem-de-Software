package br.edu.fsma.banconucleo.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
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
}
