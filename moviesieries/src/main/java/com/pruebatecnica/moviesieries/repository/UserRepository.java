/**
 * 
 */
package com.pruebatecnica.moviesieries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.pruebatecnica.moviesieries.entity.User;

/**
 * Interface que implementa el {@link JpaRepository} para {@link User}
 * @author Andres Restrepo
 *
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{

	/** 
	 * Busca el {@link User} basado en el token de seguridad
	 * @param tokenSession
	 * @return
	 */
	User findBySession(String tokenSession);
	
}
