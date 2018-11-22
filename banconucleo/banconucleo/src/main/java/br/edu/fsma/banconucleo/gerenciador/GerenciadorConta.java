package br.edu.fsma.banconucleo.gerenciador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import br.edu.fsma.banconucleo.conexao.JPAUtil;
import br.edu.fsma.banconucleo.modelo.dao.CompensacaoChequeDao;
import br.edu.fsma.banconucleo.modelo.dao.ContaDao;
import br.edu.fsma.banconucleo.modelo.dao.DepositoCaixaDao;
import br.edu.fsma.banconucleo.modelo.dao.PessoaFisicaDao;
import br.edu.fsma.banconucleo.modelo.dao.PessoaJuridicaDao;
import br.edu.fsma.banconucleo.modelo.dao.SaqueCaixaDao;
import br.edu.fsma.banconucleo.modelo.dao.TransferenciaCaixaDao;
import br.edu.fsma.banconucleo.modelo.dao.UsuarioPessoaFisicaDao;
import br.edu.fsma.banconucleo.modelo.dao.UsuarioPessoaJuridicaDao;
import br.edu.fsma.banconucleo.modelo.negocio.CompensacaoCheque;
import br.edu.fsma.banconucleo.modelo.negocio.Conta;
import br.edu.fsma.banconucleo.modelo.negocio.DepositoCaixa;
import br.edu.fsma.banconucleo.modelo.negocio.PessoaFisica;
import br.edu.fsma.banconucleo.modelo.negocio.PessoaJuridica;
import br.edu.fsma.banconucleo.modelo.negocio.SaqueCaixa;
import br.edu.fsma.banconucleo.modelo.negocio.TransferenciaCaixa;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaFisica;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaJuridica;

public class GerenciadorConta {
	
	Random gerador = new Random();
	private EntityManager em;
	private ContaDao contaDao;
	private UsuarioPessoaFisicaDao usuarioPessoaFisicaDao;
	private PessoaFisicaDao pessoaFisicaDao;
	private PessoaJuridicaDao pessoaJuridicaDao;
	private UsuarioPessoaJuridicaDao usuarioPessoaJuridicaDao;
	
	
	
	public void guardarContaFisica(Conta conta, Long idPessoaFisica, String senha) {
		this.em = JPAUtil.getEntityManager();
		usuarioPessoaFisicaDao = new UsuarioPessoaFisicaDao(em);
		pessoaFisicaDao = new PessoaFisicaDao(em);
		contaDao = new ContaDao(em);
		
		
		//adiciona conta
		try {
			em.getTransaction().begin();
			contaDao.adiciona(conta);
			em.getTransaction().commit();
		}catch(Exception ex)  {
			System.out.println("\n\nErro ao gravar conta no banco.");
			em.getTransaction().rollback();
		}
		
		PessoaFisica pessoaFisica = pessoaFisicaDao.buscaPorId(idPessoaFisica);
		UsuarioPessoaFisica usuarioPessoaFisica = new UsuarioPessoaFisica();
		
		conta = contaDao.buscaContaPorAngenciaNumero(conta.getAgencia(), conta.getNumero());
		
		usuarioPessoaFisica.setConta(conta);
		usuarioPessoaFisica.setPessoaFisica(pessoaFisica);
		usuarioPessoaFisica.setSenha(senha);
		
		//adiciona usuario
		try {
			em.getTransaction().begin();
			System.out.println(conta);
			System.out.println(usuarioPessoaFisica);
			usuarioPessoaFisicaDao.adiciona(usuarioPessoaFisica);
			em.getTransaction().commit();
		}catch(Exception ex)  {
			System.out.println("\n\nErro ao gravar usuario no banco.");
			em.getTransaction().rollback();
		}
	}
	
