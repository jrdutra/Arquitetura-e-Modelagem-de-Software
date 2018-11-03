package br.edu.fsma.bancogerente.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.edu.fsma.bancogerente.util.Redirecionador;
import br.edu.fsma.bancogerente.util.Secao;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioGerente;

@ManagedBean(name = "AdicionarTitularBean")
@ViewScoped
public class AdicionarTitularBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	private UsuarioGerente usuarioGerente;
	private Redirecionador redirecionador;
	
	public AdicionarTitularBean() {
		this.usuarioGerente = Secao.getUsuarioGerente();
	}
	
	@PostConstruct
	public void init() {
		if(Secao.getUsuarioGerente()==null) {
			redirecionador.redireciona("/bancogerente/view/index/index.xhtml");
		}
	}
	
	public UsuarioGerente getUsuarioGerente() {
		return usuarioGerente;
	}

	public void setUsuarioGerente(UsuarioGerente usuarioGerente) {
		this.usuarioGerente = usuarioGerente;
	}
	

}
