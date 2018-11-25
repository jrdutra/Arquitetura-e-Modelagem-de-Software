package br.edu.fsma.bancogerente.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import br.edu.fsma.bancogerente.util.Redirecionador;
import br.edu.fsma.bancogerente.util.Secao;
import br.edu.fsma.banconucleo.gerenciador.GerenciadorConta;
import br.edu.fsma.banconucleo.modelo.negocio.Conta;
import br.edu.fsma.banconucleo.modelo.negocio.PessoaFisica;
import br.edu.fsma.banconucleo.modelo.negocio.PessoaJuridica;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioGerente;

@ManagedBean(name = "AbrirContaBean")
@ViewScoped
public class AbrirContaBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	private UsuarioGerente usuarioGerente;
	private Conta conta = new Conta();
	private GerenciadorConta gerenciadorConta = new GerenciadorConta();
	
	private Double saldo;
	private String senha;
	
	private Long idPessoaFisica;
	private List<PessoaFisica> listaPessoaFisica = new ArrayList<PessoaFisica>();
	
	private Long idPessoaJuridica;
	private List<PessoaJuridica> listaPessoaJuridica = new ArrayList<PessoaJuridica>();
	private Redirecionador redirecionador;
	
	public AbrirContaBean() {
		this.usuarioGerente = Secao.getUsuarioGerente();
		this.conta = gerenciadorConta.gerarNovaConta();
		this.listaPessoaFisica = gerenciadorConta.getListaPessoaFisica();
		this.listaPessoaJuridica = gerenciadorConta.getListaPessoaJuridica();
	}
	
	@PostConstruct
	public void init() {
		if(Secao.getUsuarioGerente()==null) {
			redirecionador.redireciona("/bancogerente/view/index/index.xhtml");
		}
	}
	
	public void criarContaFisicaClick() {
		this.conta.setUsuarioGerente(this.usuarioGerente);
		this.conta.setSaldo(this.saldo);
		gerenciadorConta.guardarContaFisica(this.conta, this.idPessoaFisica, this.senha);
		Redirecionador redirecionador = new Redirecionador();
		redirecionador.esperaSegundos(1000);
		redirecionador.redirecionaPainel();
	}
	
	public void criarContaJuridicaClick() {
		this.conta.setUsuarioGerente(this.usuarioGerente);
		this.conta.setSaldo(this.saldo);
		gerenciadorConta.guardarContaJuridica(this.conta, this.idPessoaJuridica, this.senha);
		Redirecionador redirecionador = new Redirecionador();
		redirecionador.esperaSegundos(1000);
		redirecionador.redirecionaPainel();
	}
	
	

	private void esperaSegundos(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public UsuarioGerente getUsuarioGerente() {
		return usuarioGerente;
	}

	public void setUsuarioGerente(UsuarioGerente usuarioGerente) {
		this.usuarioGerente = usuarioGerente;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(Long idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<PessoaFisica> getListaPessoaFisica() {
		return listaPessoaFisica;
	}

	public void setListaPessoaFisica(List<PessoaFisica> listaPessoaFisica) {
		this.listaPessoaFisica = listaPessoaFisica;
	}

	public Long getIdPessoaJuridica() {
		return idPessoaJuridica;
	}

	public void setIdPessoaJuridica(Long idPessoaJuridica) {
		this.idPessoaJuridica = idPessoaJuridica;
	}

	public List<PessoaJuridica> getListaPessoaJuridica() {
		return listaPessoaJuridica;
	}

	public void setListaPessoaJuridica(List<PessoaJuridica> listaPessoaJuridica) {
		this.listaPessoaJuridica = listaPessoaJuridica;
	}
	
	
}
