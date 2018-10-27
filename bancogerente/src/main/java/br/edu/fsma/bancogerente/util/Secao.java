package br.edu.fsma.bancogerente.util;

import br.edu.fsma.banconucleo.modelo.negocio.UsuarioGerente;

public class Secao {
	
	private static UsuarioGerente usuarioGerente = new UsuarioGerente();

	public static UsuarioGerente getUsuarioGerente() {
		return usuarioGerente;
	}

	public static void setUsuarioGerente(UsuarioGerente usuarioGerente) {
		Secao.usuarioGerente = usuarioGerente;
	}
	
	
}
