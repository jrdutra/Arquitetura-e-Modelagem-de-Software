package br.edu.fsma.relogio.modelo.negocio;

public class Relogio {
	
	private Integer hora;
	private Integer minuto;
	
	public Integer getHora() {
		return hora;
	}
	public void setHora(Integer hora) {
		this.hora = hora;
	}
	public Integer getMinuto() {
		return minuto;
	}
	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}
	
	public void adicionaHora() {
		this.hora += 1;
		if(this.hora > 23) {
			this.hora = 0;
		}
	}
	
	public void adicionaMinuto() {
		this.minuto += 1;
		if(this.minuto > 59) {
			this.minuto = 0;
		}
	}
	
	private String configuraTextoDeExibicao() {
		String h;
		String m;
		
		if(this.hora < 10) {
			h = "0" + this.hora.toString();
		}else {
			h = this.hora.toString();
		}
		
		if(this.minuto < 10) {
			m = "0" + this.minuto.toString();
		}else {
			m = this.minuto.toString();
		}
		
		return h + " : " + m;
	}
	
	@Override
	public String toString() {
		return configuraTextoDeExibicao();
	}
}
