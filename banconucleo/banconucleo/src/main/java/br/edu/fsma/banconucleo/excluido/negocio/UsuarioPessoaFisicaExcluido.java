package br.edu.fsma.banconucleo.excluido.negocio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.edu.fsma.banconucleo.modelo.negocio.PessoaFisica;

@Entity
@Table(name = "tb_usuariopessoafisica_excluido")
public class UsuarioPessoaFisicaExcluido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuariopessoafisica")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "id_pessoafisica")
	private PessoaFisica pessoaFisica;
	
	@OneToOne
	@JoinColumn(name = "id_conta_excluido")
	private ContaExcluido contaExcluido;
	
	@Column(length=10)
	private String senha;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public ContaExcluido getConta() {
		return contaExcluido;
	}

	public void setConta(ContaExcluido conta) {
		this.contaExcluido = conta;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioPessoaFisicaExcluido other = (UsuarioPessoaFisicaExcluido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioPessoaFisica [id=" + id + ", pessoaFisica=" + pessoaFisica + ", conta=" + contaExcluido + ", senha="
				+ senha + "]";
	}
	
	
}
