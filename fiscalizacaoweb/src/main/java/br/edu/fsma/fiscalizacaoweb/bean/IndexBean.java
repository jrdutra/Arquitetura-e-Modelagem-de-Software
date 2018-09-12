package br.edu.fsma.fiscalizacaoweb.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
//import javax.enterprise.context.ApplicationScoped;
//import javax.faces.view.ViewScoped;
//import javax.enterprise.context.RequestScoped;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class IndexBean implements Serializable {
	
	

	private static final long serialVersionUID = 1L;

	

	@PostConstruct
	public void init() {
		System.out.println("IndexBean.init();");
	
	}
	
}
