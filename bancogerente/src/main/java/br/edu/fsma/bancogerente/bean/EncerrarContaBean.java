package br.edu.fsma.bancogerente.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.edu.fsma.bancogerente.util.Mensagem;
import br.edu.fsma.bancogerente.util.Redirecionador;
import br.edu.fsma.bancogerente.util.Secao;
import br.edu.fsma.banconucleo.gerenciador.GerenciadorConta;
import br.edu.fsma.banconucleo.gerenciador.GerenciadorUsuarioPessoa;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioGerente;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaFisica;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaJuridica;

@ManagedBean(name = "EncerrarContaBean")
@ViewScoped
public class EncerrarContaBean implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	private UsuarioGerente usuarioGerente;
	private List<UsuarioPessoaFisica> listaUsuarioPessoaFisica = new ArrayList<UsuarioPessoaFisica>();
	private List<UsuarioPessoaJuridica> listaUsuarioPessoaJuridica = new ArrayList<UsuarioPessoaJuridica>();
	private GerenciadorUsuarioPessoa gerenciadorUsuarioPessoa = new GerenciadorUsuarioPessoa();
	private GerenciadorConta gerenciadorConta = new GerenciadorConta();
	private Redirecionador redirecionador;
	private Mensagem mensagem = new Mensagem();
	
	public EncerrarContaBean() {
		this.usuarioGerente = Secao.getUsuarioGerente();
		listaUsuarioPessoaFisica = gerenciadorUsuarioPessoa.getListaPessoaFisica();
		listaUsuarioPessoaJuridica = gerenciadorUsuarioPessoa.getListaPessoaJuridica();
	}
	
	@PostConstruct
	public void init() {
		if(Secao.getUsuarioGerente()==null) {
			redirecionador.redireciona("/bancogerente/view/index/index.xhtml");
		}
	}
	
	public void encerrarClick(UsuarioPessoaFisica u) {
		if(gerenciadorConta.encerrarConta(u)) {
			mensagem.mensagemSucessoEncerrarConta(u);
			listaUsuarioPessoaFisica.remove(u);
		}else {
			
			mensagem.mensagemErroEncerrarConta(u);
		}
	}
	
	public void encerrarClick(UsuarioPessoaJuridica u) {
		if(gerenciadorConta.encerrarConta(u)) {
			mensagem.mensagemSucessoEncerrarConta(u);
			listaUsuarioPessoaJuridica.remove(u);
		}else {
			mensagem.mensagemErroEncerrarConta(u);
		}
	}
	
	public List<UsuarioPessoaFisica> getListaUsuarioPessoaFisica() {
		return listaUsuarioPessoaFisica;
	}

	public void setListaUsuarioPessoaFisica(List<UsuarioPessoaFisica> listaUsuarioPessoaFisica) {
		this.listaUsuarioPessoaFisica = listaUsuarioPessoaFisica;
	}

	public UsuarioGerente getUsuarioGerente() {
		return usuarioGerente;
	}

	public void setUsuarioGerente(UsuarioGerente usuarioGerente) {
		this.usuarioGerente = usuarioGerente;
	}

	public List<UsuarioPessoaJuridica> getListaUsuarioPessoaJuridica() {
		return listaUsuarioPessoaJuridica;
	}

	public void setListaUsuarioPessoaJuridica(List<UsuarioPessoaJuridica> listaUsuarioPessoaJuridica) {
		this.listaUsuarioPessoaJuridica = listaUsuarioPessoaJuridica;
	}
}
