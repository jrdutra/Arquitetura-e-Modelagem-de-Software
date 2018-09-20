package br.edu.fsma.fiscalizacaoweb.bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Uf;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


@Named
@ViewScoped
public class UfBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private List<Uf> ufs = new ArrayList<Uf>();
	private Uf currentUf = new Uf();
	
	public void capturaDadosPesquisa() {
		  HttpServletRequest request = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
		  currentUf.setNome(request.getParameter("frm_pesquisar_uf:input_nome_pesquisa"));
		  currentUf.setSigla(request.getParameter("frm_pesquisar_uf:input_sigla_pesquisa"));
		  System.out.println("AQUI!");
	}
	
	public Uf getCurrentUf() {
		return currentUf;
	}
	public void setCurrentUf(Uf currentUf) {
		this.currentUf = currentUf;
	}
	
	
	
}