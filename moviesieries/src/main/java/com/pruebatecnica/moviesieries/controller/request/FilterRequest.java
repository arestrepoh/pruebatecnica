/**
 * 
 */
package com.pruebatecnica.moviesieries.controller.request;

import java.io.Serializable;

import com.pruebatecnica.moviesieries.entity.MovieSerie;

/**
 * Entidad para el filtrado de las {@link MovieSerie}.
 * @author Andres Restrepo
 *
 */
public class FilterRequest implements Serializable {

	private static final long serialVersionUID = 4794410694105632901L;
	
	/** Id del filtro */
	private String id;
	
	/** Campo por el que se va a filtrar */
	private String field;
	
	/** Valor del filtro */
	private String value;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}
	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
