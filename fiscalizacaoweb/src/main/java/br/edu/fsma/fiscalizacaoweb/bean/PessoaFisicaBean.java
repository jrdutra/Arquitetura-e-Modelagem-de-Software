package br.edu.fsma.fiscalizacaoweb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.fsma.fiscalizacaoweb.modelo.dao.BairroDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.dao.PessoaFisicaDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Bairro;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.PessoaFisica;
import br.edu.fsma.fiscalizacaoweb.tx.Transacional;

@Named
@ViewScoped
public class PessoaFisicaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Bairro> listaBairro = new ArrayList<Bairro>();
	private List<PessoaFisica> listaPessoaFisica = new ArrayList<PessoaFisica>();
	private Bairro currentBairro = new Bairro();
	private Long idBairro;
	private String currentCpf;
	private PessoaFisica currentPessoaFisica = new PessoaFisica();
	private enum Nome {PAINELINCLUIR, PAINELPESQUISAR};
	private Nome nome = Nome.PAINELPESQUISAR;
	private enum EditarNovo {EDITAR, NOVO};
	private EditarNovo flag;
	
	@Inject
	private PessoaFisicaDAO pessoaFisicaDao;
	
	@Inject
	private BairroDAO bairroDao;
	
	public void incluirClick() {
		listaBairro = bairroDao.listaTodos();
		setIncluirModificar();
		flag = EditarNovo.NOVO;
	}
	
	@Transacional
	public void excluirClick(PessoaFisica pessoaFisica) {
		try {
			pessoaFisicaDao.remove(pessoaFisica);
			listaPessoaFisica.remove(pessoaFisica);
		}
		catch (Exception e){
			System.out.println("Não foi possivel excluir: " + pessoaFisica);
		}
		setPesquisar();
	}
	
	public void editarClick(PessoaFisica pessoaFisica) {
		currentPessoaFisica = pessoaFisica;
		idBairro = pessoaFisica.getBairro().getIdbairro();
		listaBairro = bairroDao.listaTodos();
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
		currentBairro = bairroDao.buscaPorId(idBairro);
		currentPessoaFisica.setBairro(currentBairro);
		if(flag == EditarNovo.NOVO) {
			pessoaFisicaDao.adiciona(currentPessoaFisica);
		}
		if(flag == EditarNovo.EDITAR) {
			pessoaFisicaDao.atualiza(currentPessoaFisica);
		}
		setPesquisar();
	}
	
	public void pesquisarClick() {
		currentPessoaFisica = pessoaFisicaDao.buscaPessoaPeloCpf(currentCpf);
		listaPessoaFisica.add(currentPessoaFisica);
		System.out.println(currentPessoaFisica);
		setPesquisar();
	}
	
	public void cancelarClick() {
		setPesquisar();
	}
	
	public String getIdToUpdate() {
		return (":frmpesquisar "
				+ ":frmResultado "
				+ ":frmResultado:tabelaPessoaFisica "
				+ ":frmnovoPessoaFisica "
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

	public List<Bairro> getListaBairro() {
		return listaBairro;
	}

	public void setListaBairro(List<Bairro> listaBairro) {
		this.listaBairro = listaBairro;
	}

	public Bairro getCurrentBairro() {
		return currentBairro;
	}

	public void setCurrentBairro(Bairro currentBairro) {
		this.currentBairro = currentBairro;
	}

	public Long getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(Long idBairro) {
		this.idBairro = idBairro;
	}

	public String getCurrentCpf() {
		return currentCpf;
	}

	public void setCurrentCpf(String currentCpf) {
		this.currentCpf = currentCpf;
	}

	public PessoaFisica getCurrentPessoaFisica() {
		return currentPessoaFisica;
	}

	public void setCurrentPessoaFisica(PessoaFisica currentPessoaFisica) {
		this.currentPessoaFisica = currentPessoaFisica;
	}

	public List<PessoaFisica> getListaPessoaFisica() {
		return listaPessoaFisica;
	}

	public void setListaPessoaFisica(List<PessoaFisica> listaPessoaFisica) {
		this.listaPessoaFisica = listaPessoaFisica;
	}
	
	
}