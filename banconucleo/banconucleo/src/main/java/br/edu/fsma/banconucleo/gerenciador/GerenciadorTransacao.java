package br.edu.fsma.banconucleo.gerenciador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.fsma.banconucleo.conexao.JPAUtil;
import br.edu.fsma.banconucleo.modelo.dao.CompensacaoChequeDao;
import br.edu.fsma.banconucleo.modelo.dao.ContaDao;
import br.edu.fsma.banconucleo.modelo.dao.DepositoCaixaDao;
import br.edu.fsma.banconucleo.modelo.dao.SaqueCaixaDao;
import br.edu.fsma.banconucleo.modelo.dao.TransferenciaCaixaDao;
import br.edu.fsma.banconucleo.modelo.negocio.CompensacaoCheque;
import br.edu.fsma.banconucleo.modelo.negocio.Conta;
import br.edu.fsma.banconucleo.modelo.negocio.DepositoCaixa;
import br.edu.fsma.banconucleo.modelo.negocio.SaqueCaixa;
import br.edu.fsma.banconucleo.modelo.negocio.Transacao;
import br.edu.fsma.banconucleo.modelo.negocio.TransferenciaCaixa;
import br.edu.fsma.banconucleo.util.Conversor;

public class GerenciadorTransacao {
	
	private EntityManager em = JPAUtil.getEntityManager();

	public List<Transacao> getListaTransacaoPorPeriodo(Long idConta, Date dataInicio, Date dataFim) {
		ContaDao contaDao = new ContaDao(em);
		Conta conta = contaDao.buscaPorId(idConta);
		return this.recebeItensPorPeriodo(conta, dataInicio, dataFim);
	}

	private List<Transacao> recebeItensPorPeriodo(Conta conta, Date dataInicio, Date dataFim) {
		List<Transacao> listaTransacao = new ArrayList<Transacao>();
		listaTransacao.addAll(getListaCompensacaoChequePorPeriodo(conta, dataInicio, dataFim));
		listaTransacao.addAll(getListaDepositoCaixaPorPeriodo(conta, dataInicio, dataFim));
		listaTransacao.addAll(getListaSaqueCaixaPorPeriodo(conta, dataInicio, dataFim));
		listaTransacao.addAll(getListaTransferenciaCaixaPorPeriodo(conta, dataInicio, dataFim));
		return listaTransacao;
	}

	private Collection<? extends Transacao> getListaTransferenciaCaixaPorPeriodo(Conta conta, Date dataInicio,
			Date dataFim) {
		TransferenciaCaixaDao transferenciaCaixaDao = new TransferenciaCaixaDao(em);
		List<Transacao> listaTransacao = new ArrayList<>();
		List<TransferenciaCaixa> listaTransferenciaCaixa = transferenciaCaixaDao.listaTodosPorContaPeriodo(conta, 
				Conversor.conversorData(dataInicio), 
				Conversor.conversorData(dataFim));
		listaTransacao.addAll(listaTransferenciaCaixa);		
		return listaTransacao;
	}

	private Collection<? extends Transacao> getListaSaqueCaixaPorPeriodo(Conta conta, Date dataInicio, Date dataFim) {
		SaqueCaixaDao saqueCaixaDao = new SaqueCaixaDao(em);
		List<Transacao> listaTransacao = new ArrayList<>();
		List<SaqueCaixa> listaSaqueCaixa = saqueCaixaDao.listaTodosPorContaPeriodo(conta, 
				Conversor.conversorData(dataInicio), 
				Conversor.conversorData(dataFim));
		listaTransacao.addAll(listaSaqueCaixa);
		return listaTransacao;
	}

	private Collection<? extends Transacao> getListaDepositoCaixaPorPeriodo(Conta conta, Date dataInicio,
			Date dataFim) {
		DepositoCaixaDao depositoCaixaDao = new DepositoCaixaDao(em);
		List<Transacao> listaTransacao = new ArrayList<>();
		List<DepositoCaixa> listaDepositoCaixa = depositoCaixaDao.listaTodosPorContaPeriodo(conta, 
				Conversor.conversorData(dataInicio), 
				Conversor.conversorData(dataFim));
		listaTransacao.addAll(listaDepositoCaixa);
		return listaTransacao;
	}

	private Collection<? extends Transacao> getListaCompensacaoChequePorPeriodo(Conta conta, Date dataInicio,
			Date dataFim) {
		CompensacaoChequeDao compensacaoChequeDao = new CompensacaoChequeDao(em);
		List<Transacao> listaTransacao = new ArrayList<>();
		List<CompensacaoCheque> listaCompensacaoCheque = compensacaoChequeDao.listaTodosPorContaPeriodo(conta, 
				Conversor.conversorData(dataInicio), 
				Conversor.conversorData(dataFim));
		listaTransacao.addAll(listaCompensacaoCheque);
		return listaTransacao;
	}

	public boolean depositar(Conta conta, Double valorDeposito) {
		DepositoCaixa depositoCaixa = new DepositoCaixa();
		depositoCaixa.setConta(conta);
		if(depositoCaixa.processarTransacao(valorDeposito) == null) {
			return false;
		}
		return true;
	}

	public boolean sacar(Conta conta, Double valorSaque) {
		SaqueCaixa saqueCaixa = new SaqueCaixa();
		saqueCaixa.setConta(conta);
		if(saqueCaixa.processarTransacao(valorSaque) == null) {
			return false;
		}
		return true;
	}
}
