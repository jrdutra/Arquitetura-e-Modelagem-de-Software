package br.edu.fsma.banconucleoweb.gerenciador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import br.edu.fsma.banconucleoweb.conexao.JPAUtil;
import br.edu.fsma.banconucleoweb.excluido.dao.CompensacaoChequeExcluidoDao;
import br.edu.fsma.banconucleoweb.excluido.dao.ContaExcluidoDao;
import br.edu.fsma.banconucleoweb.excluido.dao.DepositoCaixaExcluidoDao;
import br.edu.fsma.banconucleoweb.excluido.dao.SaqueCaixaExcluidoDao;
import br.edu.fsma.banconucleoweb.excluido.dao.TransferenciaCaixaExcluidoDao;
import br.edu.fsma.banconucleoweb.excluido.dao.UsuarioPessoaFisicaExcluidoDao;
import br.edu.fsma.banconucleoweb.excluido.dao.UsuarioPessoaJuridicaExcluidoDao;
import br.edu.fsma.banconucleoweb.excluido.negocio.CompensacaoChequeExcluido;
import br.edu.fsma.banconucleoweb.excluido.negocio.ContaExcluido;
import br.edu.fsma.banconucleoweb.excluido.negocio.DepositoCaixaExcluido;
import br.edu.fsma.banconucleoweb.excluido.negocio.SaqueCaixaExcluido;
import br.edu.fsma.banconucleoweb.excluido.negocio.TransferenciaCaixaExcluido;
import br.edu.fsma.banconucleoweb.excluido.negocio.UsuarioPessoaFisicaExcluido;
import br.edu.fsma.banconucleoweb.excluido.negocio.UsuarioPessoaJuridicaExcluido;
import br.edu.fsma.banconucleoweb.modelo.dao.CompensacaoChequeDao;
import br.edu.fsma.banconucleoweb.modelo.dao.ContaDao;
import br.edu.fsma.banconucleoweb.modelo.dao.DepositoCaixaDao;
import br.edu.fsma.banconucleoweb.modelo.dao.PessoaFisicaDao;
import br.edu.fsma.banconucleoweb.modelo.dao.PessoaJuridicaDao;
import br.edu.fsma.banconucleoweb.modelo.dao.SaqueCaixaDao;
import br.edu.fsma.banconucleoweb.modelo.dao.TransferenciaCaixaDao;
import br.edu.fsma.banconucleoweb.modelo.dao.UsuarioPessoaFisicaDao;
import br.edu.fsma.banconucleoweb.modelo.dao.UsuarioPessoaJuridicaDao;
import br.edu.fsma.banconucleoweb.modelo.negocio.CompensacaoCheque;
import br.edu.fsma.banconucleoweb.modelo.negocio.Conta;
import br.edu.fsma.banconucleoweb.modelo.negocio.DepositoCaixa;
import br.edu.fsma.banconucleoweb.modelo.negocio.PessoaFisica;
import br.edu.fsma.banconucleoweb.modelo.negocio.PessoaJuridica;
import br.edu.fsma.banconucleoweb.modelo.negocio.SaqueCaixa;
import br.edu.fsma.banconucleoweb.modelo.negocio.TransferenciaCaixa;
import br.edu.fsma.banconucleoweb.modelo.negocio.UsuarioPessoaFisica;
import br.edu.fsma.banconucleoweb.modelo.negocio.UsuarioPessoaJuridica;

public class GerenciadorConta {

	Random gerador = new Random();
	
