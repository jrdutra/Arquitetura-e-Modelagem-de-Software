package br.edu.fsma.fiscalizacaoweb.bean;

import java.util.Collection;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.lang.reflect.*;

@FacesConverter("objectConverter")
public class ObjectConverter implements Converter {
	@Override // Metodo que é chamado na inicialização do converter que pega os objetos da Lista e pega os ids deles através de reflexão e retorna esses ids em forma de String*/
	public String getAsString(FacesContext context, UIComponent component,Object value) {
		// Primeira linha em branco da combo caso exista, 
		// Aqui simulamos que o id desse campo em branco seja "-1"
		if (value == "") {
			return "-1";
		}
		//  Quando precisa renderizar uma combo com valor ainda não adquirido (null). 
		//  que é renderizada antes mesmo que um objeto seja selecionado.
		if(getIdByReflection(value) == null){
			return "-1";
		}
		// Retorna o id como Long, adquirido atraves de reflexão
		return getIdByReflection(value).toString();
	}	
	@Override // Quando eu seleciono um arquivo na combo por exemplo, ele pega o objeto e seta o valor do componente.
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.equals("")) {
			return null; 
		}
		try {
			Long id = Long.valueOf(value);
			Collection items = (Collection) component.getAttributes().get("items");			
			return findById(items, id);
		} catch (Exception ex) {
			throw new ConverterException("Não foi possível aplicar conversão de item com valor ["+ value + "] no componente [" + component.getId()+ "]", ex);
		}
	}
	/** Retorna o objeto pelo id  */
	private Object findById(Collection collection, Long idToFind) {
		Object object = null;
		for (Object obj : collection) {
			Long id = getIdByReflection(obj);
			if (id.equals(idToFind)) {
				object = obj;
				break;
			}
		}
		return object;
	}
	private Long getIdByReflection(Object bean) {
		try {
			// Pega o Id do objeto
			Field idField = bean.getClass().getDeclaredField("iduf");
			idField.setAccessible(true);
			return (Long) idField.get(bean);
		} catch (Exception ex) {
			throw new ConverterException("Não foi possível obter a propriedade 'id' do item", ex);
		}
	}
}