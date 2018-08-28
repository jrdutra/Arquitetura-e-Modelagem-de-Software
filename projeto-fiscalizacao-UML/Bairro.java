package br.edu.fsma.projetofiscalizacao.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbbairro")
public class Bairro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idbairro")
	private Long idbairro;
	
	@OneToOne
	@JoinColumn(name = "idmunicipio", referencedColumnName="idmunicipio")
	private Municipio municipio;
	
	private String nome;
	
	public Long getId() {
		return idbairro;
	}
	public void setId(Long id) {
		this.idbairro = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((idbairro == null) ? 0 : idbairro.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Bairro other = (Bairro) obj;
		
		if (idbairro == null) {
			if (other.idbairro != null)
				return false;
		} else if (!idbairro.equals(other.idbairro))
			return false;
		
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Bairro [id=" + idbairro + ", nome=" + nome + "]";
	}
	
	
}
