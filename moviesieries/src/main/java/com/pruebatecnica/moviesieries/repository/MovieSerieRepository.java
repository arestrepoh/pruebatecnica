/**
 * 
 */
package com.pruebatecnica.moviesieries.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.moviesieries.entity.MovieSerie;

/**
 * Interface para implementar las acciones a las acciones a la Base de Datos.<br>
 * Implementa el {@link JpaSpecificationExecutor} para el filtrado de los datos.
 * @author Andres Restrepo
 *
 */
@Repository
public interface MovieSerieRepository extends JpaRepository<MovieSerie, Long>,  JpaSpecificationExecutor<MovieSerie> {
	
	/**
	 * Metodo que retorna un {@link MovieSerie} de manera aleatoria.
	 * @return
	 */
	@Query(nativeQuery = true ,value = " SELECT id,name,gender, type,numberviews,score FROM movieseries ms Order by RAND() LIMIT 1")
	Iterable<MovieSerie> findRandom();
	
    @Transactional
    @Modifying
    @Query(value = "UPDATE movieseries ms SET ms.score = (SELECT AVG(score) FROM usermovieseries WHERE  movieSerie = :id)  where ms.id = :id",
            nativeQuery = true)
    void updateScore(@Param("id") Long id);
}
