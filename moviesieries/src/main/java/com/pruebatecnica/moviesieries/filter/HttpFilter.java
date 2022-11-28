/**
 * 
 */
package com.pruebatecnica.moviesieries.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.pruebatecnica.moviesieries.service.serviceimpl.UserServiceImpl;

/**
 * Clase para realizar el filtro de las peticiones Http
 * @author Andres Restrepo
 *
 */
@Component
public class HttpFilter implements Filter{

	@Autowired
	private UserServiceImpl userService;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		if(StringUtils.contains("/api/auth", req.getRequestURI())) {
			chain.doFilter(request, response);	
		}else {
			String auth = req.getHeader("authorization");
			if (StringUtils.isEmpty(auth) || !userService.authorize(auth)) {
				HttpServletResponse res = (HttpServletResponse) response;
				res.sendError(HttpStatus.UNAUTHORIZED.value(), "Acceso Denesago!!!");
				res.setStatus(HttpStatus.UNAUTHORIZED.value());
				response = res;				
				return;
			}			
			chain.doFilter(request, response);
		}					
	}

}
