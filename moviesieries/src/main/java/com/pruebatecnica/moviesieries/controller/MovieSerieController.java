/**
 * 
 */
package com.pruebatecnica.moviesieries.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.moviesieries.controller.request.FilterRequest;
import com.pruebatecnica.moviesieries.controller.request.MovieSerieRequest;
import com.pruebatecnica.moviesieries.controller.request.SortRequest;
import com.pruebatecnica.moviesieries.controller.response.MessageResponse;
import com.pruebatecnica.moviesieries.entity.Gender;
import com.pruebatecnica.moviesieries.entity.MovieSerie;
import com.pruebatecnica.moviesieries.entity.Type;
import com.pruebatecnica.moviesieries.service.serviceimpl.MovieSerieServiceImpl;
import com.pruebatecnica.moviesieries.service.serviceimpl.UserServiceImpl;

/**
 * Controlador de los servicios Rest
 * 
 * @author Andres Restrepo
 *
 */
@RestController
@RequestMapping(value = "/api/movie-series")
public class MovieSerieController {

	@Autowired
	private MovieSerieServiceImpl msService;

	@Autowired
	private UserServiceImpl userService;

	/**
	 * Retorna los generos de las peliculas y de las seires
	 * 
	 * @return Iterable<Gender>
	 */
	@RequestMapping(value = "/gender", method = RequestMethod.GET)
	public ResponseEntity<?> getGender() {
		Iterable<Gender> genders = msService.getAllGender();
		return new ResponseEntity<Iterable<Gender>>(genders, HttpStatus.OK);
	}

	/**
	 * Retorna un listado de {@link Type}
	 * @return Iterable<Type>
	 */
	@RequestMapping(value = "/type", method = RequestMethod.GET)
	public ResponseEntity<?> getType() {
		Iterable<Type> types = msService.getAllType();
		return new ResponseEntity<Iterable<Type>>(types, HttpStatus.OK);
	}

	/**
	 * Retorna una pelicula o serie de manera aleatoria.
	 * 
	 * @return Iterable<MovieSerie>
	 */
	@RequestMapping(value = "/random", method = RequestMethod.GET)
	public ResponseEntity<?> getMovieAndSerieRandom() {
		Iterable<MovieSerie> ms = msService.findAllRandom();
		return new ResponseEntity<Iterable<MovieSerie>>(ms, HttpStatus.OK);
	}

	/**
	 * Retorna todas las peliculas o series<br>
	 * Ordenada por Id
	 * 
	 * @return Iterable<MovieSerie>
	 */
	@GetMapping
	public ResponseEntity<?> getMovieAndSerieAll() {
		Iterable<MovieSerie> ms = msService.findAll();
		return new ResponseEntity<Iterable<MovieSerie>>(ms, HttpStatus.OK);
	}

	/**
	 * Obtiene el listado de {@link MovieSerie} ordenado
	 * 
	 * @param sortBy Campo por el que se va ordenar
	 * @param desc   Si es ordenamiento descendente.
	 * @return Iterable<MovieSerie>
	 */
	@GetMapping(value = "/orderby")
	public ResponseEntity<?> getMovieAndSerieAllByOrder(@RequestParam(name = "sortby") String sortBy,
			@RequestParam(name = "desc") boolean desc) {
		Iterable<MovieSerie> ms = msService.findAll(new SortRequest(sortBy, desc));
		return new ResponseEntity<Iterable<MovieSerie>>(ms, HttpStatus.OK);
	}

	/**
	 * Obtiene listado de {@link MovieSerie} filtrado
	 * 
	 * @param filter Listado de filtros a aplicar.
	 * @return
	 */
	@PostMapping(value = "/filterby")
	public ResponseEntity<?> getMovieSerieAllByFilter(@RequestBody List<FilterRequest> filter) {
		Iterable<MovieSerie> ms = msService.findAll(filter);
		return new ResponseEntity<Iterable<MovieSerie>>(ms, HttpStatus.OK);
	}

	/**
	 * Actualiza el Puntaje de la Pelicula o Serie
	 * 
	 * @return {@link MessageResponse}
	 */
	@RequestMapping(value = "/uscore", method = RequestMethod.POST)
	public ResponseEntity<?> setScore(@RequestBody MovieSerieRequest msReq) {
		return msService.updateScore(msReq, userService.getUserSession());
	}

	/**
	 * Actualiza la pelicula o serie indicando que ya fue vista.
	 * 
	 * @return {@link MessageResponse}
	 */
	@RequestMapping(value = "/uview")
	public ResponseEntity<?> setView(@RequestBody MovieSerieRequest msReq) {
		return msService.updateView(msReq, userService.getUserSession());
	}
}
