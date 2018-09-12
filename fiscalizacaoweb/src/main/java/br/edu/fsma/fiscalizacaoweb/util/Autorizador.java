package br.edu.fsma.fiscalizacaoweb.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
//import javax.inject.Inject;
//import javax.servlet.http.HttpSession;


public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent evento) {
		System.out.println("afterPhase(PhaseEvent evento)");
		FacesContext context = evento.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();
		System.out.println("Nome da página: " + nomePagina);
		if (nomePagina.endsWith("/login/login.xhtml") ||
			nomePagina.endsWith("/login/recuperarSenha.xhtml") || 
			nomePagina.endsWith("/login/novoLogin.xhtml")) {
			return;
		}
		
		
		// Redirecionamento para login.xhtml
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "/view/index/index");
		context.renderResponse();
	}

	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		
	}

	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return null;
	} 

}
