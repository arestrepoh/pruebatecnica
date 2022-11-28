/**
 * 
 */
package com.pruebatecnica.moviesieries.repository.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.pruebatecnica.moviesieries.entity.Gender;
import com.pruebatecnica.moviesieries.entity.MovieSerie;
import com.pruebatecnica.moviesieries.entity.Type;
import com.pruebatecnica.moviesieries.entity.User;

/**
 * Enumeracion de las operaciones a realizar en las consultas.
 * 
 * @author Andres Restrepo
 *
 */
public enum Operator {

	EQUAL_STRING {
		public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterSpecification request, Predicate predicate) {
			Expression<?> key = getExpressionKey(root, request.getKey());
			return cb.and(cb.equal(key, request.getValue().toString()), predicate);
		}
	},

	EQUAL {
		public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterSpecification request, Predicate predicate) {
			Object value = getObjectValue(request.getValue());
			Expression<?> key = getExpressionKey(root, request.getKey());
			return cb.and(cb.equal(key, value), predicate);
		}
	},

	LIKE {
		public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterSpecification request, Predicate predicate) {
			Expression<String> key = root.get(request.getKey());
			return cb.and(cb.like(cb.upper(key), "%" + request.getValue().toString().toUpperCase() + "%"), predicate);
		}
	};

	/**
	 * Obtiene el Key
	 * 
	 * @param <T>
	 * @param root
	 * @param key
	 * @return
	 */
	private static <T> Expression<?> getExpressionKey(Root<T> root, String key) {
		String className = root.get(key).getJavaType().getName();
		Expression<?> expKey;
		if (StringUtils.equalsAnyIgnoreCase(className, Type.class.getCanonicalName())
				|| StringUtils.equalsAnyIgnoreCase(className, Gender.class.getCanonicalName())
				|| StringUtils.equalsAnyIgnoreCase(className, User.class.getCanonicalName())
				|| StringUtils.equalsAnyIgnoreCase(className, MovieSerie.class.getCanonicalName())) {
			expKey = root.get(key).get("id");
		} else {
			expKey = root.get(key);
		}
		return expKey;
	}

	/**
	 * Obtiene el valor del objeto segun su tipo de dato.
	 * @param value
	 * @return
	 */
	private static Object getObjectValue(Object value) {
		if (value instanceof Long) {
			return Long.parseLong(value.toString());
		}
		return value;
	}

	/**
	 * Metodo abstracto que ejecuta la enumeracion.
	 * 
	 * @param <T>
	 * @param root
	 * @param cb
	 * @param request
	 * @param predicate
	 * @return
	 */
	public abstract <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterSpecification request,
			Predicate predicate);
}
