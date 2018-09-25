package br.edu.fsma.fiscalizacaoweb.bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
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
	private enum EditarNovo {EDITAR, NOVO};
	private EditarNovo flag;
	
	
	@Inject
	private EntityManager em;
	
	@Inject
	private HttpSession session;
	
	@Inject
	private UfDAO ufDao;
	
	public UfBean() {
		
	}
	
	public void incluirClick() {
		currentUf = new Uf();
		setIncluirModificar();
		flag = EditarNovo.NOVO;
	}
	

	public void excluirClick(Uf uf) {
		
		try {
			em.getTransaction().begin();
			ufDao.remove(uf);
			em.getTransaction().commit();
		}
		catch (Exception e){
			System.out.println("Não foi possivel excluir: " + uf);
		}
		
		
		setPesquisar();
	}
	
	public void editarClick(Uf uf) {
		currentUf = new Uf();
		currentUf = uf;
		flag = EditarNovo.EDITAR;
		setIncluirModificar();
	}
	
	public void okClick() {
		//capturaDadosInclusao();
		System.out.println("[OKCLICK]" + currentUf);
		em.getTransaction().begin();
		if(flag == EditarNovo.NOVO) {
			ufDao.adiciona(currentUf);
			listaUf.add(currentUf);
		}
		if(flag == EditarNovo.EDITAR) {
			ufDao.atualiza(currentUf);
		}
		
		em.getTransaction().commit();
		setPesquisar();
	}
	
	public void pesquisarClick() {
		//capturaDadosPesquisa();
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
	
	public void setIncluirModificar() {
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