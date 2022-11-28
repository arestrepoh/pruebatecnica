/**
 * 
 */
package com.pruebatecnica.moviesieries.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.moviesieries.controller.response.MessageResponse;
import com.pruebatecnica.moviesieries.entity.User;
import com.pruebatecnica.moviesieries.service.serviceimpl.UserServiceImpl;

/**
 * Controlador de la autenticacion de usuarios.
 * @author Andres Restrepo
 *
 */
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

	/**
	 * Se inyecta la clase que se encarga de la logica de validacion del usuario
	 */
	@Autowired
	private UserServiceImpl userService;
	
	/**
	 * Realiza la validacion del inicio de sesion. 
	 * @param user
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> validateUserPwd(@Validated @RequestBody User user){
		if(user == null || StringUtils.isEmpty(user.getUsername()) ||StringUtils.isEmpty(user.getPassword()) ) {
			return new ResponseEntity<MessageResponse> ( new MessageResponse ("Acceso denegado!!! Debe ingresar el usuario y la contraseña.", 1), HttpStatus.OK);
		}
		return userService.findUserByUsernamePassword(user.getUsername(), user.getPassword());
	}
}
