package br.edu.fsma.projetofiscalizacao.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tbempresa")
public class Empresa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private String cnpj;
	private String razaosocial;
	private ArrayList<Fiscalizacao> fiscalizacoes = new ArrayList<Fiscalizacao>();
	
	private String logradouro;
	private String cep;
	private Bairro bairro;
	
	public ArrayList<Fiscalizacao> getFiscalizacoes() {
		return (ArrayList<Fiscalizacao>) Collections.unmodifiableList(fiscalizacoes);
	}
	
	public void setFiscalizacoes(ArrayList<Fiscalizacao> fiscalizacoes) {
		this.fiscalizacoes = fiscalizacoes;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Empresa other = (Empresa) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
