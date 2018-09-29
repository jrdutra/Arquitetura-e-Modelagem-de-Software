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
	private Long idempresa;
	
	private String cnpj;
	private String razaosocial;
	
	/*
	@OneToMany(targetEntity=Fiscalizacao.class, mappedBy="tbempresa")
	@JoinColumn(name = "idfiscalizacao")*/
	
	@Embedded
	private ArrayList<Fiscalizacao> fiscalizacoes = new ArrayList<Fiscalizacao>();
	
	private String logradouro;
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
	
	

	public Long getIdempresa() {
		return idempresa;
	}

	public void setIdempresa(Long idempresa) {
		this.idempresa = idempresa;
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
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((fiscalizacoes == null) ? 0 : fiscalizacoes.hashCode());
		result = prime * result + ((idempresa == null) ? 0 : idempresa.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((razaosocial == null) ? 0 : razaosocial.hashCode());
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
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (fiscalizacoes == null) {
			if (other.fiscalizacoes != null)
				return false;
		} else if (!fiscalizacoes.equals(other.fiscalizacoes))
			return false;
		if (idempresa == null) {
			if (other.idempresa != null)
				return false;
		} else if (!idempresa.equals(other.idempresa))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (razaosocial == null) {
			if (other.razaosocial != null)
				return false;
		} else if (!razaosocial.equals(other.razaosocial))
			return false;
		return true;
	}

	
}
