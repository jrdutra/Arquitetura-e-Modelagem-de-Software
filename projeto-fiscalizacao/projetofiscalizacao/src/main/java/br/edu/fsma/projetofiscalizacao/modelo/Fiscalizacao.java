package br.edu.fsma.projetofiscalizacao.modelo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbfiscalizacao")
public class Fiscalizacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idfiscalizacao")
	private Long idfiscalizacao;
	
	@OneToOne
	@JoinColumn(name = "idempresa")
	private Empresa empresa;
	
	private LocalDate dataterminofiscalizacao;
	private String logradouro;
	private String cep;
	
	@ManyToOne
	@JoinColumn(name = "idbairro")
	private Bairro bairro;
	
	
	public Long getId() {
		return idfiscalizacao;
	}
	public void setId(Long id) {
		this.idfiscalizacao = id;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
		this.empresa.recebeFiscalizacao(this);
	}
	public LocalDate getDataterminofiscalizacao() {
		return dataterminofiscalizacao;
	}
	public void setDataterminofiscalizacao(LocalDate dataterminofiscalizacao) {
		this.dataterminofiscalizacao = dataterminofiscalizacao;
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
		return "Fiscalizacao [id=" + idfiscalizacao + ", empresa=" + empresa + ", dataterminofiscalizacao="
				+ dataterminofiscalizacao + ", bairro=" + bairro + "]";
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((dataterminofiscalizacao == null) ? 0 : dataterminofiscalizacao.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((idfiscalizacao == null) ? 0 : idfiscalizacao.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
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
		Fiscalizacao other = (Fiscalizacao) obj;
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
		if (dataterminofiscalizacao == null) {
			if (other.dataterminofiscalizacao != null)
				return false;
		} else if (!dataterminofiscalizacao.equals(other.dataterminofiscalizacao))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (idfiscalizacao == null) {
			if (other.idfiscalizacao != null)
				return false;
		} else if (!idfiscalizacao.equals(other.idfiscalizacao))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		return true;
	}
	
}
