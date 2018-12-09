package br.edu.fsma.banconucleoweb.gerenciador;

import java.util.List;

import javax.persistence.EntityManager;

import br.edu.fsma.banconucleoweb.conexao.JPAUtil;
import br.edu.fsma.banconucleoweb.modelo.dao.UsuarioPessoaFisicaDao;
import br.edu.fsma.banconucleoweb.modelo.dao.UsuarioPessoaJuridicaDao;
import br.edu.fsma.banconucleoweb.modelo.negocio.UsuarioPessoaFisica;
import br.edu.fsma.banconucleoweb.modelo.negocio.UsuarioPessoaJuridica;

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
