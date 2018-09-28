package br.edu.fsma.fiscalizacaoweb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
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
	private UfDAO ufDao;
	
	public void incluirClick() {
		currentUf = new Uf();
		setIncluirModificar();
		flag = EditarNovo.NOVO;
	}
		

	public void excluirClick(Uf uf) {
		this.em.getTransaction().begin();
		System.out.println(uf);
		try {
			ufDao.remove(uf);
			listaUf.remove(uf);
		}
		catch (Exception e){
			System.out.println("Não foi possivel excluir: " + uf);
		}
		setPesquisar();
		this.em.getTransaction().commit();
	}


	public void editarClick(Uf uf) {
		currentUf = uf;
		flag = EditarNovo.EDITAR;
		setIncluirModificar();
		uf = null;
	}
	
	public void okClick() {
		this.em.getTransaction().begin();
		if(flag == EditarNovo.NOVO) {
			ufDao.adiciona(currentUf);
			listaUf.add(currentUf);
		}
		if(flag == EditarNovo.EDITAR) {
			ufDao.atualiza(currentUf);
		}
		this.em.getTransaction().commit();
		setPesquisar();
	}
	
	public void pesquisarClick() {
		listaUf = ufDao.buscaListaUfPorNomeSigla(currentUf);
		setPesquisar();
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


	public List<Uf> getListaUf() {
		return listaUf;
	}


	public void setListaUf(List<Uf> listaUf) {
		this.listaUf = listaUf;
	}


	public Uf getCurrentUf() {
		return currentUf;
	}


	public void setCurrentUf(Uf currentUf) {
		this.currentUf = currentUf;
	}


	public Nome getNome() {
		return nome;
	}


	public void setNome(Nome nome) {
		this.nome = nome;
	}


	public EditarNovo getFlag() {
		return flag;
	}


	public void setFlag(EditarNovo flag) {
		this.flag = flag;
	}


	public EntityManager getEm() {
		return em;
	}


	public void setEm(EntityManager em) {
		this.em = em;
	}


	public UfDAO getUfDao() {
		return ufDao;
	}


	public void setUfDao(UfDAO ufDao) {
		this.ufDao = ufDao;
	}
	
	

	
}