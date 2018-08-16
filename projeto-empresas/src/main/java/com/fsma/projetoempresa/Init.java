package com.fsma.projetoempresa;

public class Init {
	public static void main(String[] args) {
		Leitor l = new Leitor("C:\\Users\\jrdut\\Desktop\\JAVA\\Arquitetura-e-Modelagem-de-Software\\projeto-empresas\\teste.csv", ";");
		System.out.println(l.currentApplicationDir());
		l.readCsvFile();
	}
}