	public void guardarContaJuridica(Conta conta, Long idPessoaJuridica, String senha) {
		this.em = JPAUtil.getEntityManager();
		usuarioPessoaJuridicaDao = new UsuarioPessoaJuridicaDao(em);
		pessoaJuridicaDao = new PessoaJuridicaDao(em);
		contaDao = new ContaDao(em);
		
		
		//adiciona conta
		try {
			em.getTransaction().begin();
			contaDao.adiciona(conta);
			em.getTransaction().commit();
		}catch(Exception ex)  {
			System.out.println("\n\nErro ao gravar conta no banco.");
			em.getTransaction().rollback();
		}
		
		PessoaJuridica pessoaJuridica = pessoaJuridicaDao.buscaPorId(idPessoaJuridica);
		UsuarioPessoaJuridica usuarioPessoaJuridica = new UsuarioPessoaJuridica();
		
		conta = contaDao.buscaContaPorAngenciaNumero(conta.getAgencia(), conta.getNumero());
		
		usuarioPessoaJuridica.setConta(conta);
		usuarioPessoaJuridica.setPessoaJuridica(pessoaJuridica);
		usuarioPessoaJuridica.setSenha(senha);
		
		//adiciona usuario
		try {
			em.getTransaction().begin();
			System.out.println(conta);
			System.out.println(usuarioPessoaJuridica);
			usuarioPessoaJuridicaDao.adiciona(usuarioPessoaJuridica);
			em.getTransaction().commit();
		}catch(Exception ex)  {
			System.out.println("\n\nErro ao gravar usuario no banco.");
			em.getTransaction().rollback();
		}
		
	}
	
	private String gerarNumeroAgencia() {
		 Integer numAgencia = gerador.nextInt(9999);
		 String agencia = "";
		 Integer digito = gerador.nextInt(9);
		 if(numAgencia<10) {
			 agencia = "000" + numAgencia.toString();
		 }else if(numAgencia<100) {
			 agencia = "00" + numAgencia.toString();
		 }else if(numAgencia<1000) {
			 agencia = "0" + numAgencia.toString();
		 }else if(numAgencia<10000) {
			 agencia = numAgencia.toString();
		 }
		 agencia = agencia + "-" + digito.toString();
		 return agencia;
	}
	
	private String gerarNumeroConta() {
		 Integer numConta = gerador.nextInt(99999);
		 String conta = "";
		 Integer digito = gerador.nextInt(9);
		 if(numConta<10) {
			 conta = "00000" + numConta.toString();
		 }else if(numConta<10) {
			 conta = "0000" + numConta.toString();
		 }else if(numConta<100) {
			 conta = "000" + numConta.toString();
		 }else if(numConta<1000) {
			 conta = "00" + numConta.toString();
		 }else if(numConta<10000) {
			 conta = "0" + numConta.toString();
		 }else if(numConta<100000) {
			 conta = numConta.toString();
		 }
		 conta = conta + "-" + digito.toString();
		 return conta;
	}

	public Conta gerarNovaConta() {
		Conta novaConta = new Conta();
		novaConta.setAgencia(gerarNumeroAgencia());
		novaConta.setNumero(gerarNumeroConta());
		
		this.em = JPAUtil.getEntityManager();
		contaDao = new ContaDao(em);

		while(contaDao.existe(novaConta)) {
			novaConta.setAgencia(gerarNumeroAgencia());
			novaConta.setNumero(gerarNumeroConta());
		}
		
		return novaConta;
	}
	
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

	public boolean encerrarConta(UsuarioPessoaFisica u) {
		//Tenho que primeiro encerrar todas as transacoes
		//Abro entitymanager
		this.em = JPAUtil.getEntityManager();
		//crio listas das possiveis transacoes
		List<CompensacaoCheque> listaCompensacaoCheque = new ArrayList<CompensacaoCheque>();
		List<DepositoCaixa> listaDepositoCaixa = new ArrayList<DepositoCaixa>();
		List<SaqueCaixa> listaSaqueCaixa = new ArrayList<SaqueCaixa>();
		List<TransferenciaCaixa> listaTransferenciaCaixa = new ArrayList<TransferenciaCaixa>();
		
		//Crio seus respectivos Daos
		CompensacaoChequeDao compensacaoChequeDao = new CompensacaoChequeDao(em);
		DepositoCaixaDao depositoCaixaDao = new DepositoCaixaDao(em);
		SaqueCaixaDao saqueCaixaDao = new SaqueCaixaDao(em);
		TransferenciaCaixaDao transferenciaCaixaDao = new TransferenciaCaixaDao(em);
		
		//recupero as listas das respectivas transfernecias
		listaCompensacaoCheque = compensacaoChequeDao.listaTodosPorConta(u.getConta());
		
		Falta Implementar ListaTodosPorConta para as Transacoes Abaixo;
		
		listaDepositoCaixa = depositoCaixaDao.listaTodos();
		listaSaqueCaixa = saqueCaixaDao.listaTodos();
		listaTransferenciaCaixa = transferenciaCaixaDao.listaTodos();
		
		
		
		System.out.println("Usuario: " + u);
		System.out.println("Lista de COmpensacao de Cheque: " + listaCompensacaoCheque);
		
		return true;
	}

	public boolean encerrarConta(UsuarioPessoaJuridica u) {
		return true;
	}

	
}
