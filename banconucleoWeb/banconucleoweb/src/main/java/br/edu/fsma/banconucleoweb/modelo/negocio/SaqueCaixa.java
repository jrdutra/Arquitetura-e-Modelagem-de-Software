package br.edu.fsma.banconucleoweb.modelo.negocio;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.fsma.banconucleoweb.conexao.JPAUtil;
import br.edu.fsma.banconucleoweb.modelo.dao.ContaDao;
import br.edu.fsma.banconucleoweb.modelo.dao.DepositoCaixaDao;
import br.edu.fsma.banconucleoweb.modelo.dao.SaqueCaixaDao;

@Entity
@Table(name = "tb_saquecaixa")
public class SaqueCaixa implements Serializable, Transacao{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_saquecaixa")
	private Long id;
	
	@Column(length=10)
	private Double valor;
	
	private LocalDate data;
	
	@ManyToOne
	@JoinColumn(name = "id_conta")
	private Conta conta;

	public Double processarTransacao(Double valor) {
		this.valor = valor;
		this.setData(LocalDate.now());
		
		EntityManager em;
		em = JPAUtil.getEntityManager();
		ContaDao contaDao = new ContaDao(em);
		SaqueCaixaDao saqueCaixaDao = new SaqueCaixaDao(em);
		
		this.conta = contaDao.buscaPorId(this.conta.getId());
		this.conta.setSaldo(this.conta.getSaldo()-valor);
		
		try {
			em.getTransaction().begin();
			contaDao.atualiza(conta);
			saqueCaixaDao.adiciona(this);
			em.getTransaction().commit();
		}catch(Exception ex)  {
			System.out.println("\n\nErro ao gravar alteracao de saldo conta no banco(Saque).");
			em.getTransaction().rollback();
			return null;
		}
		
		
		return this.valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaqueCaixa other = (SaqueCaixa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Saque Caixa [valor=" + valor + ", data=" + data + "]";
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
