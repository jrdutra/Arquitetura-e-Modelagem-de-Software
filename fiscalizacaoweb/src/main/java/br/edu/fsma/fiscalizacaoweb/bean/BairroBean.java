package br.edu.fsma.fiscalizacaoweb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.fsma.fiscalizacaoweb.tx.Transacional;
import br.edu.fsma.fiscalizacaoweb.modelo.dao.BairroDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.dao.MunicipioDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Bairro;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Municipio;

@Named
@ViewScoped
public class BairroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Bairro> listaBairro = new ArrayList<Bairro>();
	private Bairro currentBairro = new Bairro();
	
	private List<Municipio> listaMunicipio = new ArrayList<Municipio>();
	private Municipio currentMunicipio = new Municipio();
	private Long idMunicipio;
	
	private enum Nome {PAINELINCLUIR, PAINELPESQUISAR};
	private Nome nome = Nome.PAINELPESQUISAR;
	private enum EditarNovo {EDITAR, NOVO};
	private EditarNovo flag;
	private String idToUpdate = (":frmpesquisar :frmResultado :frmResultado:tabelaBairro :frmnovoBairro");
	
	@Inject
	private MunicipioDAO municipioDao;
	
	@Inject
	private BairroDAO bairroDao;
	
	public void incluirClick() {
		listaMunicipio = municipioDao.listaTodos();
		setIncluirModificar();
		flag = EditarNovo.NOVO;
	}
	
	@Transacional
	public void excluirClick(Bairro bairro) {
		try {
			bairroDao.remove(bairro);
		}
		catch (Exception e){
			System.out.println("Não foi possivel excluir: " + bairro);
		}
		setPesquisar();
	}
	
	public void editarClick(Bairro bairro) {
		currentBairro = bairro;
		idMunicipio = bairro.getMunicipio().getId();
		listaMunicipio = municipioDao.listaTodos();
		flag = EditarNovo.EDITAR;
		setIncluirModificar();
	}
	
	public Nome getNome() {
		return nome;
	}

	public void setNome(Nome nome) {
		this.nome = nome;
	}

	@Transacional
	public void okClick() {
		currentMunicipio = municipioDao.buscaPorId(idMunicipio);
		currentBairro.setMunicipio(currentMunicipio);
		if(flag == EditarNovo.NOVO) {
			bairroDao.adiciona(currentBairro);
			listaBairro.add(currentBairro);
		}
		if(flag == EditarNovo.EDITAR) {
			bairroDao.atualiza(currentBairro);
		}
		setPesquisar();
	}
	
	public void pesquisarClick() {
		listaBairro = bairroDao.buscaListaBairroPorNome(currentBairro);
		setPesquisar();
		System.out.println(listaBairro);
	}
	
	public void cancelarClick() {
		setPesquisar();
	}
	
	public String getIdToUpdate() {
		return idToUpdate;
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