package br.edu.fsma.fiscalizacaoweb.modelo.dao;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Fiscalizacao;

public class FiscalizacaoDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	private DAO<Fiscalizacao> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Fiscalizacao>(this.em, Fiscalizacao.class);
	}

	@Inject
	private EntityManager em;
	
	public void adiciona(Fiscalizacao fiscalizacao) {
		this.dao.adiciona(fiscalizacao);
	}

	public void atualiza(Fiscalizacao fiscalizacao){
		this.dao.atualiza(fiscalizacao);
	}

	public void remove(Fiscalizacao fiscalizacao) {
		this.dao.remove(fiscalizacao);
	}

	public Fiscalizacao buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public List<Fiscalizacao> buscaPorPeriodo(LocalDate dataInferior, LocalDate dataSuperior) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT f from Fiscalizacao f ");
		jpql.append(" WHERE ");
		jpql.append(" f.dataterminofiscalizacao "); 
		jpql.append(" BETWEEN :pDataInferior AND :pDataSuperior");
		TypedQuery<Fiscalizacao> query = em.createQuery(jpql.toString() , Fiscalizacao.class);
		query.setParameter("pDataInferior", dataInferior);
		query.setParameter("pDataSuperior", dataSuperior);
		try {
			return (ArrayList<Fiscalizacao>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public List<Fiscalizacao> buscaPorPeriodoEFiscal(LocalDate dataInferior, LocalDate dataSuperior, Long idFiscal) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT f from Fiscalizacao f ");
		jpql.append(" WHERE ");
		jpql.append(" f.dataterminofiscalizacao "); 
		jpql.append(" BETWEEN :pDataInferior AND :pDataSuperior");
		jpql.append(" AND ");
		jpql.append("    f.pessoaFisica.id = :pIdFiscal");
		TypedQuery<Fiscalizacao> query = em.createQuery(jpql.toString() , Fiscalizacao.class);
		query.setParameter("pDataInferior", dataInferior);
		query.setParameter("pDataSuperior", dataSuperior);
		query.setParameter("pIdFiscal", idFiscal);
		try {
			return (ArrayList<Fiscalizacao>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

}
