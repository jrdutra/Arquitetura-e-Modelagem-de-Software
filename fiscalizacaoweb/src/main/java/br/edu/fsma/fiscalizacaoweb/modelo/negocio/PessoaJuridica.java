package br.edu.fsma.fiscalizacaoweb.modelo.negocio;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tbpessoajuridica")
public class PessoaJuridica implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idempresa")
	private Long id;
	
	@Column(length=20)
	private String cnpj;
	
	@Column(length=80)
	private String razaosocial;
	
	@Embedded
	private ArrayList<Fiscalizacao> fiscalizacoes = new ArrayList<Fiscalizacao>();
	
	@Column(length=150)
	private String logradouro;
	
	@Column(length=11)
	private String cep;
	
	@ManyToOne
	@JoinColumn(name = "idbairro", referencedColumnName="idbairro")
	private Bairro bairro;
	

	public ArrayList<Fiscalizacao> getFiscalizacoes() {
		return (ArrayList<Fiscalizacao>) Collections.unmodifiableList(fiscalizacoes);
	}
	
	public void recebeFiscalizacao(Fiscalizacao fiscalizacao) {
		this.fiscalizacoes.add(fiscalizacao);
		this.setBairro(fiscalizacao.getBairro());
		this.setCep(fiscalizacao.getCep());
		this.setLogradouro(fiscalizacao.getLogradouro());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaosocial() {
		return razaosocial;
	}

	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	@Override
	public String toString() {
		return "\n" + cnpj + " " + razaosocial;
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
		PessoaJuridica other = (PessoaJuridica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
