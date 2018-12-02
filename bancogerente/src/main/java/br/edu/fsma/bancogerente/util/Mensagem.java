package br.edu.fsma.bancogerente.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaFisica;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaJuridica;

public class Mensagem {
	
	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public void mensagemSucessoEncerrarConta(UsuarioPessoaFisica u) {
		this.addMessage("Sucesso", "A conta " 
				+ u.getConta().getNumero() + " da agência " 
				+ u.getConta().getAgencia() + " pertencente a "
				+ u.getPessoaFisica().getNome() + " foi encerrada com sucesso.");
	}
	
	public void mensagemErroEncerrarConta(UsuarioPessoaFisica u) {
		if(u.getConta().getSaldo() != 0.0) {
			this.addMessage("Erro - Conta Com Saldo", "A conta " 
					+ u.getConta().getNumero() + " da agência " 
					+ u.getConta().getAgencia() + " pertencente a "
					+ u.getPessoaFisica().getNome() + " não foi encerrada,"
					+"pois ainda há saldo nesta conta."
					+"Saque todo o dinheiro desta conta.");
		}else {
			this.addMessage("Erro", "A conta " 
					+ u.getConta().getNumero() + " da agência " 
					+ u.getConta().getAgencia() + " pertencente a "
					+ u.getPessoaFisica().getNome() + " não foi encerrada.");
		}
		
		
	}
	
	public void mensagemSucessoEncerrarConta(UsuarioPessoaJuridica u) {
		this.addMessage("Sucesso", "A conta " 
				+ u.getConta().getNumero() + " da agência " 
				+ u.getConta().getAgencia() + " pertencente a "
				+ u.getPessoaJuridica().getRazaosocial() + " foi encerrada com sucesso.");
	}
	
	public void mensagemErroEncerrarConta(UsuarioPessoaJuridica u) {
		this.addMessage("Erro", "A conta " 
				+ u.getConta().getNumero() + " da agência " 
				+ u.getConta().getAgencia() + " pertencente a "
				+ u.getPessoaJuridica().getRazaosocial() + " não foi encerrada.");
	}
}
