/**
 * 
 */
package com.pruebatecnica.moviesieries.service.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pruebatecnica.moviesieries.controller.request.FilterRequest;
import com.pruebatecnica.moviesieries.controller.request.MovieSerieRequest;
import com.pruebatecnica.moviesieries.controller.request.SortRequest;
import com.pruebatecnica.moviesieries.controller.response.MessageResponse;
import com.pruebatecnica.moviesieries.entity.Gender;
import com.pruebatecnica.moviesieries.entity.MovieSerie;
import com.pruebatecnica.moviesieries.entity.Type;
import com.pruebatecnica.moviesieries.entity.User;
import com.pruebatecnica.moviesieries.entity.UserMovieSerie;
import com.pruebatecnica.moviesieries.repository.GenderRepository;
import com.pruebatecnica.moviesieries.repository.MovieSerieRepository;
import com.pruebatecnica.moviesieries.repository.TypeRepository;
import com.pruebatecnica.moviesieries.repository.UserMovieRepository;
import com.pruebatecnica.moviesieries.repository.filter.FilterSpecification;
import com.pruebatecnica.moviesieries.repository.filter.SearchSpecification;
import com.pruebatecnica.moviesieries.service.MovieAndSerieService;

/**
 * Clase encargada de la logica de negocio para {@link MovieSerie}
 * 
 * @author Andres Restrepo
 *
 */
@Service
public class MovieSerieServiceImpl implements MovieAndSerieService {

	/**
	 * Se inserta la clase encargada de conectar, manipular y obtener los datos de
	 * {@link Gender}
	 */
	@Autowired
	private GenderRepository genderRepository;

	/**
	 * Se inserta la clase encargada de conectar, manipular y obtener los datos de
	 * {@link Type}
	 */
	@Autowired
	private TypeRepository typeRepository;

	/**
	 * Se inserta la clase encargada de conectar, manipular y obtener los datos de
	 * {@link MovieSerie}
	 */
	@Autowired
	private MovieSerieRepository movieAndSerieRepository;

	@Autowired
	private UserMovieRepository umsRepository;

	@Override
	public Iterable<MovieSerie> findAll() {
		return find(null);
	}

	@Override
	public Iterable<MovieSerie> findAll(SortRequest orderBy) {
		return find(orderBy);
	}

	@Override
	public Iterable<MovieSerie> findAll(List<FilterRequest> filters) {
		try {
			FilterSpecification.getFilter().clear();
			filters.stream().forEach(f -> {
				switch (f.getId()) {
				case "1":
					FilterSpecification.getFilter()
							.add(FilterSpecification.getFilterStringLike(f.getField(), f.getValue()));
					break;
				case "2":
				case "3":
					FilterSpecification.getFilter()
							.add(FilterSpecification.getFilterNumberEqual(f.getField(), f.getValue()));
					break;
				default:
					break;
				}
			});
			SearchSpecification<MovieSerie> ss = new SearchSpecification<>(FilterSpecification.getFilter());
			return movieAndSerieRepository.findAll(ss);
		} finally {
			FilterSpecification.getFilter().clear();
		}
	}

	@Override
	public Iterable<MovieSerie> findAllRandom() {
		return movieAndSerieRepository.findRandom();
	}

	@Override
	public Iterable<Gender> getAllGender() {
		return genderRepository.findAll();
	}

	@Override
	public Iterable<Type> getAllType() {
		return typeRepository.findAll();
	}

	@Override
	public ResponseEntity<?> updateScore(MovieSerieRequest msReq, User user) {
		return execUpdate(msReq, user, true);
	}

	@Override
	public ResponseEntity<?> updateView(MovieSerieRequest msReq, User user) {
		return execUpdate(msReq, user, false);
	}
	
