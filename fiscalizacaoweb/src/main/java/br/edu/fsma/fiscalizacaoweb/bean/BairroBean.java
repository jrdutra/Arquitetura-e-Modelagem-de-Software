package br.edu.fsma.fiscalizacaoweb.bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import br.edu.fsma.fiscalizacaoweb.modelo.dao.BairroDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.dao.MunicipioDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.dao.UfDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Bairro;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Municipio;


@Named
@ViewScoped
public class BairroBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private List<Bairro> listaBairro = new ArrayList<Bairro>();
	private List<Municipio> listaMunicipio = new ArrayList<Municipio>();
	private Bairro currentBairro = new Bairro();
	private Municipio currentMunicipio = new Municipio();
	private Long idMunicipio;
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
	
	@Inject
	private MunicipioDAO municipioDao;
	
	@Inject
	private BairroDAO bairroDao;
	
	public BairroBean() {
		
	}
	
	public void incluirClick() {
		currentBairro = new Bairro();
		listaMunicipio = municipioDao.listaTodos();
		setIncluirModificar();
		flag = EditarNovo.NOVO;
		System.out.println(listaMunicipio);
	}
	

	public void excluirClick(Bairro bairro) {
		
		try {
			em.getTransaction().begin();
			bairroDao.remove(bairro);
			em.getTransaction().commit();
		}
		catch (Exception e){
			System.out.println("Não foi possivel excluir: " + bairro);
		}
		
		
		setPesquisar();
	}
	
	public void editarClick(Bairro bairro) {
		currentBairro = new Bairro();
		currentBairro = bairro;
		idMunicipio = bairro.getMunicipio().getIdmunicipio();
		listaMunicipio = municipioDao.listaTodos();
		flag = EditarNovo.EDITAR;
		setIncluirModificar();
		System.out.println("[editarClick]" + bairro);
		System.out.println(listaMunicipio);
	}
	
	public Nome getNome() {
		return nome;
	}

	public void setNome(Nome nome) {
		this.nome = nome;
	}

	public void okClick() {
		//capturaDadosInclusao();
		System.out.println("[Municipio id]" + idMunicipio);
		System.out.println(municipioDao.buscaPorId(idMunicipio));
		
		currentMunicipio = new Municipio();
		currentMunicipio = municipioDao.buscaPorId(idMunicipio);
		
		currentBairro.setMunicipio(currentMunicipio);
		
		em.getTransaction().begin();
		if(flag == EditarNovo.NOVO) {
			bairroDao.adiciona(currentBairro);
			listaBairro.add(currentBairro);
		}
		if(flag == EditarNovo.EDITAR) {
			bairroDao.atualiza(currentBairro);
		}
		
		em.getTransaction().commit();
		setPesquisar();
	}
	
	public void pesquisarClick() {
		//capturaDadosPesquisa();
		listaBairro = bairroDao.buscaListaBairroPorNome(currentBairro);
		setPesquisar();
		System.out.println(listaBairro);
	}
	
	public void cancelarClick() {
		setPesquisar();
	}
	
	public String getIdToUpdate() {
		return (":frmpesquisar "
				+ ":frmResultado "
				+ ":frmResultado:tabelaBairro "
				+ ":frmnovoBairro "
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

	public List<Municipio> getListaMunicipio() {
		return listaMunicipio;
	}

	public void setListaMunicipio(List<Municipio> listaMunicipio) {
		this.listaMunicipio = listaMunicipio;
	}

	public Bairro getCurrentBairro() {
		return currentBairro;
	}

	public void setCurrentBairro(Bairro currentBairro) {
		this.currentBairro = currentBairro;
	}

	public Municipio getCurrentMunicipio() {
		return currentMunicipio;
	}

	public void setCurrentMunicipio(Municipio currentMunicipio) {
		this.currentMunicipio = currentMunicipio;
	}

	public Long getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public List<Bairro> getListaBairro() {
		return listaBairro;
	}

	public void setListaBairro(List<Bairro> listaBairro) {
		this.listaBairro = listaBairro;
	}

	
}