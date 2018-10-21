package br.edu.fsma.banconucleo.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.edu.fsma.banconucleo.modelo.negocio.CompensacaoCheque;

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
	
}
