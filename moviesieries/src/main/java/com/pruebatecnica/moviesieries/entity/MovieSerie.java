/**
 * 
 */
package com.pruebatecnica.moviesieries.entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entidad para manipulacion de los datos de las peliculas o series.
 * @author Andres Restrepo
 *
 */
@Entity
@Table(name="movieseries")
public class MovieSerie implements Serializable {

	private static final long serialVersionUID = -2690893783898315874L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 150)
	private String name;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "gender", nullable = false )
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Gender gender;
		
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "type", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Type type;
	
	@Column (columnDefinition = " INT(11)  DEFAULT 0" )
	private Integer numberviews;
	
	@Column (columnDefinition = " DECIMAL(3,2)  DEFAULT 0" )
	private Double score;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
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
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}
	/**
	 * @return the numberviews
	 */
	public Integer getNumberviews() {
		return numberviews;
	}
	/**
	 * @param numberviews the numberviews to set
	 */
	public void setNumberviews(Integer numberviews) {
		this.numberviews = numberviews;
	}
	/**
	 * @return the score
	 */
	public Double getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	
	
}
