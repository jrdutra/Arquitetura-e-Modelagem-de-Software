package br.edu.fsma.banconucleo.excluido.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import br.edu.fsma.banconucleo.modelo.dao.DAO;
import br.edu.fsma.banconucleo.modelo.negocio.SaqueCaixa;

public class SaqueCaixaExcluidoDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<SaqueCaixa> dao;
	
	private EntityManager em;
	
	public SaqueCaixaExcluidoDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<SaqueCaixa>(this.em, SaqueCaixa.class);
	}
	
	public void adiciona(SaqueCaixa saqueCaixa) {
		this.dao.adiciona(saqueCaixa);
	}

	public void atualiza(SaqueCaixa saqueCaixa){
		this.dao.atualiza(saqueCaixa);
	}

	public void remove(SaqueCaixa saqueCaixa) {
		this.dao.remove(saqueCaixa);
	}

	public SaqueCaixa buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<SaqueCaixa> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<SaqueCaixa>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<SaqueCaixa> listaTodos() {
		return (ArrayList<SaqueCaixa>) this.dao.listaTodos();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
	
}
