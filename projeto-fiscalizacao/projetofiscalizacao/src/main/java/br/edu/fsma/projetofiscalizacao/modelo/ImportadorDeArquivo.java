package br.edu.fsma.projetofiscalizacao.modelo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.time.LocalDate;

import br.edu.fsma.projetofiscalizacao.modelo.Empresa;

public class ImportadorDeArquivo {
	
	private String file_dir;
	private BufferedReader br = null;
    private String linha;
    private String csvDivisor;
    
    //LINHA:
  	//2009;2009/06;05.789.313/0001-94;01 ENXUTO SUPERMERCADOS LTDA;RUA OTTO HERBEST,719;13150-000;VILA JOSE KALIL AUN;Cosmópolis;São Paulo
    // ATRIBUTOS REFERENTES A LINHA
    private LocalDate dataLida;
    private String cnpj;
    private String razaoSocial;
    private String logradouro;
    private String cep;
    private String bairro;
    private String municipio;
    private String uf;
    
	
	public ImportadorDeArquivo(String file_dir) {
		this.file_dir = file_dir;
	}
	
	private LocalDate trataData(String data) {
		LocalDate dataTratada;
		data = data.replace("/", "-") + "-01";//trata a data
		//transforma string em localdate
		dataTratada = (LocalDate.parse(data).withDayOfMonth(LocalDate.parse(data).lengthOfMonth()));
		return dataTratada;
	}
	
	public boolean validaCnpj(String CNPJ) {
	    //Trada a string de entrada, removendo caracteres especiais
		CNPJ = CNPJ.replace(".", "");
		CNPJ = CNPJ.replace(".", "");
		CNPJ = CNPJ.replace("/", "");
		CNPJ = CNPJ.replace("-", "");
	    // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
		if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
			CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
			CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
			CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
			CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
			   (CNPJ.length() != 14)) {
			   return(false);
		}
		char dig13, dig14;
		int sm, i, r, num, peso;
 
		// "try" - protege o código para eventuais erros de conversao de tipo (int)
	    try {
		      // Calculo do 1o. Digito Verificador
		      sm = 0;
		      peso = 2;
		      for (i=11; i>=0; i--) {
				//da um split na string e converte cada caracter para inteiro para posteriores calculos
		        num = (int)(CNPJ.charAt(i) - 48);
		        sm = sm + (num * peso);
		        peso = peso + 1;
		        if (peso == 10)
		           peso = 2;
		      }
		      r = sm % 11;
		      if ((r == 0) || (r == 1)) {
		    	  dig13 = '0';  
		      } 
		      else {
		    	  dig13 = (char)((11-r) + 48);
		      }
		      // Calculo do 2o. Digito Verificador
		      sm = 0;
		      peso = 2;
		      for (i=12; i>=0; i--) {
		        num = (int)(CNPJ.charAt(i)- 48);
		        sm = sm + (num * peso);
		        peso = peso + 1;
		        if (peso == 10)
		           peso = 2;
		      }
		      r = sm % 11;
		      if ((r == 0) || (r == 1)) {
		    	  dig14 = '0'; 
		      }
		      else{
		    	  dig14 = (char)((11-r) + 48);
		      }
		      // Verifica se os dígitos calculados conferem com os dígitos informados.
		      if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
		    	  return(true);  
		      }
		      else {
		    	  return(false);
		      }
	    } catch (InputMismatchException erro) {
	          return(false);
	    }
    }
	
	public Boolean importarArquivoParaBanco() {
		try {
	        br = new BufferedReader(new InputStreamReader(new FileInputStream(file_dir), "ISO-8859-1"));
	        //================================
	        //TODO CODIGO DE LEITURA RODA AQUI
	        //================================
	        int num_linha = 0;
	        while ((linha = br.readLine()) != null) {
	        	if(!linha.contains(";;;;;")) {// Trata se é o rodapé do arquivo.
		            String[] celula = linha.split(csvDivisor);
		        	if (num_linha!=0) { //ignora a primeira linha com os cabeçalhos
		        		//Setando Objeto Empresa
		        		try { //Lê e trata a data
		        			this.dataLida = this.trataData(celula[1]);
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura da data de termino de fiscalização na linha " + num_linha);
		        		}
		        		try { //Lê CNPJ
		        			this.cnpj = (celula[2]);
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura do CNPJ na linha " + num_linha);
		        		}
		        		try { //Lê Razap SOcial
		        			this.razaoSocial = (celula[3]);
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura da Razao Social na linha " + num_linha);
		        		}
		        		try { //Lê Logradouro
		        			this.logradouro = (celula[4]);
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura do Logradouro na linha " + num_linha);
		        		}
		        		try { //Lê CEP
		        			this.cep = (celula[5]);
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura do CEP na linha " + num_linha);
		        		}
		        		try { //Lê Bairro
		        			this.bairro = (celula[6]);
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura do Bairro na linha " + num_linha);
		        		}
		        		try { //Lê Municipio
		        			this.municipio = (celula[7]);
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura do Municipio na linha " + num_linha);
		        		}
		        		try { //Lê UF
		        			this.uf = (celula[8]);
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura da UF na linha " + num_linha);
		        		}
		        		//so faz a importação se o CNPJ for válido
		        		if(this.validaCnpj(this.cnpj)) {
		        			
		        		}
		        	}
		        	num_linha++;//passa para proxima linha
	        	} 	
	        }
	    } 
		catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado");
	        //e.printStackTrace();
	    } 
		catch (IOException e) {
			System.out.println("Erro inesperado: ");
	        e.printStackTrace();
	    } 
		finally {
	        if (br != null) {
	            try {
	            	System.out.println("Arquivo lido com sucesso...\nProcessando Informações...\n-------------------------------");
	                br.close(); //Fecha o arquivo
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

}
