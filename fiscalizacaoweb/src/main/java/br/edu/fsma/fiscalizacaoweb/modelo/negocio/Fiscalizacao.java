package br.edu.fsma.fiscalizacaoweb.modelo.negocio;

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
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "idempresa")
	private PessoaJuridica empresa;
	
	private LocalDate dataterminofiscalizacao;
	
	@Column(length=150)
	private String logradouro;
	
	@Column(length=11)
	private String cep;
	
	@ManyToOne
	@JoinColumn(name = "idbairro")
	private Bairro bairro;
	
	@ManyToOne
	@JoinColumn(name = "tbpessoafisica")
	private PessoaFisica pessoaFisica;
	
	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}
	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PessoaJuridica getEmpresa() {
		return empresa;
	}
	public void setEmpresa(PessoaJuridica empresa) {
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
		return "Fiscalizacao [id=" + id + ", empresa=" + empresa + ", dataterminofiscalizacao="
				+ dataterminofiscalizacao + ", bairro=" + bairro + "]";
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
		Fiscalizacao other = (Fiscalizacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
