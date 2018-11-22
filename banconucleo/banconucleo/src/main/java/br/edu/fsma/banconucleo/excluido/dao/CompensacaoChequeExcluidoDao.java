package br.edu.fsma.banconucleo.excluido.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import br.edu.fsma.banconucleo.modelo.dao.DAO;
import br.edu.fsma.banconucleo.excluido.negocio.CompensacaoChequeExcluido;

public class CompensacaoChequeExcluidoDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<CompensacaoChequeExcluido> dao;
	
	private EntityManager em;
	
	public CompensacaoChequeExcluidoDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<CompensacaoChequeExcluido>(this.em, CompensacaoChequeExcluido.class);
	}
	
	public void adiciona(CompensacaoChequeExcluido compensacaoCheque) {
		this.dao.adiciona(compensacaoCheque);
	}

	public void atualiza(CompensacaoChequeExcluido compensacaoCheque){
		this.dao.atualiza(compensacaoCheque);
	}

	public void remove(CompensacaoChequeExcluido compensacaoCheque) {
		this.dao.remove(compensacaoCheque);
	}

	public CompensacaoChequeExcluido buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<CompensacaoChequeExcluido> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<CompensacaoChequeExcluido>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<CompensacaoChequeExcluido> listaTodos() {
		return (ArrayList<CompensacaoChequeExcluido>) this.dao.listaTodos();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
	
}
