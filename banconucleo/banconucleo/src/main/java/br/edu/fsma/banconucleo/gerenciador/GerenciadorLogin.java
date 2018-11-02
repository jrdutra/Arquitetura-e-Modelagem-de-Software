package br.edu.fsma.banconucleo.gerenciador;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.edu.fsma.banconucleo.conexao.JPAUtil;
import br.edu.fsma.banconucleo.modelo.dao.UsuarioGerenteDao;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioGerente;

public class GerenciadorLogin {

	private List<UsuarioGerente> listaUsuarioGerente = new ArrayList<UsuarioGerente>();
	
	private EntityManager em;
	
	private UsuarioGerenteDao usuarioGerenteDao;
	
	private UsuarioGerente usuarioGerente = new UsuarioGerente();
	
	public GerenciadorLogin() {
		this.listaUsuarioGerente = getListaTodosGerentes();
	}
	
	public List<UsuarioGerente> getListaTodosGerentes(){
		this.em = JPAUtil.getEntityManager();
		usuarioGerenteDao = new UsuarioGerenteDao(em);
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
}
