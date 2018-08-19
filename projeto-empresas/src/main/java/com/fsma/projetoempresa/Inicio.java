package com.fsma.projetoempresa;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import com.fsma.projetoempresa.conexao.JPAUtil;
import com.fsma.projetoempresa.modelo.dao.EmpresaDao;



public class Inicio {
	
	public static void main(String[] args) {
		ArrayList<Empresa> lista_de_empresas = new ArrayList<Empresa>();
		ArrayList<Empresa> lista_de_empresas_tratadas = new ArrayList<Empresa>();
		Leitor leitor = new Leitor("C:\\Users\\jrdut\\Desktop\\JAVA\\Arquitetura-e-Modelagem-de-Software\\projeto-empresas\\teste.csv", ";");
		lista_de_empresas = leitor.getTodasEmpresas();
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		try {
			EmpresaDao empresaDao = new EmpresaDao(em);
			
			em.getTransaction().commit();
		} 
		catch(Exception ex)  {
			em.getTransaction().rollback();
		}
		
		
		lista_de_empresas_tratadas = leitor.trataTodasEmpresas(lista_de_empresas);
		
		
		
		System.out.println(lista_de_empresas_tratadas);
		
	}

}
