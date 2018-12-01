package br.edu.fsma.banconucleo.gerenciador;

import java.util.List;

import javax.persistence.EntityManager;

import br.edu.fsma.banconucleo.conexao.JPAUtil;
import br.edu.fsma.banconucleo.modelo.dao.PessoaFisicaDao;
import br.edu.fsma.banconucleo.modelo.dao.PessoaJuridicaDao;
import br.edu.fsma.banconucleo.modelo.negocio.PessoaFisica;
import br.edu.fsma.banconucleo.modelo.negocio.PessoaJuridica;


public class GerenciadorPessoa {
	private PessoaFisicaDao pessoaFisicaDao;
	private PessoaJuridicaDao pessoaJuridicaDao;
	private EntityManager em;
	
	public List<PessoaFisica> getListaPessoaFisica() {
		this.em = JPAUtil.getEntityManager();
		pessoaFisicaDao = new PessoaFisicaDao(em);
		return pessoaFisicaDao.listaTodos();
	}

	public List<PessoaJuridica> getListaPessoaJuridica() {
		this.em = JPAUtil.getEntityManager();
		pessoaJuridicaDao = new PessoaJuridicaDao(em);
		return pessoaJuridicaDao.listaTodos();
	}
}