	public void guardarContaFisica(Conta conta, Long idPessoaFisica, String senha) {
		EntityManager em;
		ContaDao contaDao;
		em = JPAUtil.getEntityManager();
		UsuarioPessoaFisicaDao usuarioPessoaFisicaDao;
		usuarioPessoaFisicaDao = new UsuarioPessoaFisicaDao(em);
		PessoaFisicaDao pessoaFisicaDao;
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
			usuarioPessoaFisicaDao.adiciona(usuarioPessoaFisica);
			em.getTransaction().commit();
		}catch(Exception ex)  {
			System.out.println("\n\nErro ao gravar usuario no banco.");
			em.getTransaction().rollback();
		}
	}
	
	public void guardarContaJuridica(Conta conta, Long idPessoaJuridica, String senha) {
		ContaDao contaDao;
		EntityManager em;
		em = JPAUtil.getEntityManager();
		UsuarioPessoaJuridicaDao usuarioPessoaJuridicaDao;
		usuarioPessoaJuridicaDao = new UsuarioPessoaJuridicaDao(em);
		PessoaJuridicaDao pessoaJuridicaDao;
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
			usuarioPessoaJuridicaDao.adiciona(usuarioPessoaJuridica);
			em.getTransaction().commit();
		}catch(Exception ex)  {
			System.out.println("\n\nErro ao gravar usuario no banco.");
			em.getTransaction().rollback();
		}
		
	}
	
	public void criarUsuarioPessoaFisica(Long idConta, Long idPessoaFisica, String senha) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		
		UsuarioPessoaFisicaDao usuarioDao = new UsuarioPessoaFisicaDao(em);
		PessoaFisicaDao pessoaFisicaDao = new PessoaFisicaDao(em);
		
		UsuarioPessoaFisica usuarioPessoaFisica = new UsuarioPessoaFisica();
		PessoaFisica pessoaFisica = pessoaFisicaDao.getPessoaFisica(idPessoaFisica);
		
		Conta conta = new Conta();
		ContaDao contaDao = new ContaDao(em);
		conta = contaDao.buscaPorId(idConta);
		
		
		usuarioPessoaFisica.setConta(conta);
		usuarioPessoaFisica.setPessoaFisica(pessoaFisica);
		usuarioPessoaFisica.setSenha(senha);
		
		try {
			em.getTransaction().begin();
			usuarioDao.adiciona(usuarioPessoaFisica);
			em.getTransaction().commit();
		}catch(Exception ex)  {
			em.getTransaction().rollback();
		}
		
		
		
	}
	
	public void criarUsuarioPessoaJuridica(Long idConta, Long idPessoaJuridica, String senha) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		UsuarioPessoaJuridicaDao usuarioDao = new UsuarioPessoaJuridicaDao(em);
		PessoaJuridicaDao pessoaJuridicaDao = new PessoaJuridicaDao(em);
	
		UsuarioPessoaJuridica usuarioPessoaJuridica = new UsuarioPessoaJuridica();
		PessoaJuridica pessoaJuridica = pessoaJuridicaDao.getPessoaJuridica(idPessoaJuridica);
		
		Conta conta = new Conta();
		ContaDao contaDao = new ContaDao(em);
		conta = contaDao.buscaPorId(idConta);
		
		usuarioPessoaJuridica.setConta(conta);
		usuarioPessoaJuridica.setPessoaJuridica(pessoaJuridica);
		usuarioPessoaJuridica.setSenha(senha);
		
		try {
			em.getTransaction().begin();
			usuarioDao.adiciona(usuarioPessoaJuridica);
			em.getTransaction().commit();
		}catch(Exception ex)  {
			em.getTransaction().rollback();
		}
	}


	public Conta gerarNovaConta() {
		ContaDao contaDao;
		Conta novaConta = new Conta();
		novaConta.setAgencia(gerarNumeroAgencia());
		novaConta.setNumero(gerarNumeroConta());
		EntityManager em;
		em = JPAUtil.getEntityManager();
		contaDao = new ContaDao(em);

		while(contaDao.existe(novaConta)) {
			novaConta.setAgencia(gerarNumeroAgencia());
			novaConta.setNumero(gerarNumeroConta());
		}
		
		return novaConta;
	}
	
	public List<PessoaFisica> getListaPessoaFisica() {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		PessoaFisicaDao pessoaFisicaDao;
		pessoaFisicaDao = new PessoaFisicaDao(em);
		return pessoaFisicaDao.listaTodos();
	}

	public List<PessoaJuridica> getListaPessoaJuridica() {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		PessoaJuridicaDao pessoaJuridicaDao;
		pessoaJuridicaDao = new PessoaJuridicaDao(em);
		return pessoaJuridicaDao.listaTodos();
	}

	public boolean encerrarConta(UsuarioPessoaFisica u) {
		
		if(u.getConta().getSaldo() == 0.0) {
			try {
				ContaExcluido contaExcluido = this.retornaContaExcluida(u.getConta());
				contaExcluido = this.salvaContaExcluidoNoBanco(contaExcluido);
				this.gravaListaCompensacaoChequeExcluido(u.getConta(), contaExcluido);
				this.gravaListaDepositoCaixaExcluido(u.getConta(), contaExcluido);
				this.gravaListaSaqueCaixa(u.getConta(), contaExcluido);
				this.gravalistaTransferenciaCaixa(u.getConta(), contaExcluido);
				this.gravaUsuarioExcluido(u, contaExcluido);
				this.excluiConta(u.getConta());
				return true;
			}catch(Exception ex) {
				System.out.println("Erro ao gravar listas de transacoes excluidas");
				return false;
			}
		}else {
			return false;
		}
		
		
		
	}
	
	public boolean encerrarConta(UsuarioPessoaJuridica u) {
		try {
			ContaExcluido contaExcluido = this.retornaContaExcluida(u.getConta());
			contaExcluido = this.salvaContaExcluidoNoBanco(contaExcluido);
			this.gravaListaCompensacaoChequeExcluido(u.getConta(), contaExcluido);
			this.gravaListaDepositoCaixaExcluido(u.getConta(), contaExcluido);
			this.gravaListaSaqueCaixa(u.getConta(), contaExcluido);
			this.gravalistaTransferenciaCaixa(u.getConta(), contaExcluido);
			this.gravaUsuarioExcluido(u, contaExcluido);
			this.excluiConta(u.getConta());
			return true;
		}catch(Exception ex) {
			System.out.println("Erro ao gravar listas de transacoes excluidas");
			return false;
		}
	}

	private void gravaUsuarioExcluido(UsuarioPessoaFisica u, ContaExcluido contaExcluido) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		UsuarioPessoaFisicaExcluidoDao usuarioPessoaFisicaExcluidoDao = new UsuarioPessoaFisicaExcluidoDao(em);
		UsuarioPessoaFisicaDao usuarioPessoaFisicaDao = new UsuarioPessoaFisicaDao(em);
		UsuarioPessoaFisicaExcluido usuarioPessoaFisicaExcluido = new UsuarioPessoaFisicaExcluido(u, contaExcluido);
		
		List<UsuarioPessoaFisica> listaUsuarioPessoaFisica = new ArrayList<UsuarioPessoaFisica>();
		
		Conta conta = u.getConta();
		
		listaUsuarioPessoaFisica = usuarioPessoaFisicaDao.listaTodosPorConta(conta);
		
		
		em.getTransaction().begin();
		
		usuarioPessoaFisicaExcluidoDao.adiciona(usuarioPessoaFisicaExcluido);
		usuarioPessoaFisicaDao.removeLista(listaUsuarioPessoaFisica);
		
		em.getTransaction().commit();
	}
	
	private void gravaUsuarioExcluido(UsuarioPessoaJuridica u, ContaExcluido contaExcluido) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		UsuarioPessoaJuridicaExcluidoDao usuarioPessoaJuridicaExcluidoDao = new UsuarioPessoaJuridicaExcluidoDao(em);
		UsuarioPessoaJuridicaDao usuarioPessoaJuridicaDao = new UsuarioPessoaJuridicaDao(em);
		UsuarioPessoaJuridicaExcluido usuarioPessoaJuridicaExcluido = new UsuarioPessoaJuridicaExcluido(u, contaExcluido);
		
		List<UsuarioPessoaJuridica> listaUsuarioPessoaJuridica = new ArrayList<UsuarioPessoaJuridica>();
		
		Conta conta = u.getConta();
		
		listaUsuarioPessoaJuridica = usuarioPessoaJuridicaDao.listaTodosPorConta(conta);
		
		em.getTransaction().begin();
		
		usuarioPessoaJuridicaExcluidoDao.adiciona(usuarioPessoaJuridicaExcluido);
		usuarioPessoaJuridicaDao.removeLista(listaUsuarioPessoaJuridica);;
		
		em.getTransaction().commit();
	}
	
	private void excluiConta(Conta conta) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		ContaDao contaDao = new ContaDao(em);
		em.getTransaction().begin();
			contaDao.remove(conta);
		em.getTransaction().commit();
	}
	
	

	private void gravalistaTransferenciaCaixa(Conta conta, ContaExcluido contaExcluido) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		
		//recupera lista transf
		List<TransferenciaCaixa> listaTransferenciaCaixa = new ArrayList<TransferenciaCaixa>();
		TransferenciaCaixaDao transferenciaCaixaDao = new TransferenciaCaixaDao(em);
		listaTransferenciaCaixa = transferenciaCaixaDao.listaTodosPorConta(conta);
		
		TransferenciaCaixaExcluidoDao transferenciaCaixaExcluidoDao = new TransferenciaCaixaExcluidoDao(em);
		try {
			//Salva CompensacaoChequeExcluido
			em.getTransaction().begin();
			for(int i = 0; i < listaTransferenciaCaixa.size(); i++) {
				TransferenciaCaixaExcluido saqueCaixaExcluido = new TransferenciaCaixaExcluido(listaTransferenciaCaixa.get(i), contaExcluido);
				transferenciaCaixaExcluidoDao.adiciona(saqueCaixaExcluido);
			}
			transferenciaCaixaDao.excluiLista(listaTransferenciaCaixa);
			em.getTransaction().commit();
		}catch(Exception ex)  {
			System.out.println("\n\nErro ao gravar CompensacaoDeChequeExclui no banco =):");
			System.out.println(ex);
			em.getTransaction().rollback();
		}
		
	}

	private void gravaListaSaqueCaixa(Conta conta, ContaExcluido contaExcluido) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		//recupera lsita saque
		List<SaqueCaixa> listaSaqueCaixa = new ArrayList<SaqueCaixa>();
		SaqueCaixaDao saqueCaixaDao = new SaqueCaixaDao(em);
		listaSaqueCaixa = saqueCaixaDao.listaTodosPorConta(conta);
		
		SaqueCaixaExcluidoDao saqueCaixaExcluidoDao = new SaqueCaixaExcluidoDao(em);
		try {
			//Salva CompensacaoChequeExcluido
			em.getTransaction().begin();
			for(int i = 0; i < listaSaqueCaixa.size(); i++) {
				SaqueCaixaExcluido saqueCaixaExcluido = new SaqueCaixaExcluido(listaSaqueCaixa.get(i), contaExcluido);
				saqueCaixaExcluidoDao.adiciona(saqueCaixaExcluido);
			}
			saqueCaixaDao.excluiLista(listaSaqueCaixa);
			em.getTransaction().commit();
		}catch(Exception ex)  {
			System.out.println("\n\nErro ao gravar CompensacaoDeChequeExclui no banco =):");
			System.out.println(ex);
			em.getTransaction().rollback();
		}
		
		
	}

	private void gravaListaDepositoCaixaExcluido(Conta conta, ContaExcluido contaExcluido) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		
		//recupera lsita deposito
		List<DepositoCaixa> listaDepositoCaixa = new ArrayList<DepositoCaixa>();
		DepositoCaixaDao depositoCaixaDao = new DepositoCaixaDao(em);
		listaDepositoCaixa = depositoCaixaDao.listaTodosPorConta(conta);
		
		DepositoCaixaExcluidoDao depositoCaixaExcluidoDao = new DepositoCaixaExcluidoDao(em);
		try {
			//Salva CompensacaoChequeExcluido
			em.getTransaction().begin();
			for(int i = 0; i < listaDepositoCaixa.size(); i++) {
				DepositoCaixaExcluido depositoCaixaExcluido = new DepositoCaixaExcluido(listaDepositoCaixa.get(i), contaExcluido);
				depositoCaixaExcluidoDao.adiciona(depositoCaixaExcluido);
			}
			depositoCaixaDao.excluiLista(listaDepositoCaixa);
			em.getTransaction().commit();
		}catch(Exception ex)  {
			System.out.println("\n\nErro ao gravar CompensacaoDeChequeExclui no banco =):");
			System.out.println(ex);
			em.getTransaction().rollback();
		}
		
	}

	private void gravaListaCompensacaoChequeExcluido(Conta conta, ContaExcluido contaExcluido) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		
		//recupera listacheque
		List<CompensacaoCheque> listaCompensacaoCheque = new ArrayList<CompensacaoCheque>();
		CompensacaoChequeDao compensacaoChequeDao = new CompensacaoChequeDao(em);
		listaCompensacaoCheque = compensacaoChequeDao.listaTodosPorConta(conta);
		
		CompensacaoChequeExcluidoDao compensacaoChequeExcluidoDao = new CompensacaoChequeExcluidoDao(em);
		try {
			//Salva CompensacaoChequeExcluido
			em.getTransaction().begin();
			for(int i = 0; i < listaCompensacaoCheque.size(); i++) {
				CompensacaoChequeExcluido compensacaoChequeExcluido = new CompensacaoChequeExcluido(listaCompensacaoCheque.get(i), contaExcluido);
				compensacaoChequeExcluidoDao.adiciona(compensacaoChequeExcluido);
			}
			compensacaoChequeDao.excluiLista(listaCompensacaoCheque);
			em.getTransaction().commit();
		}catch(Exception ex)  {
			System.out.println("\n\nErro ao gravar CompensacaoDeChequeExclui no banco =):");
			System.out.println(ex);
			em.getTransaction().rollback();
		}
	}

	private ContaExcluido retornaContaExcluida(Conta conta) {
		//recupera conta do banco-Por conta do id
		EntityManager em;
		em = JPAUtil.getEntityManager();
		ContaDao contaDao = new ContaDao(em);
		conta = contaDao.buscaContaPorAngenciaNumero(conta.getAgencia(), conta.getNumero());
		
		//cria conta excluida
		ContaExcluido contaExcluido = new ContaExcluido(conta);
		System.out.println("Conta excluido:"+contaExcluido);
		return contaExcluido;
		
	}

	private ContaExcluido salvaContaExcluidoNoBanco(ContaExcluido contaExcluido) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		ContaExcluidoDao contaExcluidoDao = new ContaExcluidoDao(em);
		try {
			em.getTransaction().begin();
			if(!contaExcluidoDao.existe(contaExcluido)) {
				contaExcluidoDao.adiciona(contaExcluido);
			}
			em.getTransaction().commit();
		}catch(Exception ex)  {
			System.out.println("\n\nErro ao gravar ContaExcluido no banco =):");
			System.out.println(ex);
			em.getTransaction().rollback();
		}
		return contaExcluidoDao.buscaContaPorAngenciaNumero(contaExcluido.getAgencia(), contaExcluido.getNumero());
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

	public List<Conta> getListaConta() {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		ContaDao contaDao = new ContaDao(em);
		return contaDao.listaTodos();
	}

	public Conta getConta(Long idConta) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		ContaDao contaDao = new ContaDao(em);
		return contaDao.getConta(idConta);
	}
	
	
}
