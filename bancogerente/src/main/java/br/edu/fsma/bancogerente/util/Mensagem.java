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
		addMessage("Sucesso", "A conta " 
				+ u.getConta().getNumero() + " da agência " 
				+ u.getConta().getAgencia() + " pertencente a "
				+ u.getPessoaFisica().getNome() + " foi encerrada com sucesso.");
	}
	
	public void mensagemErroEncerrarConta(UsuarioPessoaFisica u) {
		addMessage("Erro", "A conta " 
				+ u.getConta().getNumero() + " da agência " 
				+ u.getConta().getAgencia() + " pertencente a "
				+ u.getPessoaFisica().getNome() + " não foi encerrada.");
	}
	
	public void mensagemSucessoEncerrarConta(UsuarioPessoaJuridica u) {
		addMessage("Sucesso", "A conta " 
				+ u.getConta().getNumero() + " da agência " 
				+ u.getConta().getAgencia() + " pertencente a "
				+ u.getPessoaJuridica().getRazaosocial() + " foi encerrada com sucesso.");
	}
	
	public void mensagemErroEncerrarConta(UsuarioPessoaJuridica u) {
		addMessage("Erro", "A conta " 
				+ u.getConta().getNumero() + " da agência " 
				+ u.getConta().getAgencia() + " pertencente a "
				+ u.getPessoaJuridica().getRazaosocial() + " não foi encerrada.");
	}
}
