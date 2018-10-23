package br.edu.fsma.banconucleo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.fsma.banconucleo.conexao.JPAUtil;
import br.edu.fsma.banconucleo.modelo.dao.ContaDao;
import br.edu.fsma.banconucleo.modelo.dao.PessoaFisicaDao;
import br.edu.fsma.banconucleo.modelo.dao.UsuarioGerenteDao;
import br.edu.fsma.banconucleo.modelo.negocio.Conta;
import br.edu.fsma.banconucleo.modelo.negocio.PessoaFisica;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioGerente;

public class App 
{
    public static void main( String[] args )
    {
    	List<UsuarioGerente> listaUsuarioGerente = new ArrayList<UsuarioGerente>();
    	
    	EntityManager em;
    	
    	UsuarioGerenteDao usuarioGerenteDao;
    	
    	UsuarioGerente usuarioGerente = new UsuarioGerente();
    	
    	Long idUsuarioGerente;
    	
    	em = JPAUtil.getEntityManager();
        
    	usuarioGerenteDao = new UsuarioGerenteDao(em);
		try {
			em.getTransaction().begin();
			listaUsuarioGerente = usuarioGerenteDao.listaTodos();
			em.getTransaction().commit();
			System.out.println(listaUsuarioGerente);
		}catch(Exception ex)  {
			System.out.println("\n\nErro Ler Lista de Gerentes:");
			System.out.println(ex);
			em.getTransaction().rollback();
		}
        
		
        
        
    }
}
