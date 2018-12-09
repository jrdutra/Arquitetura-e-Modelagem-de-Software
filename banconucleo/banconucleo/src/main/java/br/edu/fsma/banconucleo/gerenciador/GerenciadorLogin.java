package br.edu.fsma.banconucleo.gerenciador;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.edu.fsma.banconucleo.conexao.JPAUtil;
import br.edu.fsma.banconucleo.modelo.dao.UsuarioGerenteDao;
import br.edu.fsma.banconucleo.modelo.dao.UsuarioPessoaFisicaDao;
import br.edu.fsma.banconucleo.modelo.dao.UsuarioPessoaJuridicaDao;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioGerente;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaFisica;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaJuridica;

public class GerenciadorLogin {

	private List<UsuarioGerente> listaUsuarioGerente = new ArrayList<UsuarioGerente>();
	
	private EntityManager em = JPAUtil.getEntityManager();
	
	private UsuarioGerenteDao usuarioGerenteDao;
	
	private UsuarioPessoaFisicaDao usuarioPessoaFisicaDao;
	
	private UsuarioPessoaJuridicaDao usuarioPessoaJuridicaDao;
	
	private UsuarioGerente usuarioGerente = new UsuarioGerente();
	
	public GerenciadorLogin() {
		this.listaUsuarioGerente = getListaTodosGerentes();
	}
	
	public List<UsuarioGerente> getListaTodosGerentes(){
		usuarioGerenteDao = new UsuarioGerenteDao(em);
		return usuarioGerenteDao.listaTodos();
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
		usuarioPessoaFisicaDao = new UsuarioPessoaFisicaDao(em);
		return usuarioPessoaFisicaDao.listaTodos();
	}

	public List<UsuarioPessoaJuridica> getListaTodosUsuarioPessoaJuridica() {
		usuarioPessoaJuridicaDao = new UsuarioPessoaJuridicaDao(em);
		return usuarioPessoaJuridicaDao.listaTodos();
	}
}
