package com.fsma.projetoempresa.modelo;


import java.util.ArrayList;
import com.fsma.projetoempresa.dao.EmpresaDao;
import javax.persistence.EntityManager;
import com.fsma.projetoempresa.conexao.JPAUtil;
//import com.fsma.projetoempresa.dao.EmpresaDao;
//import javax.persistence.EntityManager;



public class Inicio {
	
	public static void main(String[] args) {
		ArrayList<Empresa> lista_de_empresas = new ArrayList<Empresa>();
		ArrayList<Empresa> lista_de_empresas_tratadas = new ArrayList<Empresa>();
		Leitor leitor = new Leitor("C:\\Users\\jrdut\\Desktop\\JAVA\\Arquitetura-e-Modelagem-de-Software\\projeto-empresas\\teste.csv", ";");
		Empresa empresa = new Empresa();	
		EntityManager em = JPAUtil.getEntityManager();
		EmpresaDao empresadao = new EmpresaDao(em);
		
		lista_de_empresas = leitor.getTodasEmpresas();
		lista_de_empresas_tratadas = leitor.trataTodasEmpresas(lista_de_empresas);
		
		
		//Grava uma empresa
		/*
		em.getTransaction().begin();
		em.persist(empresa);
		em.getTransaction().commit();
		*/
		System.out.println("Gravando no banco..");
		try {
			
			em.getTransaction().begin();
			//adiciona empresa por empresa
			for(int i = 0; i < lista_de_empresas_tratadas.size(); i++) {
				empresa = lista_de_empresas_tratadas.get(i);
				empresadao.adiciona(empresa);
			}
			em.getTransaction().commit();
		} 
		catch(Exception ex)  {
			empresadao.getEntityManager().getTransaction().rollback();
		}
		
		System.out.println("Fim da gravação");
		
		
		
	}
}
