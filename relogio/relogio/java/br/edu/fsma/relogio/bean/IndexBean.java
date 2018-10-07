package br.edu.fsma.relogio.bean;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class IndexBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private enum Fundo {BRANCO, AMARELO};
	private Fundo fundo = Fundo.BRANCO;
	private String corDeFundo = "background-color: yellow";
	
	private boolean exibir;
	private boolean ajustar;
	private boolean acertarHora;
	private boolean acertarMinuto;
	
	
	
	public String getCorDeFundo() {
		return corDeFundo;
	}
	public void setCorDeFundo(String corDeFundo) {
		this.corDeFundo = corDeFundo;
	}
	
	
	
	
	
	
}
