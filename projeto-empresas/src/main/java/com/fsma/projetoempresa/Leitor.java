package com.fsma.projetoempresa;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import antlr.collections.List;

import java.io.File;

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
	
	public ArrayList<Empresa> getTodasEmpresas(){
		lst_empresa = new ArrayList<Empresa>();
		try {
			System.out.println("Lendo o arquivo " + this.getdirArquivoCSV());
	        br = new BufferedReader(new InputStreamReader(new FileInputStream(dirArquivoCSV), "ISO-8859-1"));
	        //=====================
	        //TODO CODIGO RODA AQUI
	        //=====================
	        int num_linha = 0;
	        while ((linha = br.readLine()) != null) {
	        	this.empresa = new Empresa();
	            String[] celula = linha.split(csvDivisor);
	        	if (num_linha!=0) { //ignora a primeira linha com os cabeçalhos
	        		//Setando Objeto Empresa
	        		try {
	        			celula[1] = celula[1].replace("/", "-") + "-01";//trata a data
		            	this.empresa.setData_termino_fiscalizacao(LocalDate.parse(celula[1]));
		            	//pega o ultimo dia válido do mês
		            	this.empresa.setData_termino_fiscalizacao(LocalDate.parse(celula[1]).withDayOfMonth(LocalDate.parse(celula[1]).lengthOfMonth()));
	        		}
	        		catch (Exception e) {
	        			System.out.println("Erro na leitura da data de termino de fiscalização");
	        		}
	            	this.empresa.setCnpj(celula[2]);
	            	this.empresa.setRazao_social(celula[3]);
	            	this.empresa.setLogradouro(celula[4]);;
	            	this.empresa.setCep(celula[5]);
	            	this.empresa.setBairro(celula[6]);
	            	this.empresa.setMunicipio(celula[7]);
	            	this.empresa.setUf(celula[8]);
	        	}
	        	this.lst_empresa.add(this.empresa);
	        	num_linha++;//passa para proxima linha
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
	            	System.out.println("Arquivo Fechado.");
	                br.close(); //Fecha o arquivo
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
		return this.lst_empresa;
	}
	
	public String currentApplicationDir() {
		String currentDir = "";
		try {
			currentDir = new File(".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return currentDir;
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
