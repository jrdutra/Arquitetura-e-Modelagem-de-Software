package com.fsma.projetoempresa;

public class Inicio {

	public static void main(String[] args) {
		
		Leitor l = new Leitor("C:\\Users\\jrdut\\Desktop\\JAVA\\Arquitetura-e-Modelagem-de-Software\\projeto-empresas\\teste.csv", ";");
		l.getTodasEmpresas();

	}

}