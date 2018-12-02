package br.edu.fsma.bancocaixa.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.edu.fsma.bancocaixa.util.Mensagem;
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
	private Double valorSaque;
	private Mensagem mensagem = new Mensagem();
		
	public SaqueCaixaBean() {
		this.usuarioSecao = Secao.getUsuarioSecao();
	}
	
	@PostConstruct
	public void init() {
		if(Secao.getUsuarioSecao()==null) {
			redirecionador.redireciona("/bancocaixa/view/index/index.xhtml");
		}
	}
	
	public void sacarClick() {
		if(gerenciadorTransacao.sacar(usuarioSecao.getConta(), valorSaque)) {
			mensagem.mensagemValorSacadoComSucesso(valorSaque);
			this.usuarioSecao.getConta().setSaldo(this.usuarioSecao.getConta().getSaldo()-valorSaque);
		}else {
			mensagem.mensagemValorNaoSacado(valorSaque);
		}
	}

	public UsuarioSecao getUsuarioSecao() {
		return usuarioSecao;
	}

	public void setUsuarioSecao(UsuarioSecao usuarioSecao) {
		this.usuarioSecao = usuarioSecao;
	}

	public Redirecionador getRedirecionador() {
		return redirecionador;
	}

	public void setRedirecionador(Redirecionador redirecionador) {
		this.redirecionador = redirecionador;
	}

	public GerenciadorTransacao getGerenciadorTransacao() {
		return gerenciadorTransacao;
	}

	public void setGerenciadorTransacao(GerenciadorTransacao gerenciadorTransacao) {
		this.gerenciadorTransacao = gerenciadorTransacao;
	}

	public Double getValorSaque() {
		return valorSaque;
	}

	public void setValorSaque(Double valorSaque) {
		this.valorSaque = valorSaque;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
