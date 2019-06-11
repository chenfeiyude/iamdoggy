package com.fytech.iamdoggy.interfaces.management;



import javax.servlet.http.HttpServletRequest;

import org.apache.http.auth.AuthenticationException;

import com.fytech.iamdoggy.dtos.management.UserDTO;

public interface AuthService {
	UserDTO authenticate(HttpServletRequest request);
	UserDTO register(String username, String password);
	boolean checkEmail(String username);
	UserDTO login(String username, String password) throws AuthenticationException;
	UserDTO getUserFromSession(HttpServletRequest request);
}
