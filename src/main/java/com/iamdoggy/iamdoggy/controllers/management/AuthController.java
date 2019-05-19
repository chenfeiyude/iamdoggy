package com.iamdoggy.iamdoggy.controllers.management;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.http.auth.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iamdoggy.iamdoggy.dtos.management.UserDTO;
import com.iamdoggy.iamdoggy.interfaces.management.AuthService;
import com.iamdoggy.iamdoggy.models.request.AuthUser;

@RestController
@RequestMapping(value="/api/auth")
@Validated
public class AuthController {
	private static Logger logger = LoggerFactory.getLogger(AuthController.class.getName());
	
	@Autowired
	private AuthService authService;
	
	/**
	 * e.g.
	 * https://localhost/api/auth/login.do
	 * 
	 * post data e.g.
	 * 
	 * {
	 * 		"username": "test@iamdoggy.com",
	 * 		"password": "1234"
	 * }
	 */
	@RequestMapping(method=RequestMethod.POST, value="/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO login(@RequestBody @Valid AuthUser authUser, HttpServletRequest request) throws AuthenticationException {
		UserDTO userDTO = authService.login(authUser.getUsername(), authUser.getPassword());
		
		HttpSession session = request.getSession(true);	
		session.setAttribute("username", userDTO.getUsername());
		session.setAttribute("token", userDTO.getToken());
		
		logger.info("Found public user " + userDTO.getUsername());
		
		return userDTO;
	}
	
	/**
	 * e.g.
	 * https://localhost/api/auth/register.do
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
	 */
	@RequestMapping(method=RequestMethod.POST, value="/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestBody @Valid AuthUser authUser, HttpServletRequest request) throws IllegalArgumentException {
		UserDTO userDTO = authService.register(authUser.getUsername(), authUser.getPassword());
		
	}
}
