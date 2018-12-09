package br.edu.fsma.banconucleoweb.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.fsma.banconucleoweb.gerenciador.GerenciadorLogin2;
import br.edu.fsma.banconucleoweb.modelo.dao.UsuarioGerenteCDIDao;
import br.edu.fsma.banconucleoweb.tx.Transacional;
import br.edu.fsma.banconucleoweb.teste.Funcionario;
import br.edu.fsma.banconucleoweb.teste.Gerente;
import br.edu.fsma.banconucleoweb.teste.Pessoa;

@Named
@ViewScoped
public class IndexBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	
	public void botaoClick() {
		
		
		ArrayList<Pessoa> listaPessoa = new ArrayList<>();
		
		Gerente gerente1 = new Gerente("João", "116.666.837-10", 3700.0);
		Gerente gerente2 = new Gerente("Maria", "116.666.837-10", 1700.0);
		
		Funcionario funcionario1 = new Funcionario("João", "116.666.837-10", 3700.0);
		Funcionario funcionario2 = new Funcionario("Maria", "116.666.837-10", 1700.0);
		
		listaPessoa.add(gerente1);
		listaPessoa.add(gerente2);
		listaPessoa.add(funcionario1);
		listaPessoa.add(funcionario2);
		
		System.out.println(listaPessoa.get(0).getClass().getSimpleName());
		System.out.println(listaPessoa.get(0).getNome());
		System.out.println(listaPessoa.get(1).getNome());
		System.out.println(listaPessoa.get(2).getNome());
		System.out.println(listaPessoa.get(3).getNome());
		
		
		
	}
	
}