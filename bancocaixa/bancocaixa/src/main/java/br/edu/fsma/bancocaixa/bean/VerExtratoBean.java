package br.edu.fsma.bancocaixa.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.edu.fsma.bancocaixa.util.Redirecionador;
import br.edu.fsma.bancocaixa.util.Secao;
import br.edu.fsma.bancocaixa.util.UsuarioSecao;
import br.edu.fsma.banconucleo.gerenciador.GerenciadorTransacao;
import br.edu.fsma.banconucleo.gerenciador.ItemExtrato;

@ManagedBean(name = "VerExtratoBean")
@ViewScoped
public class VerExtratoBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	private UsuarioSecao usuarioSecao;
	private Redirecionador redirecionador;
	private GerenciadorTransacao gerenciadorTransacao = new GerenciadorTransacao();
	private Boolean viewExtrato = false;
	private Long idConta;
	private List<ItemExtrato> listaItemExtrato;
	
	
	
	private Date dataInferior;
	private Date dataSuperior;
	
	
	public VerExtratoBean() {
		this.usuarioSecao = Secao.getUsuarioSecao();
	}
	
	@PostConstruct
	public void init() {
		if(Secao.getUsuarioSecao()==null) {
			redirecionador.redireciona("/bancocaixa/view/index/index.xhtml");
		}
	}
	
	public void verExtratoClick() {
		this.viewExtrato = true;
		System.out.println(viewExtrato);
		this.idConta = usuarioSecao.getConta().getId();
		listaItemExtrato = gerenciadorTransacao.getListaItemExtratoPorPeriodo(this.idConta, dataInferior, dataSuperior);
		Collections.sort(listaItemExtrato);
		System.out.println(listaItemExtrato);
	}

	public UsuarioSecao getUsuarioSecao() {
		return usuarioSecao;
	}

	public void setUsuarioSecao(UsuarioSecao usuarioSecao) {
		this.usuarioSecao = usuarioSecao;
	}

	public Boolean getViewExtrato() {
		return viewExtrato;
	}

	public void setViewExtrato(Boolean viewExtrato) {
		this.viewExtrato = viewExtrato;
	}

	public Long getIdConta() {
		return idConta;
	}

	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}

	public List<ItemExtrato> getListaItemExtrato() {
		return listaItemExtrato;
	}

	public void setListaItemExtrato(List<ItemExtrato> listaItemExtrato) {
		this.listaItemExtrato = listaItemExtrato;
	}

	public Date getDataInferior() {
		return dataInferior;
	}

	public void setDataInferior(Date dataInferior) {
		this.dataInferior = dataInferior;
	}

	public Date getDataSuperior() {
		return dataSuperior;
	}

	public void setDataSuperior(Date dataSuperior) {
		this.dataSuperior = dataSuperior;
	}
	
	
	
}
