package com.sagor.blog.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.sagor.blog.filter.JwtTokenProvider;
import com.sagor.blog.model.User;
import com.sagor.blog.payloadordto.LoginDto;
import com.sagor.blog.payloadordto.LoginResponseDto;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.repository.UserRepository;
import com.sagor.blog.services.AuthService;
import com.sagor.blog.utils.ResponseBuilder;

@Service
public class AuthServiceImplement implements AuthService {
	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;

	public AuthServiceImplement(UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
			AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.jwtTokenProvider = jwtTokenProvider;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Response login(LoginDto loginDto) {
		User user = userRepository.findBynameAndIsActiveTrue(loginDto.getName());
		if (user == null) {
			return ResponseBuilder.getFailureResponse(HttpStatus.UNAUTHORIZED, "Invalid username or password");
		}
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getName(), loginDto.getPassword()));
		if (authentication.isAuthenticated()) {
			LoginResponseDto responseDto = new LoginResponseDto();
			responseDto.setToken(jwtTokenProvider.generateToken(authentication));
			responseDto.setName(user.getName());
			return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Login Successfully", responseDto);
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST, "Invalid username or password");
	}

}
