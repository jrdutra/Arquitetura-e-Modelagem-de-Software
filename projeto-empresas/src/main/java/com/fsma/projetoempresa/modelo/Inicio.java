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
		EntityManager em = JPAUtil.getEntityManager();
		
		lista_de_empresas = leitor.getTodasEmpresas();
		lista_de_empresas_tratadas = leitor.trataTodasEmpresas(lista_de_empresas);
		em.getTransaction().begin();
		
		try {
			EmpresaDao empresaDao = new EmpresaDao(em);
			//adiciona empresa por empresa
			for(int i = 0; i < lista_de_empresas_tratadas.size(); i++) {
				empresaDao.adiciona(lista_de_empresas_tratadas.get(i));
			}
			em.getTransaction().commit();
		} 
		catch(Exception ex)  {
			em.getTransaction().rollback();
		}
		
		System.out.println(lista_de_empresas_tratadas);
	}
}
