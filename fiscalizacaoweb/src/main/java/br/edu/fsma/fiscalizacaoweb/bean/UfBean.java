package br.edu.fsma.fiscalizacaoweb.bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.edu.fsma.fiscalizacaoweb.modelo.dao.UfDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Uf;


@Named
@ViewScoped
public class UfBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private List<Uf> listaUf = new ArrayList<Uf>();
	private Uf currentUf = new Uf();
	private enum Nome {PAINELINCLUIR, PAINELPESQUISAR};
	private Nome nome = Nome.PAINELPESQUISAR;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private HttpSession session;
	
	@Inject
	private UfDAO ufDao;
	
	public UfBean() {
		
	}
	
	public void capturaDadosPesquisa() {
		  HttpServletRequest request = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
		  currentUf.setNome(request.getParameter("frmpesquisar:input_nome_pesquisa").toUpperCase());
		  currentUf.setSigla(request.getParameter("frmpesquisar:input_sigla_pesquisa").toUpperCase());
		  System.out.println("[FUN: DADOS PESQUISA] - CurrentUF: " + currentUf);
	}
	
	public void capturaDadosInclusao() {
		  HttpServletRequest request = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
		  currentUf.setNome(request.getParameter("frmnovauf:inputnomeinclusao").toUpperCase());
		  currentUf.setSigla(request.getParameter("frmnovauf:inputsiglainclusao").toUpperCase());
		  System.out.println("[FUN: DADOS INCLUSAO] - CurrentUF: " + currentUf);
	}
	
	public void incluirClick() {
		currentUf = new Uf();
		setIncluir();
	}
	
	public void okClick() {
		capturaDadosInclusao();
		em.getTransaction().begin();
		ufDao.escreveUf(currentUf);
		em.getTransaction().commit();
		setPesquisar();
		
	}
	
	public void pesquisarClick() {
		capturaDadosPesquisa();
		listaUf = ufDao.buscaListaUfPorNomeSigla(currentUf);
		setPesquisar();
		System.out.println(listaUf);
	}
	
	public void cancelarClick() {
		setPesquisar();
	}
	
	public String getIdToUpdate() {
		return (":frmpesquisar "
				+ ":frmResultado "
				+ ":frmResultado:tabelaUf "
				+ ":frmnovauf "
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

	public List<Uf> getListaUf() {
		return this.listaUf;
	}

	public void setListaUf(List<Uf> listaUf) {
		this.listaUf = listaUf;
	}
}