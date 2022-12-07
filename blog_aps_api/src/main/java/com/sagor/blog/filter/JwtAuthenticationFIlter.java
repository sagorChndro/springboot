package com.sagor.blog.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sagor.blog.model.User;
import com.sagor.blog.repository.UserRepository;
import com.sagor.blog.services.impl.CustomUserDetailsService;

@Configuration
public class JwtAuthenticationFIlter extends OncePerRequestFilter {
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	private final CustomUserDetailsService customUserDetailsService;

	public JwtAuthenticationFIlter(JwtTokenProvider jwtTokenProvider, UserRepository userRepository,
			CustomUserDetailsService customUserDetailsService) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.userRepository = userRepository;
		this.customUserDetailsService = customUserDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = getJwtFromRequest(request);
			if (StringUtils.hasText(jwt) && jwtTokenProvider.isValidate(jwt)) {
				Long userId = jwtTokenProvider.getUserIdFromToken(jwt);
				User user = userRepository.findByuserIdAndIsActiveTrue(userId);
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getName());
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		filterChain.doFilter(request, response);

	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
