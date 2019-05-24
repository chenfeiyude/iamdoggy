package com.iamdoggy.iamdoggy.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.iamdoggy.iamdoggy.dtos.management.UserDTO;
import com.iamdoggy.iamdoggy.interfaces.management.AuthService;


/**
 * This filter is only for spring api only. Need to disable it in spring/Application. Otherwise it is enable for all pages
 * @author fchen
 *
 */
@Component
@Slf4j
public class IamdoggyAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	
        UserDTO usersDTO = authService.authenticate(request);    

        Authentication auth = null;
        if (usersDTO != null) {
        	// return a authenticated user
            auth = new UsernamePasswordAuthenticationToken(usersDTO.getUsername(), usersDTO.getPassword(), Collections.emptyList());
		}
        else {
        	// return a unauthenticated user
        	auth = new UsernamePasswordAuthenticationToken(null, null);
        }
        
        SecurityContextHolder.getContext().setAuthentication(auth);            
        
        filterChain.doFilter(request, response);
    }

}
