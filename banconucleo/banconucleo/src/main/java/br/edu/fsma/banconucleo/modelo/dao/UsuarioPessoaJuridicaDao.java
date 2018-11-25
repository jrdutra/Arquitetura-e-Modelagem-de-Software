package br.edu.fsma.banconucleo.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.banconucleo.modelo.negocio.Conta;
import br.edu.fsma.banconucleo.modelo.negocio.PessoaJuridica;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaJuridica;

public class UsuarioPessoaJuridicaDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<UsuarioPessoaJuridica> dao;
	
	private EntityManager em;
	
	public UsuarioPessoaJuridicaDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<UsuarioPessoaJuridica>(this.em, UsuarioPessoaJuridica.class);
	}
	
	public void adiciona(UsuarioPessoaJuridica usuarioPessoaJuridica) {
		this.dao.adiciona(usuarioPessoaJuridica);
	}

	public void atualiza(UsuarioPessoaJuridica usuarioPessoaJuridica){
		this.dao.atualiza(usuarioPessoaJuridica);
	}

	public void remove(UsuarioPessoaJuridica usuarioPessoaJuridica) {
		this.dao.remove(usuarioPessoaJuridica);
	}
	
	public void removeLista(List<UsuarioPessoaJuridica> listaUsuarioPessoaJuridica) {
		for(int i = 0; i < listaUsuarioPessoaJuridica.size(); i++) {
			this.dao.remove(listaUsuarioPessoaJuridica.get(i));
		}
	}

	public UsuarioPessoaJuridica buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<UsuarioPessoaJuridica> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<UsuarioPessoaJuridica>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<UsuarioPessoaJuridica> listaTodos() {
		return (ArrayList<UsuarioPessoaJuridica>) this.dao.listaTodos();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}

	public List<UsuarioPessoaJuridica> listaTodosPorConta(Conta conta) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select p from UsuarioPessoaJuridica p ");
		jpql.append(" where ");
		jpql.append(" p.conta like :pConta");
		TypedQuery<UsuarioPessoaJuridica> query = em.createQuery(jpql.toString() , UsuarioPessoaJuridica.class);
		query.setParameter("pConta", conta);
		try {
			return (ArrayList<UsuarioPessoaJuridica>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
}
