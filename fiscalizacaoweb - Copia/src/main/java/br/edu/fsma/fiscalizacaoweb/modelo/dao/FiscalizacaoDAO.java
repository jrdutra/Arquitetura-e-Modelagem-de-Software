package br.edu.fsma.fiscalizacaoweb.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.EntityManager;

import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Fiscalizacao;

public class FiscalizacaoDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	private DAO<Fiscalizacao> dao;
	//@Inject
	private EntityManager em;
	//no lugar do inject e postconstruct fiz o construtor instanciando o em e dao
	
	public FiscalizacaoDAO(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<Fiscalizacao>(this.em, Fiscalizacao.class);
	}
	
	public void adiciona(Fiscalizacao fiscalizacao) {
		this.dao.adiciona(fiscalizacao);
	}

	public void atualiza(Fiscalizacao fiscalizacao){
		this.dao.atualiza(fiscalizacao);
	}

	public void remove(Fiscalizacao fiscalizacao) {
		this.dao.remove(fiscalizacao);
	}

	public Fiscalizacao buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<Fiscalizacao> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<Fiscalizacao>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}
}
