package br.edu.fsma.banconucleoweb.gerenciador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.fsma.banconucleoweb.conexao.JPAUtil;
import br.edu.fsma.banconucleoweb.modelo.dao.CompensacaoChequeDao;
import br.edu.fsma.banconucleoweb.modelo.dao.ContaDao;
import br.edu.fsma.banconucleoweb.modelo.dao.DepositoCaixaDao;
import br.edu.fsma.banconucleoweb.modelo.dao.SaqueCaixaDao;
import br.edu.fsma.banconucleoweb.modelo.dao.TransferenciaCaixaDao;
import br.edu.fsma.banconucleoweb.modelo.negocio.CompensacaoCheque;
import br.edu.fsma.banconucleoweb.modelo.negocio.Conta;
import br.edu.fsma.banconucleoweb.modelo.negocio.DepositoCaixa;
import br.edu.fsma.banconucleoweb.modelo.negocio.SaqueCaixa;
import br.edu.fsma.banconucleoweb.modelo.negocio.TransferenciaCaixa;
import br.edu.fsma.banconucleoweb.util.Conversor;

public class GerenciadorTransacao {

	public List<ItemExtrato> getListaItemExtrato(Long idConta) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		ContaDao contaDao = new ContaDao(em);
		Conta conta = contaDao.buscaPorId(idConta);
		List<ItemExtrato> listaItemExtrato = new ArrayList<ItemExtrato>();
		listaItemExtrato = this.recebeItens(conta);
		
