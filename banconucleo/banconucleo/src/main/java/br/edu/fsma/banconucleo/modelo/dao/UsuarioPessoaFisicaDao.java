package br.edu.fsma.banconucleo.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.banconucleo.modelo.negocio.Conta;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaFisica;
import br.edu.fsma.banconucleo.modelo.negocio.UsuarioPessoaJuridica;

public class UsuarioPessoaFisicaDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<UsuarioPessoaFisica> dao;
	
	private EntityManager em;
	
	public UsuarioPessoaFisicaDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<UsuarioPessoaFisica>(this.em, UsuarioPessoaFisica.class);
	}
	
	public void adiciona(UsuarioPessoaFisica usuarioPessoaFisica) {
		this.dao.adiciona(usuarioPessoaFisica);
	}

	public void atualiza(UsuarioPessoaFisica usuarioPessoaFisica){
		this.dao.atualiza(usuarioPessoaFisica);
	}

	public void remove(UsuarioPessoaFisica usuarioPessoaFisica) {
		this.dao.remove(usuarioPessoaFisica);
	}
	
	public void removeLista(List<UsuarioPessoaFisica> listaUsuarioPessoaFisica) {
		for(int i = 0; i < listaUsuarioPessoaFisica.size(); i++) {
			this.dao.remove(listaUsuarioPessoaFisica.get(i));
		}
	}

	public UsuarioPessoaFisica buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<UsuarioPessoaFisica> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<UsuarioPessoaFisica>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<UsuarioPessoaFisica> listaTodos() {
		return (ArrayList<UsuarioPessoaFisica>) this.dao.listaTodos();
	}
	public EntityManager getEntityManager() {
		return this.em;
	}
	
	public List<UsuarioPessoaFisica> listaTodosPorConta(Conta conta) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select p from UsuarioPessoaFisica p ");
		jpql.append(" where ");
		jpql.append(" p.conta like :pConta");
		TypedQuery<UsuarioPessoaFisica> query = em.createQuery(jpql.toString() , UsuarioPessoaFisica.class);
		query.setParameter("pConta", conta);
		try {
			return (ArrayList<UsuarioPessoaFisica>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
}
