package br.edu.fsma.relogio.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import br.edu.fsma.relogio.modelo.negocio.Relogio;

@ManagedBean (name = "IndexBean")
@ViewScoped
public class IndexBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private enum Fundo {BRANCO, AMARELO};
	private Fundo fundo = Fundo.AMARELO;

	private Relogio relogio;
	
	
	private boolean exibir;
	private boolean ajustar;
	private boolean acertarHora;
	private boolean acertarMinuto;
	
	private void botaoAClick() {
		System.out.println("Boto A CLicado");
	}
	
	private void botaoBClick() {
		System.out.println("Boto B CLicado");
	}
	
	private void setFundoBranco() {
		this.fundo = Fundo.BRANCO;
	}
	
	private void setFundoAmarelo() {
		this.fundo = Fundo.AMARELO;
	}

	public boolean isMostraBranco() {
		return (fundo == Fundo.BRANCO);
	}
	
	public boolean isMostraAmarelo() {
		System.out.println(fundo == Fundo.AMARELO);
		return (fundo == Fundo.AMARELO);
		
	}
	
	public Relogio getRelogio() {
		return relogio;
	}

	public void setRelogio(Relogio relogio) {
		this.relogio = relogio;
	}

	public Fundo getFundo() {
		return fundo;
	}

	public void setFundo(Fundo fundo) {
		this.fundo = fundo;
	}

	public boolean isExibir() {
		return exibir;
	}

	public void setExibir(boolean exibir) {
		this.exibir = exibir;
	}

	public boolean isAjustar() {
		return ajustar;
	}

	public void setAjustar(boolean ajustar) {
		this.ajustar = ajustar;
	}

	public boolean isAcertarHora() {
		return acertarHora;
	}

	public void setAcertarHora(boolean acertarHora) {
		this.acertarHora = acertarHora;
	}

	public boolean isAcertarMinuto() {
		return acertarMinuto;
	}

	public void setAcertarMinuto(boolean acertarMinuto) {
		this.acertarMinuto = acertarMinuto;
	}
	
	
}
