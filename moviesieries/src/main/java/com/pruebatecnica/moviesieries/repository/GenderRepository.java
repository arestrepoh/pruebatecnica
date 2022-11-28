/**
 * 
 */
package com.pruebatecnica.moviesieries.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebatecnica.moviesieries.entity.Gender;

/**
 * Interface que implementa el {@link JpaRepository} para {@link Gender}
 * @author Andres Restrepo
 *
 */
public interface GenderRepository  extends JpaRepository<Gender, Long> {

}
