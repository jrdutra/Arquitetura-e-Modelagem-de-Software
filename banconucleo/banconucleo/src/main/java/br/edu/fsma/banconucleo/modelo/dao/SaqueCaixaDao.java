package br.edu.fsma.banconucleo.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.banconucleo.modelo.negocio.Conta;
import br.edu.fsma.banconucleo.modelo.negocio.SaqueCaixa;

public class SaqueCaixaDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<SaqueCaixa> dao;
	
	private EntityManager em;
	
	public SaqueCaixaDao(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<SaqueCaixa>(this.em, SaqueCaixa.class);
	}
	
	public void adiciona(SaqueCaixa saqueCaixa) {
		this.dao.adiciona(saqueCaixa);
	}

	public void atualiza(SaqueCaixa saqueCaixa){
		this.dao.atualiza(saqueCaixa);
	}

	public void remove(SaqueCaixa saqueCaixa) {
		this.dao.remove(saqueCaixa);
	}

	public SaqueCaixa buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<SaqueCaixa> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<SaqueCaixa>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<SaqueCaixa> listaTodos() {
		return (ArrayList<SaqueCaixa>) this.dao.listaTodos();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}

	public List<SaqueCaixa> listaTodosPorConta(Conta conta) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select s from SaqueCaixa s ");
		jpql.append(" where ");
		jpql.append(" s.conta like :pConta");
		TypedQuery<SaqueCaixa> query = em.createQuery(jpql.toString() , SaqueCaixa.class);
		query.setParameter("pConta", conta);
		try {
			return (ArrayList<SaqueCaixa>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public void excluiLista(List<SaqueCaixa> listaSaqueCaixa) {
		for(int i = 0; i < listaSaqueCaixa.size(); i++) {
			this.dao.remove(listaSaqueCaixa.get(i));
		}	
		
	}
	
}
