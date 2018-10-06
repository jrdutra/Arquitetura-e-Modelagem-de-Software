package br.edu.fsma.fiscalizacaoweb.bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.fsma.fiscalizacaoweb.modelo.dao.MunicipioDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.dao.UfDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Municipio;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Uf;
import br.edu.fsma.fiscalizacaoweb.tx.Transacional;


@Named
@ViewScoped
public class MunicipioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Municipio> listaMunicipio = new ArrayList<Municipio>();
	private Municipio currentMunicipio = new Municipio();
	
	private List<Uf> listaUf = new ArrayList<Uf>();
	private Uf currentUf = new Uf();
	private Long iduf;
	
	private enum Nome {PAINELINCLUIR, PAINELPESQUISAR};
	private Nome nome = Nome.PAINELPESQUISAR;
	private enum EditarNovo {EDITAR, NOVO};
	private EditarNovo flag;
	private String idsToUpdate = (":frmpesquisar :frmResultado :frmResultado:tabelaMunicipio :frmnovoMunicipio");
	
	@Inject
	private UfDAO ufDao;
	
	@Inject
	private MunicipioDAO municipioDao;
		
	public void incluirClick() {
		listaUf = ufDao.listaTodos();
		setIncluirModificar();
		flag = EditarNovo.NOVO;
	}
	
	@Transacional
	public void excluirClick(Municipio municipio) {
		try {
			municipioDao.remove(municipio);
		}
		catch (Exception e){
			System.out.println("Não foi possivel excluir: " + municipio);
		}
		setPesquisar();
	}
	
	public void editarClick(Municipio municipio) {
		currentMunicipio = municipio;
		iduf = municipio.getUf().getId();
		listaUf = ufDao.listaTodos();
		flag = EditarNovo.EDITAR;
		setIncluirModificar();
	}
	
	@Transacional
	public void okClick() {
		currentUf = ufDao.buscaPorId(iduf);
		currentMunicipio.setUf(currentUf);
		if(flag == EditarNovo.NOVO) {
			municipioDao.adiciona(currentMunicipio);
			listaMunicipio.add(currentMunicipio);
		}
		if(flag == EditarNovo.EDITAR) {
			municipioDao.atualiza(currentMunicipio);
		}
		setPesquisar();
	}
	
	public void pesquisarClick() {
		listaMunicipio = municipioDao.buscaListaMunicipioPorNome(currentMunicipio);
		setPesquisar();
		System.out.println(listaMunicipio);
	}
	
	public void cancelarClick() {
		setPesquisar();
	}
	
	public String getIdToUpdate() {
		return idsToUpdate;
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