package com.iamdoggy.iamdoggy.services.management;

import java.time.LocalDateTime;

import org.apache.http.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iamdoggy.iamdoggy.dtos.management.UserDTO;
import com.iamdoggy.iamdoggy.interfaces.daos.management.UserJpaDAO;
import com.iamdoggy.iamdoggy.interfaces.management.AuthService;

@Service("authService")
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UserJpaDAO userJpaDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDTO register(String username, String password) {
		UserDTO userDTO = userJpaDAO.findByUsername(username);
		if (userDTO != null) {
			throw new IllegalArgumentException("This username has already been registered");
		}

		userDTO = new UserDTO();
		userDTO.setUsername(username);
		userDTO.setPassword(passwordEncoder.encode(password));
		userDTO.generateToken();
		userDTO.appendLog("Register new account");
		userJpaDAO.save(userDTO);
		
		return userDTO;
	}

	@Override
	public UserDTO login(String username, String password) throws AuthenticationException {
		UserDTO userDTO = userJpaDAO.findByUsername(username);
		if (userDTO == null || !userDTO.isLive()) {
			throw new AuthenticationException("Invalid username");
		}
		else if (!userDTO.getPassword().equals(passwordEncoder.encode(password))) {
			throw new AuthenticationException("username and password not match");
		}
		userDTO.generateToken();
		userDTO.setLastLogin(LocalDateTime.now());
		userJpaDAO.save(userDTO);
		
		return userDTO;
	}
	
	
}
