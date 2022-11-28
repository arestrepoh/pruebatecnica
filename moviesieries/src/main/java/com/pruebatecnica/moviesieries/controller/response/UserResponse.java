/**
 * 
 */
package com.pruebatecnica.moviesieries.controller.response;

import java.io.Serializable;

/**
 * Entidad para el manejo de los usuarios de la aplicacion
 * @author Andres Restrepo
 *
 */
public class UserResponse implements Serializable {

	private static final long serialVersionUID = 5319286734732088822L;

	/** Id */
	private Long id;
	
	
	/** Usuario */
	private String username;
		
	/** Nombres */
	private String firstName;
	
	/** Apellidos */
	private String lastName;

	private String session;
	
	
	/**
	 * Constructor
	 * @param id
	 * @param username
	 * @param firstName
	 * @param lastName
	 * @param session
	 */
	public UserResponse(Long id, String username, String firstName, String lastName, String session) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.session = session;
	}

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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the session
	 */
	public String getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(String session) {
		this.session = session;
	}

	
}
