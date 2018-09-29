package br.edu.fsma.fiscalizacaoweb.bean;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.fsma.fiscalizacaoweb.modelo.dao.BairroDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.dao.FiscalizacaoDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.dao.PessoaFisicaDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.dao.PessoaJuridicaDAO;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Bairro;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Fiscalizacao;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.PessoaFisica;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.PessoaJuridica;
import br.edu.fsma.fiscalizacaoweb.tx.Transacional;

@Named
@ViewScoped
public class FiscalizacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Fiscalizacao> listaFiscalizacao = new ArrayList<Fiscalizacao>();
	private Fiscalizacao currentFiscalizacao = new Fiscalizacao();
	private Long idFiscalizacao;
	
	private List<Bairro> listaBairro = new ArrayList<Bairro>();
	private Bairro currentBairro = new Bairro();
	private Long idBairro;
	
	private List<PessoaJuridica> listaPessoaJuridica = new ArrayList<PessoaJuridica>();
	private PessoaJuridica currentPessoaJuridica = new PessoaJuridica();
	private Long idPessoaJuridica;
	
	private List<PessoaFisica> listaFiscal = new ArrayList<PessoaFisica>();
	private PessoaFisica currentFiscal = new PessoaFisica();
	private Long idFiscal;
	
	private String currentCnpj;
	private String currentCpf;
	
	private Date currentDataInferior;
	private Date currentDataSuperior;
	private Date currentDataTermino;
	
	private enum Nome {PAINELINCLUIR, PAINELPESQUISAR};
	private Nome nome = Nome.PAINELPESQUISAR;
	private enum EditarNovo {EDITAR, NOVO};
	private EditarNovo flag;
	
	@Inject
	private PessoaJuridicaDAO pessoaJuridicaDao;
	
	@Inject
	private PessoaFisicaDAO pessoaFisicaDao;
	
	@Inject
	private BairroDAO bairroDao;
	
	@Inject
	private FiscalizacaoDAO fiscalizacaoDao;
	
	@PostConstruct
	void init() {
		listaFiscal = pessoaFisicaDao.listaTodos();
	}
	
	public void incluirClick() {
		listaBairro = bairroDao.listaTodos();
		listaFiscal = pessoaFisicaDao.listaTodos();
		listaPessoaJuridica = pessoaJuridicaDao.listaTodos();
		setIncluirModificar();
		flag = EditarNovo.NOVO;
	}
	
	@Transacional
	public void excluirClick(Fiscalizacao fiscalizacao) {
		try {
			fiscalizacaoDao.remove(fiscalizacao);
			listaFiscalizacao.remove(fiscalizacao);
		}
		catch (Exception e){
			System.out.println("Não foi possivel excluir: " + fiscalizacao);
		}
		setPesquisar();
	}
	
	public void editarClick(Fiscalizacao fiscalizacao) {
		currentFiscalizacao = fiscalizacao;
		
		idBairro = fiscalizacao.getBairro().getIdbairro();
		listaBairro = bairroDao.listaTodos();
		
		idPessoaJuridica = fiscalizacao.getEmpresa().getIdempresa();
		listaPessoaJuridica = pessoaJuridicaDao.listaTodos();
		
		idFiscal = fiscalizacao.getPessoaFisica().getIdpessoafisica();
		listaFiscal = pessoaFisicaDao.listaTodos();
		
		flag = EditarNovo.EDITAR;
		setIncluirModificar();
	}
	
	@Transacional
	public void okClick() {
		currentBairro = bairroDao.buscaPorId(idBairro);
		currentFiscalizacao.setBairro(currentBairro);
		
		currentPessoaJuridica = pessoaJuridicaDao.buscaPorId(idPessoaJuridica);
		currentFiscalizacao.setEmpresa(currentPessoaJuridica);
		
		currentFiscal = pessoaFisicaDao.buscaPorId(idFiscal);
		currentFiscalizacao.setPessoaFisica(currentFiscal);
		
		Instant instant = currentDataTermino.toInstant();
		LocalDate data = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		
		currentFiscalizacao.setDataterminofiscalizacao(data);
		
		if(flag == EditarNovo.NOVO) {
			fiscalizacaoDao.adiciona(currentFiscalizacao);
		}
		if(flag == EditarNovo.EDITAR) {
			fiscalizacaoDao.atualiza(currentFiscalizacao);
		}
		setPesquisar();
	}
	
	public void pesquisarPorPeriodoClick() {
		LocalDate dataInferior = conversorData(currentDataInferior);
		LocalDate dataSuperior = conversorData(currentDataSuperior);
		listaFiscalizacao = fiscalizacaoDao.buscaPorPeriodo(dataInferior, dataSuperior);
		System.out.println("Fiscalizacoes:" + listaFiscalizacao);
		setPesquisar();
	}
	
	public void pesquisarPorPeriodoEFiscalClick() {
		LocalDate dataInferior = conversorData(currentDataInferior);
		LocalDate dataSuperior = conversorData(currentDataSuperior);
		listaFiscalizacao = fiscalizacaoDao.buscaPorPeriodoEFiscal(dataInferior, dataSuperior, idFiscal);
		System.out.println("Fiscalizacoes:" + listaFiscalizacao);
		setPesquisar();
	}
	
	public void cancelarClick() {
		setPesquisar();
	}
	
	public String getIdToUpdate() {
		return (":frmpesquisarPorPeriodo "
				+ ":frmResultado "
				+ ":frmResultado:tabelaFiscalizacao "
				+ ":frmnovoFiscalizacao "
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

	public List<PessoaJuridica> getListaPessoaJuridica() {
		return listaPessoaJuridica;
	}

	public void setListaPessoaJuridica(List<PessoaJuridica> listaPessoaJuridica) {
		this.listaPessoaJuridica = listaPessoaJuridica;
	}

	public PessoaJuridica getCurrentPessoaJuridica() {
		return currentPessoaJuridica;
	}

	public void setCurrentPessoaJuridica(PessoaJuridica currentPessoaJuridica) {
		this.currentPessoaJuridica = currentPessoaJuridica;
	}

	public Long getIdPessoaJuridica() {
		return idPessoaJuridica;
	}

	public void setIdPessoaJuridica(Long idPessoaJuridica) {
		this.idPessoaJuridica = idPessoaJuridica;
	}

	public List<PessoaFisica> getListaFiscal() {
		return listaFiscal;
	}

	public void setListaFiscal(List<PessoaFisica> listaFiscal) {
		this.listaFiscal = listaFiscal;
	}

	public PessoaFisica getCurrentFiscal() {
		return currentFiscal;
	}

	public void setCurrentFiscal(PessoaFisica currentFiscal) {
		this.currentFiscal = currentFiscal;
	}

	public Long getIdFiscal() {
		return idFiscal;
	}

	public void setIdFiscal(Long idFiscal) {
		this.idFiscal = idFiscal;
	}

	public String getCurrentCnpj() {
		return currentCnpj;
	}

	public void setCurrentCnpj(String currentCnpj) {
		this.currentCnpj = currentCnpj;
	}

	public String getCurrentCpf() {
		return currentCpf;
	}

	public void setCurrentCpf(String currentCpf) {
		this.currentCpf = currentCpf;
	}

	public List<Fiscalizacao> getListaFiscalizacao() {
		return listaFiscalizacao;
	}

	public void setListaFiscalizacao(List<Fiscalizacao> listaFiscalizacao) {
		this.listaFiscalizacao = listaFiscalizacao;
	}

	public Fiscalizacao getCurrentFiscalizacao() {
		return currentFiscalizacao;
	}

	public void setCurrentFiscalizacao(Fiscalizacao currentFiscalizacao) {
		this.currentFiscalizacao = currentFiscalizacao;
	}

	public Long getIdFiscalizacao() {
		return idFiscalizacao;
	}

	public void setIdFiscalizacao(Long idFiscalizacao) {
		this.idFiscalizacao = idFiscalizacao;
	}

	public Date getCurrentDataInferior() {
		return currentDataInferior;
	}

	public void setCurrentDataInferior(Date currentDataInferior) {
		this.currentDataInferior = currentDataInferior;
	}

	public Date getCurrentDataSuperior() {
		return currentDataSuperior;
	}

	public void setCurrentDataSuperior(Date currentDataSuperior) {
		this.currentDataSuperior = currentDataSuperior;
	}

	public Date getCurrentDataTermino() {
		return currentDataTermino;
	}

	public void setCurrentDataTermino(Date currentDataTermino) {
		this.currentDataTermino = currentDataTermino;
	}

	public LocalDate conversorData(Date data) {
		return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}