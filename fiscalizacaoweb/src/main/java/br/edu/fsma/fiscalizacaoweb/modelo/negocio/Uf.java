package br.edu.fsma.fiscalizacaoweb.modelo.negocio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbuf")
public class Uf implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iduf")
	private Long iduf;
	private String nome;
	private String sigla;
	
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIduf() {
		return iduf;
	}

	public void setIduf(Long iduf) {
		this.iduf = iduf;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iduf == null) ? 0 : iduf.hashCode());
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
		Uf other = (Uf) obj;
		if (iduf == null) {
			if (other.iduf != null)
				return false;
		} else if (!iduf.equals(other.iduf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return sigla;
	}	
}
