/**
 * 
 */
package com.pruebatecnica.moviesieries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.pruebatecnica.moviesieries.entity.UserMovieSerie;

/**
 * Interface que implementa el {@link JpaRepository} para {@link UserMovieSerie}
 * @author Andres Restrepo
 *
 */
public interface UserMovieRepository extends JpaRepository<UserMovieSerie, Long>, JpaSpecificationExecutor<UserMovieSerie> {	
	
}
