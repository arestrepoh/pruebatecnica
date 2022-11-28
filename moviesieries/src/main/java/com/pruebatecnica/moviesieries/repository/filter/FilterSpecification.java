package com.pruebatecnica.moviesieries.repository.filter;

import java.util.ArrayList;
import java.util.List;

import com.pruebatecnica.moviesieries.entity.MovieSerie;

/**
 * Clase con las especificaciones para el filtrado de los {@link MovieSerie}
 * @author Andres Restrepo
 *
 */
public class FilterSpecification {
	
	/** Campo de la consulta */
	private String key;
	
	/** Operacion a realizar */
	private Operator operator;
	
	/** Valor de la consulta */
	private Object value;
	
	/** Listado de {@link FilterSpecification} */
	private static List<FilterSpecification> filter = new ArrayList<FilterSpecification>();
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the operator
	 */
	public Operator getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Crea filtro para campo String con operador LIKE 
	 * @param key Campo en el que se va a realizar el filtro
	 * @param value Valor a consultar.
	 * @return {@link FilterSpecification}
	 */
	public static FilterSpecification getFilterStringLike(String key, Object value) {
		FilterSpecification fr = new FilterSpecification();
		fr.setKey(key);
		fr.setOperator(Operator.LIKE);
		fr.setValue(value);
		return fr;
	}

	/**
	 * Crea filtro para campo String con operador EQUAL
	 * @param key Campo en el que se va a realizar el filtro
	 * @param value Valor a consultar.
	 * @return {@link FilterSpecification}	 
	 */
	public static FilterSpecification getFilterStringEqual(String key, Object value) {
		FilterSpecification fr = new FilterSpecification();
		fr.setKey(key);
		fr.setOperator(Operator.EQUAL_STRING);
		fr.setValue(value);
		return fr;
	}
	
	public static FilterSpecification getFilterNumberEqual(String key, Object value) {
		FilterSpecification fr = new FilterSpecification();
		fr.setKey(key);
		fr.setOperator(Operator.EQUAL);
		fr.setValue(value);
		return fr;
	}
	/**
	 * Retorna el listado de filtros a aplicar.
	 * @return the filter
	 */
	public static List<FilterSpecification> getFilter() {
		return filter;
	}

}
