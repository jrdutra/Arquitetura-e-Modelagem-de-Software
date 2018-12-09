package br.edu.fsma.banconucleoweb.gerenciador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import br.edu.fsma.banconucleoweb.conexao.JPAUtil;
import br.edu.fsma.banconucleoweb.modelo.dao.UsuarioGerenteCDIDao;
import br.edu.fsma.banconucleoweb.modelo.dao.UsuarioGerenteDao;
import br.edu.fsma.banconucleoweb.modelo.dao.UsuarioPessoaFisicaDao;
import br.edu.fsma.banconucleoweb.modelo.dao.UsuarioPessoaJuridicaDao;
import br.edu.fsma.banconucleoweb.modelo.negocio.UsuarioGerente;
import br.edu.fsma.banconucleoweb.modelo.negocio.UsuarioPessoaFisica;
import br.edu.fsma.banconucleoweb.modelo.negocio.UsuarioPessoaJuridica;

@ApplicationScoped
public class GerenciadorLogin2 implements Serializable {

	private List<UsuarioGerente> listaUsuarioGerente = new ArrayList<UsuarioGerente>();
	
	private EntityManager em;
	
	
	@Inject
	private UsuarioGerenteCDIDao usuarioGerenteDao;
	
	private UsuarioPessoaFisicaDao usuarioPessoaFisicaDao;
	
	private UsuarioPessoaJuridicaDao usuarioPessoaJuridicaDao;
	
	private UsuarioGerente usuarioGerente = new UsuarioGerente();
	
	public GerenciadorLogin2() {
		this.listaUsuarioGerente = getListaTodosGerentes();
	}
	
	public List<UsuarioGerente> getListaTodosGerentes(){
		//this.em = JPAUtil.getEntityManager();
		//usuarioGerenteDao = new UsuarioGerenteDao(em);
		try {
			return usuarioGerenteDao.listaTodos();
		}catch(Exception ex)  {
			System.out.println("\n\nErro Ler Lista de Gerentes:");
			System.out.println(ex);
		}
		return null;
	}
	
	public boolean existeUsuarioGerente(Long idUsuario, String s) {
		this.usuarioGerente = pegarDeListaPorId(idUsuario);
		if((this.usuarioGerente.getSenha()).toString().equals((s).toString())) {
			return true;
		}
		return false;
	}
	
	public UsuarioGerente pegarDeListaPorId(Long id) {
		UsuarioGerente user = null;
		for( UsuarioGerente us : this.listaUsuarioGerente )
		{
		      if(id == us.getId()) {
		    	  user = us;
		      }
		}
		return user;
	}

	public List<UsuarioPessoaFisica> getListaTodosUsuarioPessoaFisica() {
		this.em = JPAUtil.getEntityManager();
		usuarioPessoaFisicaDao = new UsuarioPessoaFisicaDao(em);
		try {
			return usuarioPessoaFisicaDao.listaTodos();
		}catch(Exception ex)  {
			System.out.println("\n\nErro Ler Lista de UPF:");
			System.out.println(ex);
		}
		return null;
	}

	public List<UsuarioPessoaJuridica> getListaTodosUsuarioPessoaJuridica() {
		this.em = JPAUtil.getEntityManager();
		usuarioPessoaJuridicaDao = new UsuarioPessoaJuridicaDao(em);
		try {
			return usuarioPessoaJuridicaDao.listaTodos();
		}catch(Exception ex)  {
			System.out.println("\n\nErro Ler Lista de UPF:");
			System.out.println(ex);
		}
		return null;
	}
}
