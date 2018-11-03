package br.edu.fsma.bancogerente.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
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
	
	public EncerrarContaBean() {
		this.usuarioGerente = Secao.getUsuarioGerente();
		listaUsuarioPessoaFisica = gerenciadorUsuarioPessoa.getListaPessoaFisica();
		listaUsuarioPessoaJuridica = gerenciadorUsuarioPessoa.getListaPessoaJuridica();
	}
	
	@PostConstruct
	public void init() {
		if(Secao.getUsuarioGerente()==null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/bancogerente/view/index/index.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void encerrarClick(UsuarioPessoaFisica u) {
		if(gerenciadorConta.encerrarConta(u)) {
			mensagemSucessoEncerrarConta(u);
		}else {
			mensagemErroEncerrarConta(u);
		}
	}
	
	public void encerrarClick(UsuarioPessoaJuridica u) {
		if(gerenciadorConta.encerrarConta(u)) {
			mensagemSucessoEncerrarConta(u);
		}else {
			mensagemErroEncerrarConta(u);
		}
	}
	
	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	private void mensagemSucessoEncerrarConta(UsuarioPessoaFisica u) {
		addMessage("Sucesso", "A conta " 
				+ u.getConta().getNumero() + " da agência " 
				+ u.getConta().getAgencia() + " pertencente a "
				+ u.getPessoaFisica().getNome() + " foi encerrada com sucesso.");
	}
	
	private void mensagemErroEncerrarConta(UsuarioPessoaFisica u) {
		addMessage("Erro", "A conta " 
				+ u.getConta().getNumero() + " da agência " 
				+ u.getConta().getAgencia() + " pertencente a "
				+ u.getPessoaFisica().getNome() + " não foi encerrada.");
	}
	
	private void mensagemSucessoEncerrarConta(UsuarioPessoaJuridica u) {
		addMessage("Sucesso", "A conta " 
				+ u.getConta().getNumero() + " da agência " 
				+ u.getConta().getAgencia() + " pertencente a "
				+ u.getPessoaJuridica().getRazaosocial() + " foi encerrada com sucesso.");
	}
	
	private void mensagemErroEncerrarConta(UsuarioPessoaJuridica u) {
		addMessage("Erro", "A conta " 
				+ u.getConta().getNumero() + " da agência " 
				+ u.getConta().getAgencia() + " pertencente a "
				+ u.getPessoaJuridica().getRazaosocial() + " não foi encerrada.");
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
