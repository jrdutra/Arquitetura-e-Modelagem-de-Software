package br.edu.fsma.banconucleo.excluido.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import br.edu.fsma.banconucleo.modelo.dao.DAO;
import br.edu.fsma.banconucleo.excluido.negocio.SaqueCaixaExcluido;

public class SaqueCaixaExcluidoDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<SaqueCaixaExcluido> dao;
	
	private EntityManager em;
	
	public SaqueCaixaExcluidoDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<SaqueCaixaExcluido>(this.em, SaqueCaixaExcluido.class);
	}
	
	public void adiciona(SaqueCaixaExcluido saqueCaixa) {
		this.dao.adiciona(saqueCaixa);
	}

	public void atualiza(SaqueCaixaExcluido saqueCaixa){
		this.dao.atualiza(saqueCaixa);
	}

	public void remove(SaqueCaixaExcluido saqueCaixa) {
		this.dao.remove(saqueCaixa);
	}

	public SaqueCaixaExcluido buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<SaqueCaixaExcluido> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<SaqueCaixaExcluido>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<SaqueCaixaExcluido> listaTodos() {
		return (ArrayList<SaqueCaixaExcluido>) this.dao.listaTodos();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
	
}
