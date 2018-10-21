package br.edu.fsma.banconucleo.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaJuridica;

public class UsuarioPessoaJuridicaDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<UsuarioPessoaJuridica> dao;
	
	private EntityManager em;
	
	public UsuarioPessoaJuridicaDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<UsuarioPessoaJuridica>(this.em, UsuarioPessoaJuridica.class);
	}
	
	public void adiciona(UsuarioPessoaJuridica usuarioPessoaJuridica) {
		this.dao.adiciona(usuarioPessoaJuridica);
	}

	public void atualiza(UsuarioPessoaJuridica usuarioPessoaJuridica){
		this.dao.atualiza(usuarioPessoaJuridica);
	}

	public void remove(UsuarioPessoaJuridica usuarioPessoaJuridica) {
		this.dao.remove(usuarioPessoaJuridica);
	}

	public UsuarioPessoaJuridica buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<UsuarioPessoaJuridica> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<UsuarioPessoaJuridica>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<UsuarioPessoaJuridica> listaTodos() {
		return (ArrayList<UsuarioPessoaJuridica>) this.dao.listaTodos();
	}
	
}
