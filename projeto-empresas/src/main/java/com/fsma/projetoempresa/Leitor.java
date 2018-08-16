package com.fsma.projetoempresa;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.File;

public class Leitor {
	
	private String dirArquivoCSV;
    private BufferedReader br = null;
    private String linha;
    private String csvDivisor;
    
    public Leitor(String dir, String divisor) {
    	this.setArquivoCSV(dir);
		this.setCsvDivisor(divisor);
		this.setLinha("");
    }

	public void readCsvFile() {
		try {
			System.out.println("Lendo o arquivo " + this.getdirArquivoCSV());
	        br = new BufferedReader(new InputStreamReader(new FileInputStream(dirArquivoCSV), "UTF-8"));
	        while ((linha = br.readLine()) != null) {
	        	System.out.println("Linha ...");
	            String[] celula = linha.split(csvDivisor);
	            for(int i = 0; i<9; i++) {
		            System.out.println("[" + celula[i] + "]");	            	
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
	            	System.out.println("Arquivo Fechado.");
	                br.close(); //Fecha o arquivo
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	public static String currentApplicationDir() {
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
	
	
}
