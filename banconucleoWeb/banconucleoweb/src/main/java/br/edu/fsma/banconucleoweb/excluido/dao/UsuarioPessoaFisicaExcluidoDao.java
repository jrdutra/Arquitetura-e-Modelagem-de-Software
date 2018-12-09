package br.edu.fsma.banconucleoweb.excluido.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import br.edu.fsma.banconucleoweb.modelo.dao.DAO;
import br.edu.fsma.banconucleoweb.excluido.negocio.UsuarioPessoaFisicaExcluido;

public class UsuarioPessoaFisicaExcluidoDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<UsuarioPessoaFisicaExcluido> dao;
	
	private EntityManager em;
	
	public UsuarioPessoaFisicaExcluidoDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<UsuarioPessoaFisicaExcluido>(this.em, UsuarioPessoaFisicaExcluido.class);
	}
	
	public void adiciona(UsuarioPessoaFisicaExcluido usuarioPessoaFisica) {
		this.dao.adiciona(usuarioPessoaFisica);
	}

	public void atualiza(UsuarioPessoaFisicaExcluido usuarioPessoaFisica){
		this.dao.atualiza(usuarioPessoaFisica);
	}

	public void remove(UsuarioPessoaFisicaExcluido usuarioPessoaFisica) {
		this.dao.remove(usuarioPessoaFisica);
	}

	public UsuarioPessoaFisicaExcluido buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<UsuarioPessoaFisicaExcluido> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<UsuarioPessoaFisicaExcluido>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<UsuarioPessoaFisicaExcluido> listaTodos() {
		return (ArrayList<UsuarioPessoaFisicaExcluido>) this.dao.listaTodos();
	}
	public EntityManager getEntityManager() {
		return this.em;
	}
}
