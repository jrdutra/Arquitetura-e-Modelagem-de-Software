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
	private enum Nome {PAINELINCLUIR, PAINELPESQUISAR};
	private Nome nome = Nome.PAINELPESQUISAR;
	
	public void capturaDadosPesquisa() {
		  HttpServletRequest request = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
		  currentUf.setNome(request.getParameter("frmpesquisar:input_nome_pesquisa"));
		  currentUf.setSigla(request.getParameter("frmpesquisar:input_sigla_pesquisa"));
	}
	
	public void incluirClick() {
		
		capturaDadosPesquisa();
		setIncluir();
	}
	
	public void okClick() {
		setPesquisar();
	}
	
	public String getIdToUpdate() {
		return (":frmpesquisar "
				+ ":frmResultado "
				+ ":frmnovauf"
				);
	}
	
	public void setIncluir() {
		nome = Nome.PAINELINCLUIR;
	}
	
	public boolean isMostraIncluir() {
		return (nome == Nome.PAINELINCLUIR);
	}
	
	public void setPesquisar() {
		nome = Nome.PAINELPESQUISAR;
	}
	
	public boolean isMostraPesquisar() {
		return (nome == Nome.PAINELPESQUISAR);
	}
	
	public Uf getCurrentUf() {
		return currentUf;
	}
	public void setCurrentUf(Uf currentUf) {
		this.currentUf = currentUf;
	}
	
	
	
}