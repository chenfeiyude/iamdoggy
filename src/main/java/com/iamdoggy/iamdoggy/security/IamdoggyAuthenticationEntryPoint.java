package com.iamdoggy.iamdoggy.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.iamdoggy.iamdoggy.services.management.AuthServiceImpl;

@Component
public class IamdoggyAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
	protected static Log logger = LogFactory.getLog(IamdoggyAuthenticationEntryPoint.class.getName());
	private static final String REQUEST_FROM_WEB = "web";
	
	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
      throws IOException, ServletException {
		String requestFrom = request.getHeader(AuthServiceImpl.Credentials.KEY_REQUEST_FROM);
		
		if(REQUEST_FROM_WEB.equals(requestFrom) )
			logger.info("Request from web, we don't add WWW-Authenticate into header");
		else
			response.addHeader("WWW-Authenticate", "Basic realm=" +getRealmName());
      
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 - " + authEx.getMessage());
    }
	
	@Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("Iamdoggy API");
        super.afterPropertiesSet();
    }

}
