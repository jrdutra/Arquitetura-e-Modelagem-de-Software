package br.edu.fsma.banconucleoweb.excluido.negocio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.edu.fsma.banconucleoweb.modelo.negocio.PessoaJuridica;
import br.edu.fsma.banconucleoweb.modelo.negocio.UsuarioPessoaJuridica;

@Entity
@Table(name = "tb_usuariopessoajuridica_excluido")
public class UsuarioPessoaJuridicaExcluido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuariopessoajuridica")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "id")
	private PessoaJuridica pessoaJuridica;
	
	@OneToOne
	@JoinColumn(name = "id_conta")
	private ContaExcluido contaExcluido;
	
	@Column(length=10)
	private String senha;

	public UsuarioPessoaJuridicaExcluido(UsuarioPessoaJuridica u, ContaExcluido contaExcluido2) {
		this.pessoaJuridica = u.getPessoaJuridica();
		this.contaExcluido = contaExcluido2;
		this.senha = u.getSenha();
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public ContaExcluido getConta() {
		return contaExcluido;
	}

	public void setConta(ContaExcluido conta) {
		this.contaExcluido = conta;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
		UsuarioPessoaJuridicaExcluido other = (UsuarioPessoaJuridicaExcluido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioPessoaJuridica [pessoaJuridica=" + pessoaJuridica + "]";
	}
	

	
	
}
