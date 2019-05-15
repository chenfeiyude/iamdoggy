package com.iamdoggy.iamdoggy.interfaces.management;



import org.apache.http.auth.AuthenticationException;

import com.iamdoggy.iamdoggy.dtos.management.UserDTO;

public interface AuthService {
	UserDTO register(String username, String password);
	UserDTO login(String username, String password) throws AuthenticationException;
}
