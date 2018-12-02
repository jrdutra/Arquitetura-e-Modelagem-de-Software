package br.edu.fsma.bancocaixa.util;

import java.util.ArrayList;
import java.util.List;

import br.edu.fsma.banconucleo.modelo.negocio.Conta;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaFisica;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaJuridica;

public class UsuarioSecao {
	private Conta conta;
	private String senha;
	private String nome;
	private int id;
	
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<UsuarioSecao> getListaUsuarioSecao(List<UsuarioPessoaFisica> listaUsuarioPessoaFisica,
			List<UsuarioPessoaJuridica> listaUsuarioPessoaJuridica) {
		
		UsuarioSecao usuarioSecao;
		List<UsuarioSecao> listaUsuarioSecao = new ArrayList<UsuarioSecao>();
		int i;
		int id = 0;
		for (i=0; i<listaUsuarioPessoaFisica.size(); i++) {
			usuarioSecao = new UsuarioSecao();
			usuarioSecao.setConta(listaUsuarioPessoaFisica.get(i).getConta());
			usuarioSecao.setSenha(listaUsuarioPessoaFisica.get(i).getSenha());
			usuarioSecao.setNome(listaUsuarioPessoaFisica.get(i).getPessoaFisica().getNome());
			usuarioSecao.setId(id);
			id++;
			listaUsuarioSecao.add(usuarioSecao);
		}
		
		for (i=0; i<listaUsuarioPessoaJuridica.size(); i++) {
			usuarioSecao = new UsuarioSecao();
			usuarioSecao.setConta(listaUsuarioPessoaJuridica.get(i).getConta());
			usuarioSecao.setSenha(listaUsuarioPessoaJuridica.get(i).getSenha());
			usuarioSecao.setNome(listaUsuarioPessoaJuridica.get(i).getPessoaJuridica().getRazaosocial());
			usuarioSecao.setId(id);
			id++;
			listaUsuarioSecao.add(usuarioSecao);
		}
		
		return listaUsuarioSecao;
	}
	@Override
	public String toString() {
		return "UsuarioSecao [conta=" + conta + ", senha=" + senha + ", nome=" + nome + ", id=" + id + "]";
	}

	
	
}
