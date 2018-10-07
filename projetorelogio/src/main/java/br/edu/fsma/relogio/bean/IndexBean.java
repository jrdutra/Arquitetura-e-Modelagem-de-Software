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
	
	private enum Modo {EXIBIR, AJUSTAR};
	private Modo modo = Modo.AJUSTAR;
	
	private enum Acertar {HORA, MINUTO};
	private Acertar acertar = Acertar.HORA;

	private Relogio relogio = new Relogio(0,0);

	public void botaoAClick() {
		if(modo == Modo.EXIBIR) {
			modo = Modo.AJUSTAR;
			fundo = Fundo.AMARELO;
		}else if(modo == Modo.AJUSTAR) {
			modo = Modo.EXIBIR;
			fundo = Fundo.BRANCO;
		}
		System.out.println(modo);
	}
	
	public void botaoBClick() {
		System.out.println("Boto B CLicado");
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

}
