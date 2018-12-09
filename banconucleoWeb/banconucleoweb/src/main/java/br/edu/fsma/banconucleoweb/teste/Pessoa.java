package br.edu.fsma.banconucleoweb.teste;

public abstract class Pessoa {
	
	protected String nome;
    protected String cpf;
    protected double salario;
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	@Override
	public String toString() {
		return "\nPessoa [nome=" + nome + ", cpf=" + cpf + ", salario=" + salario + "]";
	}
    
    
    
}
