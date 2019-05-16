package com.iamdoggy.iamdoggy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	
	@Autowired
	private IamdoggyAuthenticationProvider iamdoggyAuthenticationProvider;
	
	@Autowired
	private IamdoggyAuthenticationFilter iamdoggyAuthenticationFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// only apply new filter to the new spring api urls
		http.antMatcher("/api/**");
			
		http.csrf().disable().authorizeRequests()
			.antMatchers("/api/**").authenticated()
			.and().httpBasic().authenticationEntryPoint(authEntryPoint)
			.and().addFilterBefore(iamdoggyAuthenticationFilter, BasicAuthenticationFilter.class)
         ;   
	}

	 @Override
	 public void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.authenticationProvider(iamdoggyAuthenticationProvider);
	 }
	 
	 @Override
		public void configure(WebSecurity web) throws Exception {
		    web.ignoring().antMatchers(
		    		"/api/login**", 
		    		"/api/register**", 
		    		"/api/register/**", 
		    		"/api/logout**",
		    		"/api/reset_password**",
		    		"/api/forgot_password**")
		    		;
		}

}
