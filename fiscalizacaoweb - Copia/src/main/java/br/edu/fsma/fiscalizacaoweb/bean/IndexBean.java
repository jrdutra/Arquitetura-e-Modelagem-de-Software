package br.edu.fsma.fiscalizacaoweb.bean;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class IndexBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private enum Nome {VERMELHO, VERDE, AZUL, PAINELPESQUISAFISCALIZACAO, NONE};
	
	private Nome nome = Nome.NONE;
	
	
	public void setVermelho() {
		nome = Nome.VERMELHO;
	}
	
	public boolean isMostraVermelho() {
		return (nome == Nome.VERMELHO);
	}
	
	public void setVerde() {
		nome = Nome.VERDE;
	}
	
	public boolean isMostraVerde() {
		return (nome == Nome.VERDE);
	}
	
	public void setAzul() {
		nome = Nome.AZUL;
	}
	
	public boolean isMostraAzul() {
		return (nome == Nome.AZUL);
	}
	
	public void setPainelPesquisaFiscalizacao() {
		nome = Nome.PAINELPESQUISAFISCALIZACAO;
	}	
	
	public boolean isMostraPainelPesquisaFiscalizacao() {
		return (nome == Nome.PAINELPESQUISAFISCALIZACAO);
	}
	
	public String getIdToUpdate() {
		return (":frmVermelho " + 
				":frmVerde " +
				":frmAzul " +
				":frmPainelPesquisaFiscalizacao" );
	}
	
}
