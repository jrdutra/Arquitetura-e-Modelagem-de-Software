package br.edu.fsma.banconucleo.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
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
	
}
