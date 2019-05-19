package com.iamdoggy.iamdoggy.services.management;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.auth.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iamdoggy.iamdoggy.dtos.management.UserDTO;
import com.iamdoggy.iamdoggy.interfaces.daos.management.UserJpaDAO;
import com.iamdoggy.iamdoggy.interfaces.management.AuthService;

@Service("authService")
@Transactional
public class AuthServiceImpl implements AuthService {
	private static Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class.getName());
	
	public class Credentials {
		
		public static final String KEY_API_KEY = "api-key";
		public static final String KEY_TOKEN = "token";
		public static final String KEY_USERNAME = "username";
		public static final String KEY_REQUEST_FROM = "request-from";
		
		private String login;
		private String password;

		public Credentials(String login, String password) {
			this.login = login;
			this.password = password;
		}
		
		public String getLogin() {
			return login;
		}
		public String getPassword() {
			return password;
		}
	}
	
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
		logger.info(username + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		UserDTO userDTO = userJpaDAO.findByUsername(username);
		if (userDTO == null || !userDTO.isLive()) {
			throw new AuthenticationException("Invalid username");
		}
		else if (!passwordEncoder.matches(password, userDTO.getPassword())) {
			throw new AuthenticationException("username and password not match");
		}
		userDTO.generateToken();
		userDTO.setLastLogin(LocalDateTime.now());
		userJpaDAO.save(userDTO);
		
		return userDTO;
	}

	@Override
	public UserDTO authenticate(HttpServletRequest request) {
		Credentials cred = getCredentialsBasicAuthentication(request);
		String ip = request.getRemoteAddr();
		if (cred==null) {
			String token = request.getHeader(Credentials.KEY_TOKEN);
			String username = request.getHeader(Credentials.KEY_USERNAME);
			if (token != null && username != null) {
				logger.info("User: " +username+ " token = "+token+" connects from IP: "+ip);
			}
			return validateTokenUser(username, token, request);
		}
		logger.info("User: "+cred.getLogin()+" connects from IP: "+ip);
		return validateLiveUser(cred);
	}
	
	private UserDTO validateLiveUser(Credentials cred)
	{
		if(cred==null)
			return null;
		
		String username=cred.getLogin();
		if(StringUtils.isEmpty(username))
			return null;
		
		UserDTO user = userJpaDAO.findByUsername(username);
		if (user == null || !user.isLive())
		{
			logger.error("validateLiveUser: Username "+username+" is disabled or couldn't be found in DB");
			return null;
		}
		
		if (!passwordEncoder.matches(cred.getPassword(), user.getPassword()))
		{
			logger.info("validateLiveUser received incorrect password for: "+username);
			return null;
		}
		
		logger.info("validateLiveUser Authenticated: "+username);
		return user;
	}
	
	private UserDTO validateTokenUser(String username, String token, HttpServletRequest request) {
		if(StringUtils.isEmpty(token)) 
			return null;
		UserDTO user = userJpaDAO.findByUsernameAndToken(username, token);
		if(user == null) {
			logger.warn("No user found for username: " + username + " token: " + token);
		}
		else {
			HttpSession session = request.getSession();
			if(session!=null)
			{
				String sessionUsername = (String) session.getAttribute(Credentials.KEY_USERNAME);
				String sessionToken = (String) session.getAttribute(Credentials.KEY_TOKEN);
				if (!username.equals(sessionUsername) || !token.equals(sessionToken)) {
					//session timeout or not correct
					logger.info("sessionUsername:" + sessionUsername);
					logger.info("sessionToken:" + sessionToken);
					user = null;
				}
				else {
					logger.info("Found user "+user.getUsername()+" with api key "+token);
				}
			}
			else {
				user = null;
			}
		}
		return user;
	}
	
	private Credentials getCredentialsBasicAuthentication(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null) 
		{
			StringTokenizer st = new StringTokenizer(authHeader);
			if (st.hasMoreTokens()) 
			{
				String basic = st.nextToken();
			
				if (basic.equalsIgnoreCase("Basic")) 
				{
					try {
						String credentials = new String(Base64.decodeBase64(st.nextToken()), "UTF-8");
					
						int p = credentials.indexOf(":");
						if (p != -1) 
						{
							String login = credentials.substring(0, p).trim();
							String password = credentials.substring(p + 1).trim();
							return new Credentials(login, password);
						} 
						else 
							logger.error("Invalid authentication token");
						
					} catch (UnsupportedEncodingException e) {
						logger.warn("Couldn't retrieve authentication", e);
					}
				}
			}
		}
		return null;
	}
}
