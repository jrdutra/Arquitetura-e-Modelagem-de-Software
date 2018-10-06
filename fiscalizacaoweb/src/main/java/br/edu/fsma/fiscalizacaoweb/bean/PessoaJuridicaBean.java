package br.edu.fsma.fiscalizacaoweb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.fsma.fiscalizacaoweb.modelo.dao.BairroDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.dao.PessoaJuridicaDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Bairro;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.PessoaJuridica;
import br.edu.fsma.fiscalizacaoweb.tx.Transacional;

@Named
@ViewScoped
public class PessoaJuridicaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Bairro> listaBairro = new ArrayList<Bairro>();
	private Bairro currentBairro = new Bairro();
	private Long idBairro;
	
	private List<PessoaJuridica> listaPessoaJuridica = new ArrayList<PessoaJuridica>();
	private PessoaJuridica currentPessoaJuridica = new PessoaJuridica();
	private String currentCnpj;
	
	private enum Nome {PAINELINCLUIR, PAINELPESQUISAR};
	private Nome nome = Nome.PAINELPESQUISAR;
	private enum EditarNovo {EDITAR, NOVO};
	private EditarNovo flag;
	private String idToUpdate = (":frmpesquisar :frmResultado :frmResultado:tabelaPessoaJuridica :frmnovoPessoaJuridica");
	
	@Inject
	private PessoaJuridicaDAO pessoaJuridicaDao;
	
	@Inject
	private BairroDAO bairroDao;
	
	public void incluirClick() {
		listaBairro = bairroDao.listaTodos();
		setIncluirModificar();
		flag = EditarNovo.NOVO;
	}
	
	@Transacional
	public void excluirClick(PessoaJuridica pessoaJuridica) {
		try {
			pessoaJuridicaDao.remove(pessoaJuridica);
			listaPessoaJuridica.remove(pessoaJuridica);
		}
		catch (Exception e){
			System.out.println("Não foi possivel excluir: " + pessoaJuridica);
		}
		setPesquisar();
	}
	
	public void editarClick(PessoaJuridica pessoaJuridica) {
		currentPessoaJuridica = pessoaJuridica;
		idBairro = pessoaJuridica.getBairro().getId();
		listaBairro = bairroDao.listaTodos();
		flag = EditarNovo.EDITAR;
		setIncluirModificar();
	}

	@Transacional
	public void okClick() {
		currentBairro = bairroDao.buscaPorId(idBairro);
		currentPessoaJuridica.setBairro(currentBairro);
		if(flag == EditarNovo.NOVO) {
			pessoaJuridicaDao.adiciona(currentPessoaJuridica);
		}
		if(flag == EditarNovo.EDITAR) {
			pessoaJuridicaDao.atualiza(currentPessoaJuridica);
		}
		setPesquisar();
	}
	
	public void pesquisarClick() {
		currentPessoaJuridica = pessoaJuridicaDao.buscaPessoaPeloCnpj(currentCnpj);
		listaPessoaJuridica.add(currentPessoaJuridica);
		System.out.println(currentPessoaJuridica);
		setPesquisar();
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

	public List<Bairro> getListaBairro() {
		return listaBairro;
	}

	public void setListaBairro(List<Bairro> listaBairro) {
		this.listaBairro = listaBairro;
	}

	public List<PessoaJuridica> getListaPessoaJuridica() {
		return listaPessoaJuridica;
	}

	public void setListaPessoaJuridica(List<PessoaJuridica> listaPessoaJuridica) {
		this.listaPessoaJuridica = listaPessoaJuridica;
	}

	public Bairro getCurrentBairro() {
		return currentBairro;
	}

	public void setCurrentBairro(Bairro currentBairro) {
		this.currentBairro = currentBairro;
	}

	public String getCurrentCnpj() {
		return currentCnpj;
	}

	public void setCurrentCnpj(String currentCnpj) {
		this.currentCnpj = currentCnpj;
	}

	public PessoaJuridica getCurrentPessoaJuridica() {
		return currentPessoaJuridica;
	}

	public void setCurrentPessoaJuridica(PessoaJuridica currentPessoaJuridica) {
		this.currentPessoaJuridica = currentPessoaJuridica;
	}

	public Long getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(Long idBairro) {
		this.idBairro = idBairro;
	}
	
	public Nome getNome() {
		return nome;
	}

	public void setNome(Nome nome) {
		this.nome = nome;
	}
	
}