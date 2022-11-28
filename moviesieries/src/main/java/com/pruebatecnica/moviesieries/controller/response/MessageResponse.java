/**
 * 
 */
package com.pruebatecnica.moviesieries.controller.response;

import java.io.Serializable;

/**
 * Mensaje de respuesta de la aplicacion. 
 * @author Andres Restrepo
 *
 */
public class MessageResponse implements Serializable {

	private static final long serialVersionUID = 1148211991428810711L;

	/** Mensaje */
	private String message;

	/** Tipo de mensaje */
	private int tipo;
	
	/**
	 * @param message
	 */
	public MessageResponse(String message, int tipo) {
		super();
		this.message = message;
		this.tipo = tipo;
	}

	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
