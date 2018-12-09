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
	private EntityManager em = JPAUtil.getEntityManager();
	
	public List<UsuarioPessoaFisica> getListaPessoaFisica() {
		usuarioPessoaFisicaDao = new UsuarioPessoaFisicaDao(em);
		return usuarioPessoaFisicaDao.listaTodos();
	}

	public List<UsuarioPessoaJuridica> getListaPessoaJuridica() {
		usuarioPessoaJuridicaDao = new UsuarioPessoaJuridicaDao(em);
		return usuarioPessoaJuridicaDao.listaTodos();
	}

}
