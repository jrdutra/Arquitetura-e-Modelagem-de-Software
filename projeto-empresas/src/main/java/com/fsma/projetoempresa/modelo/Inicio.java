package com.fsma.projetoempresa.modelo;

import com.fsma.projetoempresa.conexao.JPAUtil;
import com.fsma.projetoempresa.dao.EmpresaDao;
import javax.persistence.EntityManager;
import java.util.ArrayList;

//import javax.persistence.EntityManager;
//import com.fsma.projetoempresa.conexao.JPAUtil;
//import com.fsma.projetoempresa.dao.EmpresaDao;



public class Inicio {
	
	public static void main(String[] args) {
		ArrayList<Empresa> lista_de_empresas = new ArrayList<Empresa>();
		ArrayList<Empresa> lista_de_empresas_tratadas = new ArrayList<Empresa>();
		Leitor leitor = new Leitor("C:\\Users\\jrdut\\Desktop\\JAVA\\Arquitetura-e-Modelagem-de-Software\\projeto-empresas\\teste.csv", ";");
		lista_de_empresas = leitor.getTodasEmpresas();
		lista_de_empresas_tratadas = leitor.trataTodasEmpresas(lista_de_empresas);
		Empresa empresa = new Empresa();	
		
		
		
		
		
		EntityManager em = JPAUtil.getEntityManager();
		EmpresaDao empresaDao = new EmpresaDao(em);
		
		
		
		//Grava uma empresa
		/*
		em.getTransaction().begin();
		em.persist(empresa);
		em.getTransaction().commit();
		*/
		
		try {
			
			em.getTransaction().begin();
			//adiciona empresa por empresa
			for(int i = 0; i < lista_de_empresas_tratadas.size(); i++) {
				empresa = lista_de_empresas_tratadas.get(i);
				em.persist(empresa);
			}
			em.getTransaction().commit();
		} 
		catch(Exception ex)  {
			em.getTransaction().rollback();
		}
		
		
		System.out.println(empresa);
	}
}
