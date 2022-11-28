/**
 * 
 */
package com.pruebatecnica.moviesieries.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad para manipulacion en BD de los Generos
 * @author Andres Restrepo
 *
 */
@Entity
@Table(name="gender")
public class Gender implements Serializable {
	
	private static final long serialVersionUID = -7429269198860678529L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50, unique = true, nullable = false)
	private String name;
	
	@Column(length = 450)
	private String description;
	
	/**
	 * @return the code
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param code the code to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
