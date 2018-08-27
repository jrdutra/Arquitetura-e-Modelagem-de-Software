package br.edu.fsma.projetofiscalizacao.modelo;

public class Main {
	
	public static void main(String[] args) {
		
		//EXEMPLO DE FISCALIZACAO DO ARQUIVO:
		//2010;2010/06;96.534.094/0001-58;ACCENTURE DO BRASIL LTDA;RUA ALEXANDRE DUMAS,2051;04717-004;CHAC SANTO ANTONIO;São Paulo;São Paulo
		
		
		ImportadorDeArquivo imp = new ImportadorDeArquivo();
		/*boolean resultado = imp.importarArquivoDeEmpresasParaBanco("C:\\Users\\jrdut\\Desktop\\JAVA\\Arquitetura-e-Modelagem-de-Software\\projeto-fiscalizacao\\projetofiscalizacao\\teste.csv");
		if(resultado) {
			System.out.println("\n\nArquivo importado com sucesso!");
		}else {
			System.out.println("\n\nOcorreu algum erro ao importar o arquivo.");
		}*/
		
		boolean resultado = imp.validaCPF("123.237.516-03");
		if(resultado) {
			System.out.println("\n\nCPF Valido");
		}else {
			System.out.println("\n\nCPF invalido");
		}
		
	}
}
