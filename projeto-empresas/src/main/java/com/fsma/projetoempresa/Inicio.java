package com.fsma.projetoempresa;

import java.util.ArrayList;



public class Inicio {
	
	public static void main(String[] args) {
		ArrayList<Empresa> lista_de_empresas = new ArrayList<Empresa>();
		Leitor leitor = new Leitor("C:\\Users\\jrdut\\Desktop\\JAVA\\Arquitetura-e-Modelagem-de-Software\\projeto-empresas\\teste.csv", ";");
		lista_de_empresas = leitor.getTodasEmpresas();
		
	}

}
