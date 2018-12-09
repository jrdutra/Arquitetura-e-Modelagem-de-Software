package br.edu.fsma.banconucleoweb.excluido.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import br.edu.fsma.banconucleoweb.modelo.dao.DAO;
import br.edu.fsma.banconucleoweb.excluido.negocio.TransferenciaCaixaExcluido;

public class TransferenciaCaixaExcluidoDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<TransferenciaCaixaExcluido> dao;
	
	private EntityManager em;
	
	public TransferenciaCaixaExcluidoDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<TransferenciaCaixaExcluido>(this.em, TransferenciaCaixaExcluido.class);
	}
	
	public void adiciona(TransferenciaCaixaExcluido transferenciaCaixa) {
		this.dao.adiciona(transferenciaCaixa);
	}

	public void atualiza(TransferenciaCaixaExcluido transferenciaCaixa){
		this.dao.atualiza(transferenciaCaixa);
	}

	public void remove(TransferenciaCaixaExcluido transferenciaCaixa) {
		this.dao.remove(transferenciaCaixa);
	}

	public TransferenciaCaixaExcluido buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<TransferenciaCaixaExcluido> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<TransferenciaCaixaExcluido>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<TransferenciaCaixaExcluido> listaTodos() {
		return (ArrayList<TransferenciaCaixaExcluido>) this.dao.listaTodos();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
}
