package br.edu.fsma.banconucleo.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaFisica;

public class UsuarioPessoaFisicaDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<UsuarioPessoaFisica> dao;
	
	private EntityManager em;
	
	public UsuarioPessoaFisicaDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<UsuarioPessoaFisica>(this.em, UsuarioPessoaFisica.class);
	}
	
	public void adiciona(UsuarioPessoaFisica usuarioPessoaFisica) {
		this.dao.adiciona(usuarioPessoaFisica);
	}

	public void atualiza(UsuarioPessoaFisica usuarioPessoaFisica){
		this.dao.atualiza(usuarioPessoaFisica);
	}

	public void remove(UsuarioPessoaFisica usuarioPessoaFisica) {
		this.dao.remove(usuarioPessoaFisica);
	}

	public UsuarioPessoaFisica buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<UsuarioPessoaFisica> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<UsuarioPessoaFisica>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<UsuarioPessoaFisica> listaTodos() {
		return (ArrayList<UsuarioPessoaFisica>) this.dao.listaTodos();
	}
	
}
