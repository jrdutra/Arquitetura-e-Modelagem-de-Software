package br.edu.fsma.projetofiscalizacao.dao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.projetofiscalizacao.modelo.Bairro;

public class BairroDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private DAO<Bairro> dao;
	//@Inject
	private EntityManager em;
	//no lugar do inject e postconstruct fiz o construtor instanciando o em e dao
	
	public BairroDAO(EntityManager em2) {
		this.em = em2;
		this.dao = new DAO<Bairro>(this.em, Bairro.class);
	}
	
	public boolean existe(Bairro bairro) {
		@SuppressWarnings("unused")
		Bairro resultado = new Bairro();
		TypedQuery<Bairro> query = em.createQuery(
				  " select b from tbbairro b "
				+ " where b.Nome = :pNome", Bairro.class);
		query.setParameter("pNome", bairro.getNome());
		try {
			resultado = query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}
	
	public void adiciona(Bairro bairro) {
		this.dao.adiciona(bairro);
	}

	public void atualiza(Bairro bairro){
		this.dao.atualiza(bairro);
	}

	public void remove(Bairro bairro) {
		this.dao.remove(bairro);
	}

	public Bairro buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public ArrayList<Bairro> listaTodosPaginada(int firstResult, int maxResults) {
		return (ArrayList<Bairro>) this.dao.listaTodosPaginada(firstResult, maxResults);
	}
	
}
