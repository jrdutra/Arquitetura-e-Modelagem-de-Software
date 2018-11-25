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
	public void redirecionaPainel() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/bancogerente/view/painel/painel.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void esperaSegundos(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
