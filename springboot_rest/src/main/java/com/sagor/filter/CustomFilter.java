package com.sagor.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sagor.model.User;
import com.sagor.service.UserService;
import com.sagor.service.impl.CustomUserDetailsService;

@Configuration
public class CustomFilter extends OncePerRequestFilter {
	private final PasswordEncoder passwordEncoder;
	private final UserService userService;
	private final CustomUserDetailsService customUserDetailsService;

	public CustomFilter(UserService userService, PasswordEncoder passwordEncoder,
			CustomUserDetailsService customUserDetailsService) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.customUserDetailsService = customUserDetailsService;
	}

	private User getUser(HttpServletRequest request) {
		String authString = null;
		Enumeration<String> headerNameEnumeration = request.getHeaderNames();
		while (headerNameEnumeration.hasMoreElements()) {
			String headerKey = headerNameEnumeration.nextElement();
			if (headerKey.equalsIgnoreCase("Authentication")) {
				authString = request.getHeader(headerKey);
				break;
			}
		}
		if (authString != null && !authString.equals("")) {
			String[] authParts = authString.split("\\s+");
			String authInfo = authParts[1];
			byte[] bytes = DatatypeConverter.parseBase64Binary(authInfo);
			String decodeAuth = new String(bytes);
			String usernameAndPassword[] = decodeAuth.split(":");
			if (usernameAndPassword[0] != null && usernameAndPassword[1] != null) {
				User user = userService.getUsername(usernameAndPassword[0]);
				if (passwordEncoder.matches(usernameAndPassword[1], user.getPassword())) {
					return user;
				}
				return null;
			}
		}
		return null;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			User user = getUser(request);
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {

		}
		filterChain.doFilter(request, response);

	}

}
