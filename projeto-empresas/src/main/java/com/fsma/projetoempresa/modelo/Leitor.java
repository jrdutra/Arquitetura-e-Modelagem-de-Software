package com.fsma.projetoempresa.modelo;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException; //utlizado na validacao do CNPJ

public class Leitor {
	
	private String dirArquivoCSV;
    private BufferedReader br = null;
    private String linha;
    private String csvDivisor;
    private ArrayList<Empresa> lst_empresa;
    private Empresa empresa;
    
    public Leitor(String dir, String divisor) {
    	this.setArquivoCSV(dir);
		this.setCsvDivisor(divisor);
		this.setLinha("");
    }
    
   // ==============================================
   //                 VALIDA CNPJ
   // ==============================================
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
   
    // ==============================================
    //          LÊ TODAS EMPRESAS DO ARQUIVO
    // ==============================================
	public ArrayList<Empresa> getTodasEmpresas(){
		this.lst_empresa = new ArrayList<Empresa>();
		try {
			System.out.println("Lendo o arquivo " + this.getdirArquivoCSV());
	        br = new BufferedReader(new InputStreamReader(new FileInputStream(dirArquivoCSV), "ISO-8859-1"));
	        //================================
	        //TODO CODIGO DE LEITURA RODA AQUI
	        //================================
	        int num_linha = 0;
	        while ((linha = br.readLine()) != null) {
	        	if(!linha.contains(";;;;;")) {// Trata se é o rodapé do arquivo.
	        		this.empresa = new Empresa();
		            String[] celula = linha.split(csvDivisor);
		        	if (num_linha!=0) { //ignora a primeira linha com os cabeçalhos
		        		//Setando Objeto Empresa
		        		try { //Lê e trata a data
		        			celula[1] = celula[1].replace("/", "-") + "-01";//trata a data
			            	this.empresa.setData_termino_fiscalizacao(LocalDate.parse(celula[1]));
			            	//pega o ultimo dia válido do mês
			            	this.empresa.setData_termino_fiscalizacao(LocalDate.parse(celula[1]).withDayOfMonth(LocalDate.parse(celula[1]).lengthOfMonth()));
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura da data de termino de fiscalização na linha " + num_linha);
		        		}
		        		try { //Lê CNPJ
		        			this.empresa.setCnpj(celula[2]);
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura do CNPJ na linha " + num_linha);
		        		}
		        		try { //Lê Razap SOcial
		        			this.empresa.setRazao_social(celula[3]);
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura da Razao Social na linha " + num_linha);
		        		}
		        		try { //Lê Logradouro
		        			this.empresa.setLogradouro(celula[4]);
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura do Logradouro na linha " + num_linha);
		        		}
		        		try { //Lê CEP
		        			this.empresa.setCep(celula[5]);
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura do CEP na linha " + num_linha);
		        		}
		        		try { //Lê Bairro
		        			this.empresa.setBairro(celula[6]);
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura do Bairro na linha " + num_linha);
		        		}
		        		try { //Lê Municipio
		        			this.empresa.setMunicipio(celula[7]);
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura do Municipio na linha " + num_linha);
		        		}
		        		try { //Lê UF
		        			this.empresa.setUf(celula[8]);
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura da UF na linha " + num_linha);
		        		}
		        		//so faz o add se o cnpj for valido
		        		if(this.validaCnpj(this.empresa.getCnpj())) {
		        			this.lst_empresa.add(this.empresa); // faz o append da empresa so se o cnpj for valido
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
		return this.lst_empresa;
	}
	
	// ==============================================
    // TRATA O ARRAYLIST DE EMPRESAS PARA O BANCO
    // ==============================================
	public ArrayList<Empresa> trataTodasEmpresas(ArrayList<Empresa> lista_bruta_de_empresas){
		this.lst_empresa = new ArrayList<Empresa>();
		ArrayList<Empresa> lst_empresa_aux = new ArrayList<Empresa>();
		//-----------------------------------------------------
		// CORRE TODOS OS ELEMENTOS DA LISTA BRUTA
		// 
		//-----------------------------------------------------
		//clona lista bruta para a this.lst_lista
		System.out.println("Criando nova lista de empresas...\n");
		for (Empresa item: lista_bruta_de_empresas) {
			lst_empresa_aux.add(item);
		}
		//corre a lista auxiliar atualizando os elementos para o mais recente
		System.out.println("Unificando Elementos Repetidos...\n");
		for(int i = 0 ; i < this.lst_empresa.size(); i++) {	
			//corre a lista até a posiçaõ i atual para ver se tem elementos repetidos
			for(int j = 0 ; j < i; j++) {			
				if(this.lst_empresa.get(j).getCnpj().equals(this.lst_empresa.get(i).getCnpj())) {
					this.lst_empresa.get(j).setRazao_social(this.lst_empresa.get(i).getRazao_social());
					this.lst_empresa.get(j).setBairro(this.lst_empresa.get(i).getBairro());
					this.lst_empresa.get(j).setData_termino_fiscalizacao(this.lst_empresa.get(i).getData_termino_fiscalizacao());
					this.lst_empresa.get(j).setLogradouro(this.lst_empresa.get(i).getLogradouro());
					this.lst_empresa.get(j).setCep(this.lst_empresa.get(i).getCep());
					this.lst_empresa.get(j).setMunicipio(this.lst_empresa.get(i).getMunicipio());
					this.lst_empresa.get(j).setUf(this.lst_empresa.get(i).getUf());
				}
			}
		}
		System.out.println("Removendo Elementos Repetidos...\n");
		//int n = 0;
		int k_value = 0;
		int tam = lst_empresa_aux.size() - 1;
		for(int i = 0 ; i < tam; i++) {	
			
			for(int j = i + 1; j < tam; )
	        {
	            if( lst_empresa_aux.get(j).getCnpj().equals(lst_empresa_aux.get(i).getCnpj()))
	            {
	                for(int k = j; k < tam; k++ ) {
	                	lst_empresa_aux.set(k, lst_empresa_aux.get(k+1));
	                	k_value = k;
	                }
	                tam--;
	            }
	            else
	            {
	                j++;
	            }
			
	        }
		}
		
		System.out.println("Finalizando o ArrayList de Empresas...\n");
		// pega as empresas em ordem
		
		for (int i = 0; i < (k_value + 1); i++) {
			//lst_empresa_aux.get(i).setId((long) k_value);
			this.lst_empresa.add(lst_empresa_aux.get(i));
		}
		//this.lst_empresa.remove(j);
		return this.lst_empresa;
	}
	
	public String getdirArquivoCSV() {
		return dirArquivoCSV;
	}

	public void setArquivoCSV(String arquivoCSV) {
		this.dirArquivoCSV = arquivoCSV;
	}

	public String getLinha() {
		return linha;
	}

	public void setLinha(String linha) {
		this.linha = linha;
	}

	public String getCsvDivisor() {
		return csvDivisor;
	}

	public void setCsvDivisor(String csvDivisor) {
		this.csvDivisor = csvDivisor;
	}

	public ArrayList<Empresa> getLst_empresa() {
		return lst_empresa;
	}

	public void setLst_empresa(ArrayList<Empresa> lst_empresa) {
		this.lst_empresa = lst_empresa;
	}
	
	
}
