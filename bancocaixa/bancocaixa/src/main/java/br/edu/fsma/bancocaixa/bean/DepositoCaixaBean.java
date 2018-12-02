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
import br.edu.fsma.bancocaixa.util.Mensagem;
import br.edu.fsma.banconucleo.gerenciador.GerenciadorTransacao;
import br.edu.fsma.banconucleo.gerenciador.ItemExtrato;

@ManagedBean(name = "DepositoCaixaBean")
@ViewScoped
public class DepositoCaixaBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	private UsuarioSecao usuarioSecao;
	private Redirecionador redirecionador;
	private GerenciadorTransacao gerenciadorTransacao = new GerenciadorTransacao();
	private Double valorDeposito;
	private Mensagem mensagem = new Mensagem();
	
	public DepositoCaixaBean() {
		this.usuarioSecao = Secao.getUsuarioSecao();
	}
	
	@PostConstruct
	public void init() {
		if(Secao.getUsuarioSecao()==null) {
			redirecionador.redireciona("/bancocaixa/view/index/index.xhtml");
		}
	}
	
	public void depositarClick() {
		if(gerenciadorTransacao.depositar(usuarioSecao.getConta(), valorDeposito)) {
			mensagem.mensagemValorDepositadoComSucesso(valorDeposito);
			this.usuarioSecao.getConta().setSaldo(this.usuarioSecao.getConta().getSaldo()+valorDeposito);
		}else {
			mensagem.mensagemValorNaoDepositado(valorDeposito);
		}
	}
	
	
	public UsuarioSecao getUsuarioSecao() {
		return usuarioSecao;
	}

	public void setUsuarioSecao(UsuarioSecao usuarioSecao) {
		this.usuarioSecao = usuarioSecao;
	}

	public Double getValorDeposito() {
		return valorDeposito;
	}

	public void setValorDeposito(Double valorDeposito) {
		this.valorDeposito = valorDeposito;
	}


}
