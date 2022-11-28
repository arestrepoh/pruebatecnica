package com.pruebatecnica.moviesieries.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebatecnica.moviesieries.entity.Type;

/**
 * Interface que implementa el {@link JpaRepository} para {@link Type}
 * @author Andres Restrepo
 *
 */
public interface TypeRepository  extends JpaRepository<Type, Long>{

}
