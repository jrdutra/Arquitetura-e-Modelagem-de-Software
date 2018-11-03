package br.edu.fsma.banconucleo.gerenciador;

import java.util.List;

import javax.persistence.EntityManager;

import br.edu.fsma.banconucleo.conexao.JPAUtil;
import br.edu.fsma.banconucleo.modelo.dao.UsuarioPessoaFisicaDao;
import br.edu.fsma.banconucleo.modelo.dao.UsuarioPessoaJuridicaDao;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaFisica;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaJuridica;

public class GerenciadorUsuarioPessoa {
	private UsuarioPessoaFisicaDao usuarioPessoaFisicaDao;
	private UsuarioPessoaJuridicaDao usuarioPessoaJuridicaDao;
	private EntityManager em;
	
	public List<UsuarioPessoaFisica> getListaPessoaFisica() {
		this.em = JPAUtil.getEntityManager();
		usuarioPessoaFisicaDao = new UsuarioPessoaFisicaDao(em);
		return usuarioPessoaFisicaDao.listaTodos();
	}

	public List<UsuarioPessoaJuridica> getListaPessoaJuridica() {
		this.em = JPAUtil.getEntityManager();
		usuarioPessoaJuridicaDao = new UsuarioPessoaJuridicaDao(em);
		return usuarioPessoaJuridicaDao.listaTodos();
	}

}
