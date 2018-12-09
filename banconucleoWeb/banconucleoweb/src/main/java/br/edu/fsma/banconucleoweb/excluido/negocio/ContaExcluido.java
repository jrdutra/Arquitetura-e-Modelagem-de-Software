package br.edu.fsma.banconucleoweb.excluido.negocio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.fsma.banconucleoweb.modelo.negocio.Conta;
import br.edu.fsma.banconucleoweb.modelo.negocio.UsuarioGerente;

@Entity
@Table(name = "tb_conta_excluido")
public class ContaExcluido implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conta")
	private Long id;
	
	@Column(length=10)
	private String numero;
	
	@Column(length=8)
	private String agencia;
	
	@Column(length=10)
	private Double saldo;
	
	@ManyToOne
	@JoinColumn(name = "id_usuariogerente")
	private UsuarioGerente usuarioGerente;

	public ContaExcluido(Conta conta) {
		this.numero = conta.getNumero();
		this.agencia = conta.getAgencia();
		this.saldo = conta.getSaldo();
	}

	public ContaExcluido() {
		// TODO Auto-generated constructor stub
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
		ContaExcluido other = (ContaExcluido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

	@Override
	public String toString() {
		return "Conta [id=" + id + ", numero=" + numero + ", agencia=" + agencia + ", saldo=" + saldo
				+ ", usuarioGerente=" + usuarioGerente + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public UsuarioGerente getUsuarioGerente() {
		return usuarioGerente;
	}

	public void setUsuarioGerente(UsuarioGerente usuarioGerente) {
		this.usuarioGerente = usuarioGerente;
	}
}
