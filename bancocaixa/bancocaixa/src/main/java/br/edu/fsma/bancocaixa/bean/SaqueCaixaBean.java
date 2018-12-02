package br.edu.fsma.bancocaixa.bean;

import java.io.Serializable;
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

@ManagedBean(name = "SaqueCaixaBean")
@ViewScoped
public class SaqueCaixaBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	private UsuarioSecao usuarioSecao;
	private Redirecionador redirecionador;
	private GerenciadorTransacao gerenciadorTransacao = new GerenciadorTransacao();
	private Long idConta;
	private List<ItemExtrato> listaItemExtrato;
		
	public SaqueCaixaBean() {
		this.usuarioSecao = Secao.getUsuarioSecao();
	}
	
	@PostConstruct
	public void init() {
		if(Secao.getUsuarioSecao()==null) {
			redirecionador.redireciona("/bancocaixa/view/index/index.xhtml");
		}
	}
	
	public UsuarioSecao getUsuarioSecao() {
		return usuarioSecao;
	}

	public void setUsuarioSecao(UsuarioSecao usuarioSecao) {
		this.usuarioSecao = usuarioSecao;
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
}
