package br.edu.fsma.fiscalizacaoweb.bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.edu.fsma.fiscalizacaoweb.modelo.dao.MunicipioDao;
import br.edu.fsma.fiscalizacaoweb.modelo.dao.UfDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Municipio;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Uf;


@Named
@ViewScoped
public class MunicipioBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private List<Municipio> listaMunicipio = new ArrayList<Municipio>();
	private List<Uf> listaUf = new ArrayList<Uf>();
	private Municipio currentMunicipio = new Municipio();
	private Long iduf;
	private enum Nome {PAINELINCLUIR, PAINELPESQUISAR};
	private Nome nome = Nome.PAINELPESQUISAR;
	private enum EditarNovo {EDITAR, NOVO};
	private EditarNovo flag;
	
	@Inject
	private EntityManager em;
	@Inject
	private UfDAO ufDao;
	@Inject
	private MunicipioDao municipioDao;
	
	public void incluirClick() {
		currentMunicipio = new Municipio();
		listaUf = ufDao.listaTodos();
		setIncluirModificar();
		flag = EditarNovo.NOVO;
	}

	public void excluirClick(Municipio municipio) {
		
		try {
			em.getTransaction().begin();
			municipioDao.remove(municipio);
			em.getTransaction().commit();
		}
		catch (Exception e){
			System.out.println("Não foi possivel excluir: " + municipio);
		}
		
		setPesquisar();
	}
	
	public void editarClick(Municipio municipio) {
		currentMunicipio = new Municipio();
		currentMunicipio = municipio;
		iduf = municipio.getUf().getIduf();
		listaUf = ufDao.listaTodos();
		flag = EditarNovo.EDITAR;
		setIncluirModificar();
	}
	
	public void okClick() {
		em.getTransaction().begin();
		
		Uf uf = ufDao.buscaPorId(iduf);
		currentMunicipio.setUf(uf);

		municipioDao.persiste(currentMunicipio);
		if(flag == EditarNovo.NOVO) {
			listaMunicipio.add(currentMunicipio);
		}
		
		em.getTransaction().commit();
		setPesquisar();
	}
	
	public void pesquisarClick() {
		//capturaDadosPesquisa();
		listaMunicipio = municipioDao.buscaListaMunicipioPorNome(currentMunicipio);
		setPesquisar();
		System.out.println(listaMunicipio);
	}
	
	public void cancelarClick() {
		setPesquisar();
	}
	
	public String getIdToUpdate() {
		return (":frmpesquisar "
				+ ":frmResultado "
				+ ":frmResultado:tabelaMunicipio "
				+ ":frmnovoMunicipio "
				);
	}
	
	public void setIncluirModificar() {
		nome = Nome.PAINELINCLUIR;
	}
	
	public boolean isMostraIncluir() {
		return (nome == Nome.PAINELINCLUIR);
	}
	
	public Long getIduf() {
		return iduf;
	}

	public void setIduf(Long iduf) {
		this.iduf = iduf;
	}

	public void setPesquisar() {
		nome = Nome.PAINELPESQUISAR;
	}
	
	public boolean isMostraPesquisar() {
		return (nome == Nome.PAINELPESQUISAR);
	}
	
	public Municipio getCurrentMunicipio() {
		return currentMunicipio;
	}
	public void setCurrentMunicipio(Municipio currentMunicipio) {
		this.currentMunicipio = currentMunicipio;
	}

	public List<Municipio> getListaMunicipio() {
		return this.listaMunicipio;
	}

	public void setListaMunicipio(List<Municipio> listaMunicipio) {
		this.listaMunicipio = listaMunicipio;
	}

	public List<Uf> getListaUf() {
		return listaUf;
	}

	public void setListaUf(List<Uf> listaUf) {
		this.listaUf = listaUf;
	}
	
	
}