		return listaItemExtrato;
	}
	
	public List<ItemExtrato> getListaItemExtratoPorPeriodo(Long idConta, Date dataInicio, Date dataFim) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		ContaDao contaDao = new ContaDao(em);
		Conta conta = contaDao.buscaPorId(idConta);
		List<ItemExtrato> listaItemExtrato = new ArrayList<ItemExtrato>();
		listaItemExtrato = this.recebeItensPorPeriodo(conta, dataInicio, dataFim);
		
		return listaItemExtrato;
	}

	private List<ItemExtrato> recebeItens(Conta conta) {
		List<ItemExtrato> listaItemExtrato = new ArrayList<ItemExtrato>();
		listaItemExtrato.addAll(getListaCompensacaoCheque(conta));
		listaItemExtrato.addAll(getListaDepositoCaixa(conta));
		listaItemExtrato.addAll(getListaSaqueCaixa(conta));
		listaItemExtrato.addAll(getListaTransferenciaCaixa(conta));
		return listaItemExtrato;
	}
	
	private List<ItemExtrato> recebeItensPorPeriodo(Conta conta, Date dataInicio, Date dataFim) {
		List<ItemExtrato> listaItemExtrato = new ArrayList<ItemExtrato>();
		listaItemExtrato.addAll(getListaCompensacaoChequePorPeriodo(conta, dataInicio, dataFim));
		listaItemExtrato.addAll(getListaDepositoCaixaPorPeriodo(conta, dataInicio, dataFim));
		listaItemExtrato.addAll(getListaSaqueCaixaPorPeriodo(conta, dataInicio, dataFim));
		listaItemExtrato.addAll(getListaTransferenciaCaixaPorPeriodo(conta, dataInicio, dataFim));
		return listaItemExtrato;
	}

	private Collection<? extends ItemExtrato> getListaTransferenciaCaixaPorPeriodo(Conta conta, Date dataInicio,
			Date dataFim) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		TransferenciaCaixaDao transferenciaCaixaDao = new TransferenciaCaixaDao(em);
		List<ItemExtrato> listaItemExtrato = new ArrayList<ItemExtrato>();
		List<TransferenciaCaixa> listaTransferenciaCaixa = transferenciaCaixaDao.listaTodosPorContaPeriodo(conta, 
				Conversor.conversorData(dataInicio), 
				Conversor.conversorData(dataFim));
		
		for(int i = 0; i < listaTransferenciaCaixa.size(); i++) {
			listaItemExtrato.add(new ItemExtrato(listaTransferenciaCaixa.get(i).getValor(),
					                             listaTransferenciaCaixa.get(i).getData(),
					                             "Transferencia Caixa",
					                             listaTransferenciaCaixa.get(i).getConta()));
		}
		
		return listaItemExtrato;
	}

	private Collection<? extends ItemExtrato> getListaSaqueCaixaPorPeriodo(Conta conta, Date dataInicio, Date dataFim) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		SaqueCaixaDao saqueCaixaDao = new SaqueCaixaDao(em);
		List<ItemExtrato> listaItemExtrato = new ArrayList<ItemExtrato>();
		List<SaqueCaixa> listaSaqueCaixa = saqueCaixaDao.listaTodosPorContaPeriodo(conta, 
				Conversor.conversorData(dataInicio), 
				Conversor.conversorData(dataFim));
		
		for(int i = 0; i < listaSaqueCaixa.size(); i++) {
			listaItemExtrato.add(new ItemExtrato(listaSaqueCaixa.get(i).getValor(),
					                             listaSaqueCaixa.get(i).getData(),
					                             "Saque Caixa",
					                             listaSaqueCaixa.get(i).getConta()));
		}
		
		return listaItemExtrato;
	}

	private Collection<? extends ItemExtrato> getListaDepositoCaixaPorPeriodo(Conta conta, Date dataInicio,
			Date dataFim) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		DepositoCaixaDao depositoCaixaDao = new DepositoCaixaDao(em);
		List<ItemExtrato> listaItemExtrato = new ArrayList<ItemExtrato>();
		List<DepositoCaixa> listaDepositoCaixa = depositoCaixaDao.listaTodosPorContaPeriodo(conta, 
				Conversor.conversorData(dataInicio), 
				Conversor.conversorData(dataFim));
		
		for(int i = 0; i < listaDepositoCaixa.size(); i++) {
			listaItemExtrato.add(new ItemExtrato(listaDepositoCaixa.get(i).getValor(),
					                             listaDepositoCaixa.get(i).getData(),
					                             "Deposito Caixa",
					                             listaDepositoCaixa.get(i).getConta()));
		}
		
		return listaItemExtrato;
	}

	private Collection<? extends ItemExtrato> getListaCompensacaoChequePorPeriodo(Conta conta, Date dataInicio,
			Date dataFim) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		CompensacaoChequeDao compensacaoChequeDao = new CompensacaoChequeDao(em);
		List<ItemExtrato> listaItemExtrato = new ArrayList<ItemExtrato>();
		List<CompensacaoCheque> listaCompensacaoCheque = compensacaoChequeDao.listaTodosPorContaPeriodo(conta, 
				Conversor.conversorData(dataInicio), 
				Conversor.conversorData(dataFim));
		
		for(int i = 0; i < listaCompensacaoCheque.size(); i++) {
			listaItemExtrato.add(new ItemExtrato(listaCompensacaoCheque.get(i).getValor(),
					                             listaCompensacaoCheque.get(i).getData(),
					                             "Compensação de Cheque",
					                             listaCompensacaoCheque.get(i).getConta()));
		}
		
		return listaItemExtrato;
	}

	

	private Collection<? extends ItemExtrato> getListaCompensacaoCheque(Conta conta) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		CompensacaoChequeDao compensacaoChequeDao = new CompensacaoChequeDao(em);
		List<ItemExtrato> listaItemExtrato = new ArrayList<ItemExtrato>();
		List<CompensacaoCheque> listaCompensacaoCheque = compensacaoChequeDao.listaTodosPorConta(conta);
		
		for(int i = 0; i < listaCompensacaoCheque.size(); i++) {
			listaItemExtrato.add(new ItemExtrato(listaCompensacaoCheque.get(i).getValor(),
					                             listaCompensacaoCheque.get(i).getData(),
					                             "Compensação de Cheque",
					                             listaCompensacaoCheque.get(i).getConta()));
		}
		
		return listaItemExtrato;
	}
	
	private Collection<? extends ItemExtrato> getListaDepositoCaixa(Conta conta) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		DepositoCaixaDao depositoCaixaDao = new DepositoCaixaDao(em);
		List<ItemExtrato> listaItemExtrato = new ArrayList<ItemExtrato>();
		List<DepositoCaixa> listaDepositoCaixa = depositoCaixaDao.listaTodosPorConta(conta);
		
		for(int i = 0; i < listaDepositoCaixa.size(); i++) {
			listaItemExtrato.add(new ItemExtrato(listaDepositoCaixa.get(i).getValor(),
								                    listaDepositoCaixa.get(i).getData(),
								                    "Deposito Caixa",
								                    listaDepositoCaixa.get(i).getConta()));
		}
		
		return listaItemExtrato;
	}
	
	private Collection<? extends ItemExtrato> getListaSaqueCaixa(Conta conta) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		SaqueCaixaDao saqueCaixaDao = new SaqueCaixaDao(em);
		List<ItemExtrato> listaItemExtrato = new ArrayList<ItemExtrato>();
		List<SaqueCaixa> listaSaqueCaixa = saqueCaixaDao.listaTodosPorConta(conta);
		
		for(int i = 0; i < listaSaqueCaixa.size(); i++) {
			listaItemExtrato.add(new ItemExtrato(listaSaqueCaixa.get(i).getValor(),
												listaSaqueCaixa.get(i).getData(),
							                    "Saque Caixa",
							                    listaSaqueCaixa.get(i).getConta()));
		}
		
		return listaItemExtrato;
	}
	
	private Collection<? extends ItemExtrato> getListaTransferenciaCaixa(Conta conta) {
		EntityManager em;
		em = JPAUtil.getEntityManager();
		TransferenciaCaixaDao transferenciaCaixaDao = new TransferenciaCaixaDao(em);
		List<ItemExtrato> listaItemExtrato = new ArrayList<ItemExtrato>();
		List<TransferenciaCaixa> listaTransferenciaCaixa = transferenciaCaixaDao.listaTodosPorConta(conta);
		
		for(int i = 0; i < listaTransferenciaCaixa.size(); i++) {
			listaItemExtrato.add(new ItemExtrato(listaTransferenciaCaixa.get(i).getValor(),
								                    listaTransferenciaCaixa.get(i).getData(),
								                    "Transfernecia Caixa",
								                    listaTransferenciaCaixa.get(i).getConta()));
		}
		
		return listaItemExtrato;
	}

	public boolean depositar(Conta conta, Double valorDeposito) {
		
		DepositoCaixa depositoCaixa = new DepositoCaixa();
		depositoCaixa.setConta(conta);
		
		if(depositoCaixa.processarTransacao(valorDeposito) == null) {
			return false;
		}else {
			return true;
		}
		
	}

	public boolean sacar(Conta conta, Double valorSaque) {
		SaqueCaixa saqueCaixa = new SaqueCaixa();
		saqueCaixa.setConta(conta);
		
		if(saqueCaixa.processarTransacao(valorSaque) == null) {
			return false;
		}else {
			return true;
		}
	}

	
	
}
