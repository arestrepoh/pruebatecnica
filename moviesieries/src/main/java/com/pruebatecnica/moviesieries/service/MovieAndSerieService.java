/**
 * 
 */
package com.pruebatecnica.moviesieries.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pruebatecnica.moviesieries.controller.request.FilterRequest;
import com.pruebatecnica.moviesieries.controller.request.MovieSerieRequest;
import com.pruebatecnica.moviesieries.controller.request.SortRequest;
import com.pruebatecnica.moviesieries.controller.response.MessageResponse;
import com.pruebatecnica.moviesieries.entity.Gender;
import com.pruebatecnica.moviesieries.entity.MovieSerie;
import com.pruebatecnica.moviesieries.entity.Type;
import com.pruebatecnica.moviesieries.entity.User;

/**
 * @author Andres Restrepo
 *
 */
public interface MovieAndSerieService  {

	/**
	 * Obtiene un objeto {@link Iterable} con todos los {@link MovieSerie}
	 * @return Iterable<MovieSerie>
	 */
	Iterable<MovieSerie> findAll();
	
	/**
	 * Obtiene un objeto {@link Iterable} de {@link MovieSerie} ordenado.
	 * @param orderBy {@link SortRequest} 
	 * @return Iterable<MovieSerie>
	 */
	Iterable<MovieSerie> findAll(SortRequest orderBy);
	
	/**
	 * Obtiene un objeto {@link Iterable} de {@link MovieSerie} de manera aleatoria.<br>
	 * Retorna solo un registro.
	 * @return Iterable<MovieSerie>
	 */
	Iterable<MovieSerie> findAllRandom();
	
	/**
	 * Obtiene un objeto {@link Iterable} de todos los {@link Gender}.
	 * @return Iterable<Gender>
	 */
	Iterable<Gender> getAllGender();

	/**
	 * Obtiene un objeto {@link Iterable} de todos los {@link Type}.
	 * @return Iterable<Type>
	 */
	Iterable<Type> getAllType();

	/**
	 * Obtiene un objeto {@link Iterable} de {@link MovieSerie} con base en una lista de {@link FilterRequest}.
	 * @param filter List {@link} 
	 * @return Iterable<MovieSerie>
	 */
	Iterable<MovieSerie> findAll(List<FilterRequest> filter);
	
	/**
	 * Metodo que se encarga de actualizar el Score del {@link MovieSerie}
	 * @param msReq {@link MovieSerieRequest}
	 * @param user {@link User}
	 * @return {@link MessageResponse}
	 */
	ResponseEntity<?> updateScore(MovieSerieRequest msReq, User user);
	
	/**
	 * Metodo que se encarga de actualizar  {@link MovieSerie} ya fue vista y el numero de visualizaciones
	 * @param msReq {@link MovieSerieRequest}
	 * @param user {@link User}
	 * @return {@link MessageResponse}
	 */
	ResponseEntity<?> updateView(MovieSerieRequest msReq, User user);
}
