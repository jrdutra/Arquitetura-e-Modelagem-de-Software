package br.edu.fsma.projetofiscalizacao.modelo;

import javax.persistence.EntityManager;

import br.edu.fsma.projetofiscalizacao.conexao.JPAUtil;
import br.edu.fsma.projetofiscalizacao.dao.EmpresaDAO;
import br.edu.fsma.projetofiscalizacao.dao.FiscalizacaoDAO;
import br.edu.fsma.projetofiscalizacao.dao.BairroDAO;
import br.edu.fsma.projetofiscalizacao.dao.MunicipioDAO;
import br.edu.fsma.projetofiscalizacao.dao.UfDAO;
import java.time.LocalDate;

public class Main {
	
	public static void main(String[] args) {
		
		//EXEMPLO DE FISCALIZACAO DO ARQUIVO:
		//2010;2010/06;96.534.094/0001-58;ACCENTURE DO BRASIL LTDA;RUA ALEXANDRE DUMAS,2051;04717-004;CHAC SANTO ANTONIO;São Paulo;São Paulo
		
		LocalDate dataterminofiscalizacao;
		String logradouro;
		String cep;
		String cnpj;
		String razaosocial;
		Empresa empresa = new Empresa();
		Fiscalizacao fiscalizacao = new Fiscalizacao();
		Bairro bairro = new Bairro();
		Municipio municipio = new Municipio();
		Uf uf = new Uf();

		
		EntityManager em = JPAUtil.getEntityManager();
		EmpresaDAO empresadao = new EmpresaDAO(em);
		FiscalizacaoDAO fiscalizacaodao = new FiscalizacaoDAO(em);
		BairroDAO bairrodao = new BairroDAO(em);
		MunicipioDAO municipiodao = new MunicipioDAO(em);
		UfDAO ufdao = new UfDAO(em);
		
		//Setando os atributos dos objetos:
		logradouro = "RUA ALEXANDRE DUMAS,2051";
		cep = "04717-004";
		dataterminofiscalizacao = LocalDate.parse("2010-06-30");
		cnpj = "96.534.094/0001-58";
		razaosocial = "ACCENTURE DO BRASIL LTDA";
		
		uf.setNome("São Paulo");
		municipio.setNome("São Paulo");
		municipio.setUf(uf);
		bairro.setNome("CHAC SANTO ANTÔNIO");
		bairro.setMunicipio(municipio);
		
		
		empresa.setBairro(bairro);
		empresa.setCep(cep);
		empresa.setLogradouro(logradouro);
		empresa.setCnpj(cnpj);
		empresa.setRazaosocial(razaosocial);
		
		fiscalizacao.setLogradouro(logradouro);
		fiscalizacao.setCep(cep);
		fiscalizacao.setDataterminofiscalizacao(dataterminofiscalizacao);
		fiscalizacao.setBairro(bairro);
		fiscalizacao.setEmpresa(empresa);
		
		empresa.recebeFiscalizacao(fiscalizacao);
		
		try {
			
			em.getTransaction().begin();
			
			ufdao.adiciona(uf);
			municipiodao.adiciona(municipio);
			bairrodao.adiciona(bairro);
			empresadao.adiciona(empresa);
			
			fiscalizacaodao.adiciona(fiscalizacao);
			
			em.getTransaction().commit();
		} 
		catch(Exception ex)  {
			em.getTransaction().rollback();
		}
		
		
	}
}
