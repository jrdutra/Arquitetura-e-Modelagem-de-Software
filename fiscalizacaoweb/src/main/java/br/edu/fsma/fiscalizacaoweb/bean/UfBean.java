package br.edu.fsma.fiscalizacaoweb.bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.fsma.fiscalizacaoweb.modelo.negocio.Uf;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class UfBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private List<Uf> ufs = new ArrayList<Uf>();
	private Uf currentUf = new Uf();
	
}