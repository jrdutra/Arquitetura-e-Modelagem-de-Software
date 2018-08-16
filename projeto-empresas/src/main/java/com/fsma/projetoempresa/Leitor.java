package com.fsma.projetoempresa;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;

public class Leitor {
	
	private String arquivoCSV = "arquivo.csv";
    private BufferedReader br = null;
    private String linha = "";
    private String csvDivisor = ",";

	public void readCsvFile(String dir, String divisor) {
		
	}
	
	public static void currentDir() {
		try {
			System.out.println("/  -> " + new File("/").getCanonicalPath());
			System.out.println(".. -> " + new File("..").getCanonicalPath());
			System.out.println(".  -> " + new File(".").getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getArquivoCSV() {
		return arquivoCSV;
	}

	public void setArquivoCSV(String arquivoCSV) {
		this.arquivoCSV = arquivoCSV;
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
