package br.edu.fsma.bancocaixa.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import br.edu.fsma.bancocaixa.util.Redirecionador;
import br.edu.fsma.bancocaixa.util.Secao;
import br.edu.fsma.bancocaixa.util.UsuarioSecao;

@ManagedBean(name = "PainelBean")
@ViewScoped
public class PainelBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	private UsuarioSecao usuarioSecao;
	private Redirecionador redirecionador;
	
	public PainelBean() {
		this.usuarioSecao = Secao.getUsuarioSecao();
	}
	
	@PostConstruct
	public void init() {
		if(Secao.getUsuarioSecao()==null) {
			redirecionador.redireciona("/bancocaixa/view/index/index.xhtml");
		}
	}
	
	public String nomeUsuario() {
		if(usuarioSecao!=null) {
			return "Correntista: " + usuarioSecao.getNome() + " ";
		}
		return "";
	}
	
	public void verExtratoClick() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/bancocaixa/view/verextrato/verextrato.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void depositoCaixaClick() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/bancocaixa/view/depositocaixa/depositocaixa.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saqueCaixaClick() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/bancocaixa/view/saquecaixa/saquecaixa.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sair() {
		Secao.setUsuarioSecao(null);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/bancocaixa/view/index/index.xhtml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void painelClick() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/bancocaixa/view/painel/painel.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public UsuarioSecao getUsuarioSecao() {
		return usuarioSecao;
	}

	public void setUsuarioSecao(UsuarioSecao usuarioSecao) {
		this.usuarioSecao = usuarioSecao;
	}
	
	
	

}
