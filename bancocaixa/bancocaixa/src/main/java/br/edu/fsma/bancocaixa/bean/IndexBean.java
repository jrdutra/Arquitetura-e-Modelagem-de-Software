package br.edu.fsma.bancocaixa.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import br.edu.fsma.bancocaixa.util.Secao;
import br.edu.fsma.bancocaixa.util.UsuarioSecao;
import br.edu.fsma.banconucleo.gerenciador.GerenciadorLogin;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaFisica;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaJuridica;


@ManagedBean(name = "IndexBean")
@ViewScoped
public class IndexBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private List<UsuarioSecao> listaUsuarioSecao = new ArrayList<UsuarioSecao>();
	private List<UsuarioPessoaFisica> listaUsuarioPessoaFisica = new ArrayList<UsuarioPessoaFisica>();
	private List<UsuarioPessoaJuridica> listaUsuarioPessoaJuridica = new ArrayList<UsuarioPessoaJuridica>();
	private GerenciadorLogin gerenciadorLogin = new GerenciadorLogin();
	private UsuarioSecao usuarioSecao = new UsuarioSecao();
	private int idUsuarioSecao;
	private String senha;
	
	public IndexBean() {
		listaUsuarioPessoaFisica = gerenciadorLogin.getListaTodosUsuarioPessoaFisica();
		listaUsuarioPessoaJuridica = gerenciadorLogin.getListaTodosUsuarioPessoaJuridica();
		listaUsuarioSecao = usuarioSecao.getListaUsuarioSecao(listaUsuarioPessoaFisica, listaUsuarioPessoaJuridica);
		System.out.println(listaUsuarioSecao);
	}
	
	@PostConstruct
	public void init() {
		Secao.setUsuarioSecao(null);
	}
	
	public void autenticarClick() {
		
		System.out.println(existeUsuarioSecao(idUsuarioSecao, senha));
		if(existeUsuarioSecao(idUsuarioSecao, senha)) {
			this.usuarioSecao = retornaUsuarioSecaoPorId(idUsuarioSecao);
			Secao.setUsuarioSecao(usuarioSecao);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/bancocaixa/view/painel/painel.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			erroDeSenha();
		}
	}

	private UsuarioSecao retornaUsuarioSecaoPorId(int idUsuarioSecao2) {
		for(int i=0; i<listaUsuarioSecao.size(); i++) {
			if(listaUsuarioSecao.get(i).getId() == idUsuarioSecao2) {
				return listaUsuarioSecao.get(i);
			}
		}
		return null;
	}

	private boolean existeUsuarioSecao(int idUsuarioSecao2, String senha2) {
		
		for(int i=0; i<listaUsuarioSecao.size(); i++) {
			System.out.println(senha2+"=="+listaUsuarioSecao.get(i).getSenha());
			System.out.println(idUsuarioSecao2+"=="+listaUsuarioSecao.get(i).getId());
			if(listaUsuarioSecao.get(i).getId() == idUsuarioSecao2 && listaUsuarioSecao.get(i).getSenha().equals(senha2)) {
				System.out.println("Achou!");
				
				return true;
			}
		}
		return false;
	}

	public void erroDeSenha() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Senha incorreta."));
    }
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<UsuarioSecao> getListaUsuarioSecao() {
		return listaUsuarioSecao;
	}

	public void setListaUsuarioSecao(List<UsuarioSecao> listaUsuarioSecao) {
		this.listaUsuarioSecao = listaUsuarioSecao;
	}

	public List<UsuarioPessoaFisica> getListaUsuarioPessoaFisica() {
		return listaUsuarioPessoaFisica;
	}

	public void setListaUsuarioPessoaFisica(List<UsuarioPessoaFisica> listaUsuarioPessoaFisica) {
		this.listaUsuarioPessoaFisica = listaUsuarioPessoaFisica;
	}

	public List<UsuarioPessoaJuridica> getListaUsuarioPessoaJuridica() {
		return listaUsuarioPessoaJuridica;
	}

	public void setListaUsuarioPessoaJuridica(List<UsuarioPessoaJuridica> listaUsuarioPessoaJuridica) {
		this.listaUsuarioPessoaJuridica = listaUsuarioPessoaJuridica;
	}

	public UsuarioSecao getUsuarioSecao() {
		return usuarioSecao;
	}

	public void setUsuarioSecao(UsuarioSecao usuarioSecao) {
		this.usuarioSecao = usuarioSecao;
	}

	public int getIdUsuarioSecao() {
		return idUsuarioSecao;
	}

	public void setIdUsuarioSecao(int idUsuarioSecao) {
		this.idUsuarioSecao = idUsuarioSecao;
	}
	
}