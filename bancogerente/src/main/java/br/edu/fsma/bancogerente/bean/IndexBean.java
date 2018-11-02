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
import javax.persistence.EntityManager;

import br.edu.fsma.banconucleo.modelo.dao.UsuarioGerenteDao;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioGerente;
import br.edu.fsma.banconucleo.conexao.JPAUtil;
import br.edu.fsma.bancogerente.util.Secao;
import br.edu.fsma.banconucleo.gerenciador.GerenciadorLogin;


@ManagedBean(name = "IndexBean")
@ViewScoped
public class IndexBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<UsuarioGerente> listaUsuarioGerente = new ArrayList<UsuarioGerente>();
	private GerenciadorLogin gerenciadorLogin = new GerenciadorLogin();
	private EntityManager em;
	private UsuarioGerenteDao usuarioGerenteDao;
	private UsuarioGerente usuarioGerente = new UsuarioGerente();
	private Long idUsuarioGerente;
	private String senha;
	
	public IndexBean() {
		listaUsuarioGerente = gerenciadorLogin.getListaTodosGerentes();
	}
	
	@PostConstruct
	public void init() {
		Secao.setUsuarioGerente(null);
	}
	
	public void autenticarClick() {
		if(gerenciadorLogin.existeUsuarioGerente(this.idUsuarioGerente, this.senha)) {
			this.usuarioGerente = gerenciadorLogin.pegarDeListaPorId(this.idUsuarioGerente);
			Secao.setUsuarioGerente(usuarioGerente);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/bancogerente/view/painel/painel.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			erroDeSenha();
		}
	}

	public void erroDeSenha() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Senha incorreta."));
    }
	
	public List<UsuarioGerente> getListaUsuarioGerente() {
		return listaUsuarioGerente;
	}

	public void setListaUsuarioGerente(List<UsuarioGerente> listaUsuarioGerente) {
		this.listaUsuarioGerente = listaUsuarioGerente;
	}

	public UsuarioGerenteDao getUsuarioGerenteDao() {
		return usuarioGerenteDao;
	}

	public void setUsuarioGerenteDao(UsuarioGerenteDao usuarioGerenteDao) {
		this.usuarioGerenteDao = usuarioGerenteDao;
	}

	public UsuarioGerente getUsuarioGerente() {
		return usuarioGerente;
	}

	public void setUsuarioGerente(UsuarioGerente usuarioGerente) {
		this.usuarioGerente = usuarioGerente;
	}

	public Long getIdUsuarioGerente() {
		return idUsuarioGerente;
	}

	public void setIdUsuarioGerente(Long idUsuarioGerente) {
		this.idUsuarioGerente = idUsuarioGerente;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}