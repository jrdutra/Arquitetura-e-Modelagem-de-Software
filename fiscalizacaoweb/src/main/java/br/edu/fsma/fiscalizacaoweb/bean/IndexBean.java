package br.edu.fsma.fiscalizacaoweb.bean;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class IndexBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private enum Cor {VERMELHO, VERDE, AZUL, NONE};
	
	private Cor cor = Cor.NONE;
	
	
	public void setVermelho() {
		cor = Cor.VERMELHO;
	}
	
	public void setVerde() {
		cor = Cor.VERDE;
	}
	
	public void setAzul() {
		cor = Cor.AZUL;
	}
	
	public boolean isMostraVermelho() {
		return (cor == Cor.VERMELHO);
	}
	
	public boolean isMostraVerde() {
		return (cor == Cor.VERDE);
	}
	
	public boolean isMostraAzul() {
		return (cor == Cor.AZUL);
	}
	
}
