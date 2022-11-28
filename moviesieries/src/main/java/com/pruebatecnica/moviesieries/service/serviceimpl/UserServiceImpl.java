/**
 * 
 */
package com.pruebatecnica.moviesieries.service.serviceimpl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.pruebatecnica.moviesieries.controller.response.MessageResponse;
import com.pruebatecnica.moviesieries.controller.response.UserResponse;
import com.pruebatecnica.moviesieries.entity.User;
import com.pruebatecnica.moviesieries.repository.UserRepository;
import com.pruebatecnica.moviesieries.repository.filter.FilterSpecification;
import com.pruebatecnica.moviesieries.repository.filter.SearchSpecification;
import com.pruebatecnica.moviesieries.service.UserService;

/**
 * Servicio para la logica de negocio encargada de manipular la informacion de los {@link User}
 * @author Andres Restrepo
 *
 */
@Service
public class UserServiceImpl implements UserService {

	/**Usuario de la session */
	private User userSession;
	/**
	 * Interface que se encarga de manipular la informacion en la BD
	 */
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Busca todos los usuarios.<br>
	 * Los filtra primero el nombre del usuario ingresado<br>
	 * Si el resultado del filtro es que esta vacio, se genera mensaje de error y prohibe el ingreso.<br>
	 * Si ingreso un usario correcto, se valida la contraseña, si esta correcta le da paso, sino genera mensaje de error.
	 */
	@Override
	public ResponseEntity<?> findUserByUsernamePassword(final String userName, final String pwd) {
		List<User> users = userRepository.findAll();
		Optional<User> user = users.stream().filter( u -> StringUtils.equalsIgnoreCase(u.getUsername() , userName)).findAny();
		ResponseEntity<?> response;
		if(user.isEmpty()) {
			response = new ResponseEntity<MessageResponse> (new MessageResponse ("El usuario ingresado no existe!!!", 1),HttpStatus.OK);
		}else if(!StringUtils.equalsIgnoreCase(user.get().getPassword(), pwd)) {
			response = new ResponseEntity<MessageResponse> (new MessageResponse ("El password ingresado no es correcto!!!", 1),HttpStatus.OK);
		}else {
			User userEntity = user.get();
			
			Date date = DateUtils.addMilliseconds(new Date(), 3660000);
			String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
			String session = MessageFormat.format("{0},{1},{2}",userEntity.getId(),Long.toString(date.getTime()) , uuid) ;
			userEntity.setSession(Base64.getEncoder().encodeToString(session.getBytes()));
			userRepository.save(userEntity);
			
			UserResponse userResp = new UserResponse(userEntity.getId(), userEntity.getUsername(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getSession());
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();			
			headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE); 
			response = new ResponseEntity<UserResponse> ( userResp, headers, HttpStatus.OK );
		}
		return response;
	}

	@Override
	public boolean authorize(String tokenSession) {		
		List<FilterSpecification> filters = new ArrayList<FilterSpecification>();
		filters.add(FilterSpecification.getFilterStringEqual("session", tokenSession));
		SearchSpecification<User> ss = new SearchSpecification<>(filters);
		List<User> users = userRepository.findAll(ss);		
		boolean authorize = true;
		if(CollectionUtils.isEmpty(users) ||  users.size()>1) {
			authorize = false;
		}else {
			byte[] decodedBytes = Base64.getDecoder().decode(tokenSession.getBytes());
			String[] data = new String(decodedBytes).split(",");						
			Date date = new Date();
			Date dateSession = new Date(Long.parseLong(data[1]));			
			authorize = dateSession.after(date);
			setUserSession(users.get(0));
		}
		return authorize;
	}

	/**
	 * @return the userSession
	 */
	public User getUserSession() {
		return userSession;
	}

	/**
	 * @param userSession the userSession to set
	 */
	public void setUserSession(User userSession) {
		this.userSession = userSession;
	}

}
