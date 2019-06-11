package com.fytech.iamdoggy.controllers.management;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fytech.iamdoggy.dtos.management.UserDTO;
import com.fytech.iamdoggy.interfaces.management.AuthService;
import com.fytech.iamdoggy.models.request.AuthUser;

//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping(value="/api/auth")
@Validated
@Slf4j
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	/**
	 * e.g.
	 * https://localhost:8080/iamdoggy-0.0.1-SNAPSHOT/api/auth/login.do
	 * 
	 * post data e.g.
	 * 
	 * {
	 * 		"username": "test@iamdoggy.com",
	 * 		"password": "1234"
	 * }
	 */
	@RequestMapping(method=RequestMethod.POST, value="/login", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO login(@RequestBody @Valid AuthUser authUser, HttpServletRequest request) throws AuthenticationException {
		UserDTO userDTO = authService.login(authUser.getUsername(), authUser.getPassword());
		
		HttpSession session = request.getSession(true);	
		session.setAttribute("username", userDTO.getUsername());
		session.setAttribute("token", userDTO.getToken());
		log.info("Found public user " + userDTO.getUsername() + " with token: " + userDTO.getToken());
		
		return userDTO;
	}
	
	/**
	 * e.g.
	 * https://localhost:8080/iamdoggy-0.0.1-SNAPSHOT/api/auth/register.do
	 * 
	 * post data e.g.
	 * 
	 * {
	 * 		"username": "test@iamdoggy.com",
	 * 		"password": "1234"
	 * }
	 * 
	 * @param authUser
	 * @param request
	 * @throws IllegalArgumentException
	 * @throws AuthenticationException 
	 */
	@RequestMapping(method=RequestMethod.POST, value="/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO register(@RequestBody @Valid AuthUser authUser, HttpServletRequest request) throws IllegalArgumentException, AuthenticationException {
		authService.register(authUser.getUsername(), authUser.getPassword());
		// TODO add validate email later
		return login(authUser, request);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/check_email")
    public Map<String, Object> check_email(@RequestParam("username") String username, HttpServletRequest request) throws IllegalArgumentException {
		boolean exist = authService.checkEmail(username);
		Map<String, Object> response = new HashMap<>();
		response.put("exist", exist);
		return response;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/logout")
    public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}
}
