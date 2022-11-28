/**
 * 
 */
package com.pruebatecnica.moviesieries.controller.request;

import java.io.Serializable;

import com.pruebatecnica.moviesieries.entity.MovieSerie;

/**
 * Entidad para las peticiones de actualizacion del {@link MovieSerie}
 * @author Andres Restrepo
 *
 */
public class MovieSerieRequest implements Serializable {

	private static final long serialVersionUID = 7238506258635437504L;
	
	/** Id */
	private Long id;
	
	/** Score a actualizar */
	private Integer score;
	
	/**Ya fue vista */
	private boolean view;

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
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
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
	
	

}
