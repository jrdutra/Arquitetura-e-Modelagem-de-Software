package br.edu.fsma.projetofiscalizacao.modelo;

import javax.persistence.EntityManager;

import br.edu.fsma.projetofiscalizacao.conexao.JPAUtil;
import br.edu.fsma.projetofiscalizacao.dao.EmpresaDAO;
import br.edu.fsma.projetofiscalizacao.dao.FiscalizacaoDAO;
import br.edu.fsma.projetofiscalizacao.dao.BairroDAO;
import br.edu.fsma.projetofiscalizacao.dao.MunicipioDAO;
import br.edu.fsma.projetofiscalizacao.dao.UfDAO;

public class Main {
	
	public static void main(String[] args) {
		
		//EXEMPLO DE FISCALIZACAO DO ARQUIVO:
		//2010;2010/06;96.534.094/0001-58;ACCENTURE DO BRASIL LTDA;RUA ALEXANDRE DUMAS,2051;04717-004;CHAC SANTO ANTONIO;São Paulo;São Paulo
		
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
		
	}
}
