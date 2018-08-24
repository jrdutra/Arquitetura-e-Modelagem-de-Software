package br.edu.fsma.projetofiscalizacao.modelo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.InputMismatchException;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import br.edu.fsma.projetofiscalizacao.conexao.JPAUtil;
import br.edu.fsma.projetofiscalizacao.dao.BairroDAO;
import br.edu.fsma.projetofiscalizacao.dao.MunicipioDAO;
import br.edu.fsma.projetofiscalizacao.dao.UfDAO;
import br.edu.fsma.projetofiscalizacao.dao.FiscalizacaoDAO;
import br.edu.fsma.projetofiscalizacao.dao.EmpresaDAO;
import br.edu.fsma.projetofiscalizacao.modelo.Empresa;
import br.edu.fsma.projetofiscalizacao.modelo.Uf;

public class ImportadorDeArquivo {
	
	private String file_dir;
	private BufferedReader br = null;
    private String linha;
    private String csvDivisor = ";";
    
    
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
    
    //OBJETOS PARA PERSISTIR NO BANCO
    //private Uf objUf;
    //private Municipio objMunicipio;
    //private Bairro objBairro;
    //private Fiscalizacao objFiscalizacao;
    //private Empresa objEmpresa;
 
    //OBJETOS DE PERSISTENCIA
    //private UfDAO objUfDAO;
    //private MunicipioDAO objMunicipioDAO;
    //private BairroDAO objBairroDAO;
    //private FiscalizacaoDAO objFiscalizacaoDAO;
    //private EmpresaDAO objEmpresaDAO;
    //private EntityManager em;
	
	public ImportadorDeArquivo(String file_dir) {
		this.file_dir = file_dir;
		//this.em = JPAUtil.getEntityManager();
	}
	
	private String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
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
		//Declara objetos locais
		
		//Objetos do modelo
	    Uf objUf;
	    Municipio objMunicipio;
	    Bairro objBairro;
	    Fiscalizacao objFiscalizacao;
	    Empresa objEmpresa;
	 
