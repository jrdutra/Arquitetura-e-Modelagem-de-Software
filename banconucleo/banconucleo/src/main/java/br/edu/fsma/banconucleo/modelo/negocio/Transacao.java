package br.edu.fsma.banconucleo.modelo.negocio;

import java.time.LocalDate;


public abstract class Transacao implements Comparable<Transacao> {

	private Double valor;
	private LocalDate data;
	private Conta conta;
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	@Override //-> para ordenar
    public int compareTo(Transacao item) {
		 if (this.data.isAfter(item.getData())) {
	          return -1;
	     }
	     if (this.data.isBefore(item.getData())) {
	          return 1;
	     }
	     return 0;
    }
	
	@Override
	public String toString() {
		return "\nTransacao [valor=" + valor + ", data=" + data + ", conta=" + conta + "]";
	}
	
	
	
}
