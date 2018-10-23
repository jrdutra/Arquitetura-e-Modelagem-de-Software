package br.edu.fsma.bancogerente.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;

import br.edu.fsma.banconucleo.modelo.dao.UsuarioGerenteDao;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioGerente;
import br.edu.fsma.banconucleo.conexao.JPAUtil;


@ManagedBean(name = "IndexBean")
@ViewScoped
public class IndexBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<UsuarioGerente> listaUsuarioGerente = new ArrayList<UsuarioGerente>();
	
	private EntityManager em;
	
	private UsuarioGerenteDao usuarioGerenteDao;
	
	private UsuarioGerente usuarioGerente = new UsuarioGerente();
	
	private Long idUsuarioGerente;
	
	public IndexBean() {
		this.em = JPAUtil.getEntityManager();
		usuarioGerenteDao = new UsuarioGerenteDao(em);
		try {
			listaUsuarioGerente = usuarioGerenteDao.listaTodos();
			System.out.println("Lista de Usuario Gerente:");
			System.out.println(listaUsuarioGerente);
		}catch(Exception ex)  {
			System.out.println("\n\nErro Ler Lista de Gerentes:");
			System.out.println(ex);
		}
	}

	public List<UsuarioGerente> getListaUsuarioGerente() {
		return listaUsuarioGerente;
	}

	public void setListaUsuarioGerente(List<UsuarioGerente> listaUsuarioGerente) {
		this.listaUsuarioGerente = listaUsuarioGerente;
	}

	public UsuarioGerenteDao getUsuarioGerenteDao() {
		return usuarioGerenteDao;
	}

	public void setUsuarioGerenteDao(UsuarioGerenteDao usuarioGerenteDao) {
		this.usuarioGerenteDao = usuarioGerenteDao;
	}

	public UsuarioGerente getUsuarioGerente() {
		return usuarioGerente;
	}

	public void setUsuarioGerente(UsuarioGerente usuarioGerente) {
		this.usuarioGerente = usuarioGerente;
	}

	public Long getIdUsuarioGerente() {
		return idUsuarioGerente;
	}

	public void setIdUsuarioGerente(Long idUsuarioGerente) {
		this.idUsuarioGerente = idUsuarioGerente;
	}
	
	
	
}
