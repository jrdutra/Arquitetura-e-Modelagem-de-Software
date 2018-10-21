package br.edu.fsma.banconucleo.modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import br.edu.fsma.banconucleo.modelo.negocio.PessoaFisica;

public class PessoaFisicaDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	private DAO<PessoaFisica> dao;
	
		@PostConstruct
		void init() {
			this.dao = new DAO<PessoaFisica>(this.em, PessoaFisica.class);
		}
	
		@Inject
		private EntityManager em;
		
		public boolean existe(PessoaFisica pessoa) {
			@SuppressWarnings("unused")
			PessoaFisica resultado = new PessoaFisica();
			TypedQuery<PessoaFisica> query = em.createQuery("SELECT p FROM PessoaFisica p WHERE p.cpf=:pCpf", PessoaFisica.class);
			query.setParameter("pCpf", pessoa.getCpf());

			try {
				resultado = query.getSingleResult();
				return true;
			} catch (NoResultException ex) {
				return false;
			}
		}
		
		public PessoaFisica buscaPessoaPeloCpf(PessoaFisica pessoa) {
			StringBuilder jpql = new StringBuilder();
			jpql.append(" select p from PessoaFisica p ");
			jpql.append(" where ");
			jpql.append("       p.cpf = :pCpf ");
			
			TypedQuery<PessoaFisica> query = em.createQuery(jpql.toString() , PessoaFisica.class);
			
			query.setParameter("pCpf", pessoa.getCpf());
			try {
				return query.getSingleResult();
			} catch (NoResultException ex) {
				return null;
			}
		}
		
		public PessoaFisica buscaPessoaPeloCpf(String cpf) {
			String jpql = "select p from PessoaFisica p where p.cpf like :pCpf";
			TypedQuery<PessoaFisica> query = em.createQuery(jpql, PessoaFisica.class);
			query.setParameter("pCpf", cpf);
			try {
				return query.getSingleResult();
			} catch (NoResultException ex) {
				return null;
			}
		}
		
		public List<PessoaFisica> buscaListaPessoaFisicaPorCpf(PessoaFisica pessoaFisica) {
			StringBuilder jpql = new StringBuilder();
			jpql.append(" select p from PessoaFisica p ");
			jpql.append(" where ");
			jpql.append(" p.cpf like :pCpf");
			TypedQuery<PessoaFisica> query = em.createQuery(jpql.toString() , PessoaFisica.class);
			query.setParameter("pCpf", pessoaFisica.getCpf());
			try {
				return (ArrayList<PessoaFisica>) query.getResultList();
			} catch (NoResultException ex) {
				return null;
			}
		}
		
		public List<PessoaFisica> listaTodos() {
			return (ArrayList<PessoaFisica>) this.dao.listaTodos();
		}
		
		public void adiciona(PessoaFisica pessoa) {
			this.dao.adiciona(pessoa);
		}

		public void atualiza(PessoaFisica pessoa){
			this.dao.atualiza(pessoa);
		}

		public void remove(PessoaFisica pessoa) {
			this.dao.remove(pessoa);
		}

		public PessoaFisica buscaPorId(Long id) {
			return this.dao.buscaPorId(id);
		}

		public ArrayList<PessoaFisica> listaTodosPaginada(int firstResult, int maxResults) {
			return (ArrayList<PessoaFisica>) this.dao.listaTodosPaginada(firstResult, maxResults);
		}
}
