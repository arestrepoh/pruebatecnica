/**
 * 
 */
package com.pruebatecnica.moviesieries.controller.request;

import java.io.Serializable;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * Clase con el campo y direccion para el ordenamiento de los datos.
 * @author Andres Restrepo
 *
 */
public class SortRequest implements Serializable {

	private static final long serialVersionUID = 1374475555986202895L;

	/** Campo del ordenamiento */
	private String fieldSort;
	
	/**Indica si la direccion es Descendente */
	private boolean desc;
	
	
	/**
	 * Constructor
	 * @param fieldSort
	 * @param desc
	 */
	public SortRequest(String fieldSort, boolean desc) {
		super();
		this.fieldSort = fieldSort;
		this.desc = desc;
	}
	
	/**
	 * @return the fieldSort
	 */
	public String getFieldSort() {
		return fieldSort;
	}
	/**
	 * @param fieldSort the fieldSort to set
	 */
	public void setFieldSort(String fieldSort) {
		this.fieldSort = fieldSort;
	}
	
	/**
	 * @return the desc
	 */
	public boolean isDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(boolean desc) {
		this.desc = desc;
	}
	
	/**
	 * Retorna la direccion del ordenamiento
	 * @return {@link Direction}
	 */
	public Direction getDirection() {		
		return this.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC; 
	}
}
