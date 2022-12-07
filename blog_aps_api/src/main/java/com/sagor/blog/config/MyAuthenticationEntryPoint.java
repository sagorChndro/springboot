package com.sagor.blog.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
public class MyAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
	private static final Logger logger = LogManager.getLogger(MyAuthenticationEntryPoint.class);

	@Override
	public void afterPropertiesSet() {
		setRealmName("BlogApiProject");
		super.afterPropertiesSet();
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.addHeader("WWW-Authenticate", "Basic Realm=" + getRealmName());
		logger.error("message " + authException.getMessage());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
	}

}
