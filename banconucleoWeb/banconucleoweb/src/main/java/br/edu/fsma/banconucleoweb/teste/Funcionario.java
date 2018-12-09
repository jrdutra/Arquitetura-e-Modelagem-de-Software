package br.edu.fsma.banconucleoweb.teste;

public class Funcionario extends Pessoa {
	private String nome;
	private String cpf;
	private double salario;
    

	
	public Funcionario(String string, String string2, double d) {
		this.nome = string;
		this.cpf = string2;
		this.salario = d;
	}
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
		return "\nGerente [nome=" + nome + ", cpf=" + cpf + ", salario=" + salario + "]";
	}
}
