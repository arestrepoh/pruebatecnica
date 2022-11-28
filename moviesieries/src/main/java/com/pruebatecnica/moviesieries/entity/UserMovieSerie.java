/**
 * 
 */
package com.pruebatecnica.moviesieries.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entidad para manipular las {@link MovieSerie} asociadas a un {@link User}
 * @author Andres Restrepo
 */
@Entity
@Table(name="usermovieseries",  uniqueConstraints = { @UniqueConstraint( name="uqUserMovieSerie", columnNames  =  {"user" ,"movieserie"} ) } )
public class UserMovieSerie implements Serializable {
	
	private static final long serialVersionUID = 1091083458332412826L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user", nullable = false )
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "movieserie", nullable = false )
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private MovieSerie movieSerie;
	
	@Column (columnDefinition = " BOOLEAN  DEFAULT 0" )
	private boolean view;
	
	@Column (columnDefinition = " DOUBLE  DEFAULT 0" )
	private double score;

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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the movieSerie
	 */
	public MovieSerie getMovieSerie() {
		return movieSerie;
	}

	/**
	 * @param movieSerie the movieSerie to set
	 */
	public void setMovieSerie(MovieSerie movieSerie) {
		this.movieSerie = movieSerie;
	}

	/**
	 * @return the view
	 */
	public boolean isView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(boolean view) {
		this.view = view;
	}

	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}
}
