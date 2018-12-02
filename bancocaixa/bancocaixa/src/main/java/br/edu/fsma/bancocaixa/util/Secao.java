package br.edu.fsma.bancocaixa.util;


public class Secao {
	
	private static UsuarioSecao usuarioSecao = new UsuarioSecao();

	public static UsuarioSecao getUsuarioSecao() {
		return usuarioSecao;
	}

	public static void setUsuarioSecao(UsuarioSecao usuarioSecao) {
		Secao.usuarioSecao = usuarioSecao;
	}

	
}
