package br.edu.fsma.relogio.bean;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.edu.fsma.relogio.modelo.negocio.Relogio;

@ManagedBean(name = "IndexBean")
@ApplicationScoped
public class IndexBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private enum Fundo {BRANCO, AMARELO};
	private Fundo fundo = Fundo.AMARELO;
	
	private enum Modo {EXIBIR, AJUSTAR};
	private Modo modo = Modo.AJUSTAR;
	
	private enum Acertar {HORA, MINUTO};
	private Acertar acertar = Acertar.HORA;

	private Relogio relogio = new Relogio(0,0);
	
	private String status = "Modo: " + modo + " | Acertar: " + acertar;

	public void botaoAClick() {
		if(modo == Modo.EXIBIR) {
			modo = Modo.AJUSTAR;
			fundo = Fundo.AMARELO;
		}else if(modo == Modo.AJUSTAR){
			if(acertar == Acertar.HORA) {
				acertar = Acertar.MINUTO;
			}else if (acertar == Acertar.MINUTO) {
				acertar = Acertar.HORA;
				modo = Modo.EXIBIR;
				fundo = Fundo.BRANCO;
			}
		}
		atualizarStatus();
	}
	
	public void botaoBClick() {
		if(modo == Modo.AJUSTAR) {
			if(acertar == Acertar.HORA) {
				relogio.adicionaHora();
			}else if(acertar == Acertar.MINUTO) {
				relogio.adicionaMinuto();
			}
		}
	}
	
	private void atualizarStatus() {
		status = "Modo: " + modo;
		if (modo == Modo.AJUSTAR) {
			status = "Modo: " + modo + " | Acertar: " + acertar;
		}
	}
	
	public void setFundoBranco() {
		this.fundo = Fundo.BRANCO;
	}
	
	public void setFundoAmarelo() {
		this.fundo = Fundo.AMARELO;
	}

	public boolean isMostraBranco() {
		return (fundo == Fundo.BRANCO);
	}
	
	public boolean isMostraAmarelo() {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
