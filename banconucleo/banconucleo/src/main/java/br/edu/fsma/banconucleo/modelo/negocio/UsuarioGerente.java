package br.edu.fsma.banconucleo.modelo.negocio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tb_usuariogerente")
public class UsuarioGerente implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "id")
	private PessoaFisica pessoaFisica;
	
	@Column(length=10)
	private String senha;
	
	
}
