package br.edu.fsma.banconucleo;

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
    	
    	EntityManager em = JPAUtil.getEntityManager();
        
        ContaDao contaDao = new ContaDao(em);
        PessoaFisicaDao pessoaFisicaDao = new PessoaFisicaDao(em);
        UsuarioGerenteDao usuarioGerenteDao = new UsuarioGerenteDao(em);
        
        Conta conta = new Conta();
        PessoaFisica pessoaFisica = new PessoaFisica();
        UsuarioGerente usuarioGerente = new UsuarioGerente();
        
        pessoaFisica.setCpf("116666837-10");
        pessoaFisica.setNome("João Ricardo");
        
        usuarioGerente.setPessoaFisica(pessoaFisica);
        usuarioGerente.setSenha("123456");
        
        conta.setAgencia("6186");
        conta.setNumero("07022-0");
        conta.setSaldo(100.00);
        conta.setUsuarioGerente(usuarioGerente);
        
        System.out.println("Gravando no banco..");
		try {
			
			em.getTransaction().begin();

			pessoaFisicaDao.adiciona(pessoaFisica);
			usuarioGerenteDao.adiciona(usuarioGerente);
			contaDao.adiciona(conta);

			em.getTransaction().commit();
		} 
		catch(Exception ex)  {
			contaDao.getEntityManager().getTransaction().rollback();
			System.out.println("Deu Merda");
		}
		
		System.out.println("Fim da gravação");
        
        
        
    }
}
