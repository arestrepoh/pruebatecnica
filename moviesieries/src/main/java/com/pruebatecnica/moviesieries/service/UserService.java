/**
 * 
 */
package com.pruebatecnica.moviesieries.service;

import org.springframework.http.ResponseEntity;

import com.pruebatecnica.moviesieries.entity.User;

/**
 * @author Andres Restrepo
 *
 */
public interface UserService {

	/**
	 * Busca el usuario 
	 * @param userName Nombre del usuario
	 * @param pwd Contraseña
	 * @return {@link User}
	 */
	ResponseEntity<?> findUserByUsernamePassword(String userName, String pwd);
	
	/**
	 * Valida si la peticion realizada esta autorizada.
	 * @param tokenSession
	 * @return
	 */
	boolean authorize(String tokenSession);
}
