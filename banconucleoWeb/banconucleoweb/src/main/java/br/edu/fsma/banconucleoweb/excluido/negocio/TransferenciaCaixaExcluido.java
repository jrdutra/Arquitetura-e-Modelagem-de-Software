package br.edu.fsma.banconucleoweb.excluido.negocio;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.fsma.banconucleoweb.modelo.negocio.TransferenciaCaixa;

@Entity
@Table(name = "tb_transferenciacaixa_excluido")
public class TransferenciaCaixaExcluido implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transferenciacaixa")
	private Long id;
	
	@Column(length=10)
	private Double valor;
	
	private LocalDate data;
	
	@ManyToOne
	@JoinColumn(name = "id_conta")
	private ContaExcluido contaExcluido;

	public TransferenciaCaixaExcluido(TransferenciaCaixa transferenciaCaixa, ContaExcluido contaExcluido2) {
		this.valor = transferenciaCaixa.getValor();
		this.data = transferenciaCaixa.getData();
		this.contaExcluido = contaExcluido2;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransferenciaCaixaExcluido other = (TransferenciaCaixaExcluido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SaqueCaixa [valor=" + valor + ", data=" + data + "]";
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

	public ContaExcluido getConta() {
		return contaExcluido;
	}

	public void setConta(ContaExcluido conta) {
		this.contaExcluido = conta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
