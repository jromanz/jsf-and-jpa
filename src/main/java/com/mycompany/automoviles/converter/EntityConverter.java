package com.mycompany.automoviles.converter;

import com.mycompany.automoviles.util.JPAUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@FacesConverter("entityConverter")
public class EntityConverter implements Converter {

	private Logger logger = LoggerFactory.getLogger(EntityConverter.class);
	
	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
		if(object == null) return null;
		
		try {
			Class<?> classe = object.getClass();
			Long id = (Long) classe.getMethod("getId").invoke(object);
			
			return classe.getName() + "-" + id;
		} catch (Exception e) {
			logger.error("Error al convertir entidad en String", e);
			return null;
		}
		
	}
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
		if(string == null || string.isEmpty()) return null;
		
		try {
			String[] values = string.split("-");
			Object obj = JPAUtil.getEntityManager().find(Class.forName(values[0]), Long.valueOf(values[1]));
			System.out.println(obj);
			return obj;
		} catch (Exception e) {
			logger.error("Error al convertir String en entidad", e);
			return null;
		}
	}


}