	    //Objetos de Persistencia
		EntityManager em = JPAUtil.getEntityManager();
	    UfDAO objUfDAO = new UfDAO(em);
	    MunicipioDAO objMunicipioDAO = new MunicipioDAO(em);
	    BairroDAO objBairroDAO = new BairroDAO(em);
	    FiscalizacaoDAO objFiscalizacaoDAO = new FiscalizacaoDAO(em);
	    EmpresaDAO objEmpresaDAO = new EmpresaDAO(em);

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
		        			this.cnpj = (celula[2].toUpperCase());
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura do CNPJ na linha " + num_linha);
		        		}
		        		try { //Lê Razap SOcial
		        			this.razaoSocial = this.removerAcentos((celula[3].toUpperCase()));
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura da Razao Social na linha " + num_linha);
		        		}
		        		try { //Lê Logradouro
		        			this.logradouro = this.removerAcentos((celula[4].toUpperCase()));
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura do Logradouro na linha " + num_linha);
		        		}
		        		try { //Lê CEP
		        			this.cep = (celula[5].toUpperCase());
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura do CEP na linha " + num_linha);
		        		}
		        		try { //Lê Bairro
		        			this.bairro = this.removerAcentos((celula[6].toUpperCase()));
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura do Bairro na linha " + num_linha);
		        		}
		        		try { //Lê Municipio
		        			this.municipio = this.removerAcentos((celula[7].toUpperCase()));
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura do Municipio na linha " + num_linha);
		        		}
		        		try { //Lê UF
		        			this.uf = this.removerAcentos((celula[8].toUpperCase()));
		        		}
		        		catch (Exception e) {
		        			System.out.println("Erro na leitura da UF na linha " + num_linha);
		        		}
		        		//so faz a importação se o CNPJ for válido
		        		if(this.validaCnpj(this.cnpj)) {
		        			
		        			try {
		        				
		        				
		        				//==================================
			        			// INICIO DA LOGICA DE PERSISTENCIA
			        			//==================================
			        			em.getTransaction().begin();
			        			
			        			
			        			objUf = new Uf();
			        			objUf.setNome(this.uf);
			        			//Testa se UF ja existe, se existe pega se nao exite grava
			        			//objUfDAO.existe(objUf)
			        			
			        			if(objUfDAO.existe(objUf)){
			        				objUf = objUfDAO.buscaUfPorNome(objUf);
			        			}
			        			else
			        			{
			        				objUfDAO.adiciona(objUf);
			        			}
			        			
			        			/*
			        			objMunicipio = new Municipio();
			        			objMunicipioDAO = new MunicipioDAO(em);
			        			objMunicipio.setUf(objUf);
			        			objMunicipio.setNome(this.municipio);
			        			
			        			//Testa se Munnicipio ja existe, se existe pega se nao exite grava
			        			if(objMunicipioDAO.existe(objMunicipio)) {
			        				objMunicipio = objMunicipioDAO.buscaMunicipioPorNome(objMunicipio);
			        			}else {
			        				objMunicipioDAO.adiciona(objMunicipio);
			        			}
			        			
			        			
			        			
			        			this.objBairro = new Bairro();
			        			this.objBairroDAO = new BairroDAO(em);
			        			this.objBairro.setMunicipio(this.objMunicipio);
			        			this.objBairro.setNome(this.bairro);
			        			
			        			//Testa se Bairro ja existe, se existe pega se nao exite grava
			        			if(objBairroDAO.existe(objBairro)) {
			        				this.objBairro = this.objBairroDAO.buscaBairroPorNome(this.objBairro);
			        			}else {
			        				this.objBairroDAO.adiciona(objBairro);
			        			}
			        			
			        			
			        			this.objEmpresa= new Empresa();
			        			this.objEmpresaDAO = new EmpresaDAO(em);
			        			
			        			//Testa se Bairro ja existe, se existe pega se nao exite grava
			        			if(objEmpresaDAO.existe(objEmpresa)) {
			        				this.objEmpresa = this.objEmpresaDAO.buscaEmpresaPeloCNPJ(this.objEmpresa);
			        			}else {
			        				this.objEmpresaDAO.adiciona(objEmpresa);
			        			}
			        			
			        			
			        			this.objFiscalizacao = new Fiscalizacao();
			        			this.objFiscalizacaoDAO = new FiscalizacaoDAO(em);
			        			this.objFiscalizacao.setDataterminofiscalizacao(this.dataLida);
			        			this.objFiscalizacao.setCep(this.cep);
			        			this.objFiscalizacao.setLogradouro(this.logradouro);
			        			this.objFiscalizacao.setBairro(this.objBairro);
			        			this.objFiscalizacao.setEmpresa(this.objEmpresa);
			        			//Persiste a fiscalizacao
			        			this.objFiscalizacaoDAO.adiciona(this.objFiscalizacao);
			        			
			        			
			        			//atualiza endereço empresa
			        			this.objEmpresa.recebeFiscalizacao(this.objFiscalizacao);
			        			//atualiza empresa no banco
			        			this.objEmpresaDAO.atualiza(this.objEmpresa);
			        			//persiste a fiscalizacao com a empresa 
			        			// Já que o cnpj da empresa é válido, cria a fiscalização correspondente a linha*/
			        			
			        			em.getTransaction().commit();
		        				
			        			
			        			
		        			}catch(Exception ex)  {
		        				em.getTransaction().rollback();
		        			}
		        			
		        		}
		        	}
		        	num_linha++;//passa para proxima linha
	        	} 	
	        }
	        return true;
	    } 
		catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado");
	        //e.printStackTrace();
			return false;
	    } 
		catch (IOException e) {
			System.out.println("Erro inesperado: ");
	        e.printStackTrace();
	        return false;
	    } 
		finally {
	        if (br != null) {
	            try {
	            	
	                br.close(); //Fecha o arquivo
	                return true;
	            } catch (IOException e) {
	                e.printStackTrace();
	                return false;
	            }
	        }
	    }
	}
	

}
