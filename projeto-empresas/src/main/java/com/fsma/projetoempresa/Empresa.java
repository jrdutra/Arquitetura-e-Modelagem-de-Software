package com.fsma.projetoempresa;

import java.time.LocalDate;

public class Empresa {
	private LocalDate data_termino_fiscalizacao;
	private String cnpj;
	private String razao_social;
	private String logradouro;
	private String cep;
	private String bairro;
	private String municipio;
	private String uf;
	
	public LocalDate getData_termino_fiscalizacao() {
		return data_termino_fiscalizacao;
	}
	public void setData_termino_fiscalizacao(LocalDate data_termino_fiscalizacao) {
		this.data_termino_fiscalizacao = data_termino_fiscalizacao;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getRazao_social() {
		return razao_social;
	}
	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
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
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	@Override
	public String toString() {
		return "Empresa [data_termino_fiscalizacao=" + data_termino_fiscalizacao + ", cnpj=" + cnpj + ", razao_social="
				+ razao_social + ", logradouro=" + logradouro + ", cep=" + cep + ", bairro=" + bairro + ", municipio="
				+ municipio + ", uf=" + uf + "]";
	}
	
	
}
