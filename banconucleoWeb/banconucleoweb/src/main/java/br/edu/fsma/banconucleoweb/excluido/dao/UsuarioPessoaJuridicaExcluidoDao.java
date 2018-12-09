package br.edu.fsma.banconucleoweb.excluido.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import br.edu.fsma.banconucleoweb.modelo.dao.DAO;
import br.edu.fsma.banconucleoweb.excluido.negocio.UsuarioPessoaJuridicaExcluido;

public class UsuarioPessoaJuridicaExcluidoDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<UsuarioPessoaJuridicaExcluido> dao;
	
	private EntityManager em;
	
	public UsuarioPessoaJuridicaExcluidoDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<UsuarioPessoaJuridicaExcluido>(this.em, UsuarioPessoaJuridicaExcluido.class);
	}
	
	public void adiciona(UsuarioPessoaJuridicaExcluido usuarioPessoaJuridica) {
		this.dao.adiciona(usuarioPessoaJuridica);
	}

	public void atualiza(UsuarioPessoaJuridicaExcluido usuarioPessoaJuridica){
		this.dao.atualiza(usuarioPessoaJuridica);
	}

	public void remove(UsuarioPessoaJuridicaExcluido usuarioPessoaJuridica) {
		this.dao.remove(usuarioPessoaJuridica);
	}

	public UsuarioPessoaJuridicaExcluido buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<UsuarioPessoaJuridicaExcluido> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<UsuarioPessoaJuridicaExcluido>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<UsuarioPessoaJuridicaExcluido> listaTodos() {
		return (ArrayList<UsuarioPessoaJuridicaExcluido>) this.dao.listaTodos();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
}
