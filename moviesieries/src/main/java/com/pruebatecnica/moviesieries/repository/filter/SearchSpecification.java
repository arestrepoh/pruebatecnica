/**
 * 
 */
package com.pruebatecnica.moviesieries.repository.filter;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.pruebatecnica.moviesieries.entity.MovieSerie;

/**
 * Especificacion para la busqueda o filtrado de las {@link MovieSerie}
 * @author Andres Restrepo
 *
 */
public class SearchSpecification<T> implements Specification<T> {

	private static final long serialVersionUID = -38604183651533859L;

	/**
	 * Listado de los filtros a aplicar.
	 */
	private List<FilterSpecification> filterSpecications;

	/**
	 * Constructor
	 * @param filterSpecications
	 */
	public SearchSpecification(List<FilterSpecification> filterSpecications) {
		super();
		this.filterSpecications = filterSpecications;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		Predicate predicate = criteriaBuilder.equal(criteriaBuilder.literal(Boolean.TRUE), Boolean.TRUE);
		for (FilterSpecification filter : this.filterSpecications) {
			predicate = filter.getOperator().build(root, criteriaBuilder, filter, predicate);
		}
		return predicate;
	}

}
