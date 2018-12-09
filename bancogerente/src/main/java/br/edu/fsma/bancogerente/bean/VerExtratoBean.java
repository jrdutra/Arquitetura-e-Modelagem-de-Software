package br.edu.fsma.bancogerente.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.edu.fsma.bancogerente.util.Redirecionador;
import br.edu.fsma.bancogerente.util.Secao;
import br.edu.fsma.banconucleo.gerenciador.GerenciadorConta;
import br.edu.fsma.banconucleo.gerenciador.GerenciadorTransacao;
import br.edu.fsma.banconucleo.modelo.negocio.Conta;
import br.edu.fsma.banconucleo.modelo.negocio.Transacao;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioGerente;

@ManagedBean(name = "VerExtratoBean")
@ViewScoped
public class VerExtratoBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	private UsuarioGerente usuarioGerente;
	private Redirecionador redirecionador;
	private GerenciadorConta gerenciadorConta = new GerenciadorConta();
	private GerenciadorTransacao gerenciadorTransacao = new GerenciadorTransacao();
	private Boolean viewExtrato = false;
	private Long idConta;
	private List<Conta> listaConta = new ArrayList<Conta>();
	private List<Transacao> listaTransacao;
	private Date dataInferior;
	private Date dataSuperior;
	
	public VerExtratoBean() {
		this.usuarioGerente = Secao.getUsuarioGerente();
	}
	
	@PostConstruct
	public void init() {
		if(Secao.getUsuarioGerente()==null) {
			redirecionador.redireciona("/bancogerente/view/index/index.xhtml");
		}
		listaConta = gerenciadorConta.getListaConta();
	}
	
	public void verExtratoClick() {
		this.viewExtrato = true;
		System.out.println(viewExtrato);
		listaTransacao = gerenciadorTransacao.getListaTransacaoPorPeriodo(this.idConta, dataInferior, dataSuperior);
		Collections.sort(listaTransacao);
	}
	
	public UsuarioGerente getUsuarioGerente() {
		return usuarioGerente;
	}

	public void setUsuarioGerente(UsuarioGerente usuarioGerente) {
		this.usuarioGerente = usuarioGerente;
	}

	public Long getIdConta() {
		return idConta;
	}

	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}

	public List<Conta> getListaConta() {
		return listaConta;
	}

	public void setListaConta(List<Conta> listaConta) {
		this.listaConta = listaConta;
	}

	public Date getDataInferior() {
		return dataInferior;
	}

	public void setDataInferior(Date dataInferior) {
		this.dataInferior = dataInferior;
	}

	public Date getDataSuperior() {
		return dataSuperior;
	}

	public void setDataSuperior(Date dataSuperior) {
		this.dataSuperior = dataSuperior;
	}

	public Boolean getViewExtrato() {
		return viewExtrato;
	}

	public void setViewExtrato(Boolean viewExtrato) {
		this.viewExtrato = viewExtrato;
	}

	public List<Transacao> getListaTransacao() {
		return listaTransacao;
	}

	public void setListaTransacao(List<Transacao> listaTransacao) {
		this.listaTransacao = listaTransacao;
	}

	
}
