package br.edu.fsma.bancogerente.util;

import java.io.IOException;

import javax.faces.context.FacesContext;

public class Redirecionador {

	public void redireciona(String url) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
