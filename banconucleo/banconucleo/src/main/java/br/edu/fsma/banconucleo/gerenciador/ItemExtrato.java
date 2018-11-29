package br.edu.fsma.banconucleo.gerenciador;

import java.time.LocalDate;


import br.edu.fsma.banconucleo.modelo.negocio.Conta;

public class ItemExtrato implements Comparable<ItemExtrato> {

	private Double valor;
	private LocalDate data;
	private String descricao;
	private Conta conta;
	
	public ItemExtrato(Double valor, LocalDate data, String descricao, Conta conta) {
		this.valor = valor;
		this.data = data;
		this.descricao = descricao;
		this.conta = conta;
	}
	
	@Override //-> para ordenar
    public int compareTo(ItemExtrato item) {
		 if (this.data.isAfter(item.getData())) {
	          return -1;
	     }
	     if (this.data.isBefore(item.getData())) {
	          return 1;
	     }
	     return 0;
    }
	
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "ItemExtrato \n [valor=" + valor + ", data=" + data + ", descricao=" + descricao + ", conta=" + conta + "]";
	}
	
	
}
