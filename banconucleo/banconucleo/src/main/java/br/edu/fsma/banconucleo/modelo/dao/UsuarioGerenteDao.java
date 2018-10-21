package br.edu.fsma.banconucleo.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioGerente;

public class UsuarioGerenteDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<UsuarioGerente> dao;
	
	private EntityManager em;
	
	public UsuarioGerenteDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<UsuarioGerente>(this.em, UsuarioGerente.class);
	}

	public void adiciona(UsuarioGerente usuarioGerente) {
		this.dao.adiciona(usuarioGerente);
	}

	public void atualiza(UsuarioGerente usuarioGerente){
		this.dao.atualiza(usuarioGerente);
	}

	public void remove(UsuarioGerente usuarioGerente) {
		this.dao.remove(usuarioGerente);
	}

	public UsuarioGerente buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<UsuarioGerente> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<UsuarioGerente>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<UsuarioGerente> listaTodos() {
		return (ArrayList<UsuarioGerente>) this.dao.listaTodos();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
}
