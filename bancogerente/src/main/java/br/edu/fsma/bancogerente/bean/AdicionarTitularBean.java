package br.edu.fsma.bancogerente.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import br.edu.fsma.banconucleo.gerenciador.GerenciadorConta;
import br.edu.fsma.banconucleo.gerenciador.GerenciadorPessoa;
import br.edu.fsma.bancogerente.util.Redirecionador;
import br.edu.fsma.bancogerente.util.Secao;
import br.edu.fsma.banconucleo.modelo.negocio.Conta;
import br.edu.fsma.banconucleo.modelo.negocio.PessoaFisica;
import br.edu.fsma.banconucleo.modelo.negocio.PessoaJuridica;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioGerente;

@ManagedBean(name = "AdicionarTitularBean")
@ViewScoped
public class AdicionarTitularBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	private UsuarioGerente usuarioGerente = new UsuarioGerente();
	private Redirecionador redirecionador = new Redirecionador();
	private Long idConta;
	private Long idPessoaFisica;
	private Long idPessoaJuridica;
	private String senha;
	private Conta conta = new Conta();
	
	private GerenciadorConta gerenciadorConta = new GerenciadorConta();
	private GerenciadorPessoa gerenciadorPessoa = new GerenciadorPessoa();
	
	private List<Conta> listaConta = new ArrayList<Conta>();
	private List<PessoaFisica> listaPessoaFisica = new ArrayList<PessoaFisica>();
	private List<PessoaJuridica> listaPessoaJuridica = new ArrayList<PessoaJuridica>();
	
	public AdicionarTitularBean() {
		this.usuarioGerente = Secao.getUsuarioGerente();
		listaConta = gerenciadorConta.getListaConta();
		listaPessoaFisica = gerenciadorPessoa.getListaPessoaFisica();
		listaPessoaJuridica  = gerenciadorPessoa.getListaPessoaJuridica();
	}
	
	@PostConstruct
	public void init() {
		if(Secao.getUsuarioGerente()==null) {
			redirecionador.redireciona("/bancogerente/view/index/index.xhtml");
		}
	}
	
	public void adicionarContaFisicaClick() {

		gerenciadorConta.criarUsuarioPessoaFisica(this.idConta, this.idPessoaFisica, this.senha);
		Redirecionador redirecionador = new Redirecionador();
		redirecionador.esperaSegundos(1000);
		redirecionador.redirecionaPainel();
	}
	
	public void adicionarContaJuridicaClick() {

		gerenciadorConta.criarUsuarioPessoaJuridica(this.idConta, this.idPessoaJuridica, this.senha);
		Redirecionador redirecionador = new Redirecionador();
		redirecionador.esperaSegundos(1000);
		redirecionador.redirecionaPainel();
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

	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(Long idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}

	public Long getIdPessoaJuridica() {
		return idPessoaJuridica;
	}

	public void setIdPessoaJuridica(Long idPessoaJuridica) {
		this.idPessoaJuridica = idPessoaJuridica;
	}

	public List<PessoaFisica> getListaPessoaFisica() {
		return listaPessoaFisica;
	}

	public void setListaPessoaFisica(List<PessoaFisica> listaPessoaFisica) {
		this.listaPessoaFisica = listaPessoaFisica;
	}

	public List<PessoaJuridica> getListaPessoaJuridica() {
		return listaPessoaJuridica;
	}

	public void setListaPessoaJuridica(List<PessoaJuridica> listaPessoaJuridica) {
		this.listaPessoaJuridica = listaPessoaJuridica;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	

}
