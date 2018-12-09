package br.edu.fsma.banconucleoweb.excluido.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import br.edu.fsma.banconucleoweb.modelo.dao.DAO;
import br.edu.fsma.banconucleoweb.excluido.negocio.DepositoCaixaExcluido;

public class DepositoCaixaExcluidoDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<DepositoCaixaExcluido> dao;
	
	private EntityManager em;
	
	public DepositoCaixaExcluidoDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<DepositoCaixaExcluido>(this.em, DepositoCaixaExcluido.class);
	}
	
	public void adiciona(DepositoCaixaExcluido depositoCaixa) {
		this.dao.adiciona(depositoCaixa);
	}

	public void atualiza(DepositoCaixaExcluido depositoCaixa){
		this.dao.atualiza(depositoCaixa);
	}

	public void remove(DepositoCaixaExcluido depositoCaixa) {
		this.dao.remove(depositoCaixa);
	}

	public DepositoCaixaExcluido buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<DepositoCaixaExcluido> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<DepositoCaixaExcluido>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<DepositoCaixaExcluido> listaTodos() {
		return (ArrayList<DepositoCaixaExcluido>) this.dao.listaTodos();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
}