	/**
	 * Realiza la ejecucion de las actualizacion y sus validaciones.
	 * @param msReq
	 * @param user
	 * @return
	 */
	private ResponseEntity<?> execUpdate(MovieSerieRequest msReq, User user, boolean updateScore) {
		Optional<MovieSerie> ms = movieAndSerieRepository.findById(msReq.getId());
		if (ms.isEmpty()) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("No existe la Pelicula o serie!!!", 1),
					HttpStatus.OK);
		}
		Optional<UserMovieSerie> umsOp = getUserMovie(msReq, user);
		UserMovieSerie ums;
		if (umsOp.isEmpty()) {
			ums = new UserMovieSerie();
			ums.setMovieSerie(ms.get());
			ums.setUser(user);
		} else {
			ums = umsOp.get();
		}
		try {
			ResponseEntity<MessageResponse> response;
			if (updateScore) {
				response = updateScore(msReq, ums);
			} else {
				response = updateView(msReq, ums);
			}
			return response;
		} catch (Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Se presento un error inesperado !!!", 1),
					HttpStatus.OK);
		}
	}

	/**
	 * Actualiza el numero de visualizacion de {@link MovieSerie} y la visualizacion del {@link UserMovieSerie}
	 * @param msReq
	 * @param ums
	 * @return
	 */
	private ResponseEntity<MessageResponse> updateView(MovieSerieRequest msReq, UserMovieSerie ums) {
		if (ums.isView()) {
			return new ResponseEntity<MessageResponse>(
					new MessageResponse("Ya indico que vio la pelicula o serie. No  puede hacerlo de nuevo", 1),
					HttpStatus.OK);
		}
		ums.setView(msReq.isView());
		umsRepository.save(ums);
		MovieSerie ms = ums.getMovieSerie();
		ms.setNumberviews(ms.getNumberviews() + 1);
		movieAndSerieRepository.save(ms);
		return new ResponseEntity<MessageResponse>(new MessageResponse("Actualizado!!!", 0), HttpStatus.OK);
	}

	/**
	 * Actualiza el Score del {@link MovieSerie} 
	 * @param msReq
	 * @param ums
	 * @return 
	 */
	private ResponseEntity<MessageResponse> updateScore(MovieSerieRequest msReq, UserMovieSerie ums) {
		if (ums.getScore() > 0) {
			return new ResponseEntity<MessageResponse>(
					new MessageResponse("Ya le dio el puntaje a la pelicula o serie. No  puede hacerlo de nuevo", 1),
					HttpStatus.OK);
		}
		ums.setScore(msReq.getScore());
		umsRepository.save(ums);
		movieAndSerieRepository.updateScore(ums.getMovieSerie().getId());
		return new ResponseEntity<MessageResponse>(new MessageResponse("Actualizado!!!", 0), HttpStatus.OK);
	}

	/**
	 * Obtiene el {@link UserMovieSerie} filtrado por el id del {@link User} y el id del {@link MovieSerie}
	 * @param msReq
	 * @param user
	 * @return
	 */
	private Optional<UserMovieSerie> getUserMovie(MovieSerieRequest msReq, User user) {
		List<FilterSpecification> filters = new ArrayList<FilterSpecification>();
		filters.add(FilterSpecification.getFilterNumberEqual("user", user.getId()));
		filters.add(FilterSpecification.getFilterNumberEqual("movieSerie", msReq.getId()));
		SearchSpecification<UserMovieSerie> ss = new SearchSpecification<>(filters);

		return umsRepository.findOne(ss);
	}

	/**
	 * Realiza la buscada de las {@link MovieSerie}e
	 * 
	 * @param orderBy
	 * @return
	 */
	private Iterable<MovieSerie> find(SortRequest orderBy) {
		if (orderBy == null) {
			List<Order> orders = new ArrayList<Order>();
			orders.add(new Order(Direction.ASC, "name"));
			orders.add(new Order(Direction.ASC, "type.name"));
			orders.add(new Order(Direction.ASC, "gender.name"));
			orders.add(new Order(Direction.ASC, "score"));
			return movieAndSerieRepository.findAll(Sort.by(orders));
		}
		return movieAndSerieRepository.findAll(Sort.by(orderBy.getDirection(), orderBy.getFieldSort()));
	}
}
