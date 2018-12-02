package br.edu.fsma.bancocaixa.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaFisica;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaJuridica;

public class Mensagem {
	
	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public void mensagemValorDepositadoComSucesso(Double valorDeposito) {
		this.addMessage("Sucesso!", "O valor de R$ " + valorDeposito + " foi depositado em sua conta!");
	}

	public void mensagemValorNaoDepositado(Double valorDeposito) {
		this.addMessage("Houve um proble!", "O valor de R$ " + valorDeposito + " não foi depositado em sua conta!");
	}

	public void mensagemValorSacadoComSucesso(Double valorSaque) {
		this.addMessage("Sucesso!", "O valor de R$ " + valorSaque + " foi sacado em sua conta!");
		
	}

	public void mensagemValorNaoSacado(Double valorSaque) {
		this.addMessage("Houve um proble!", "O valor de R$ " + valorSaque + " não foi sacado em sua conta!");
	}
}
