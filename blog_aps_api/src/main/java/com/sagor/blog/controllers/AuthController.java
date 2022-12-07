package com.sagor.blog.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sagor.blog.annotations.ApiController;
import com.sagor.blog.payloadordto.LoginDto;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.payloadordto.UserDto;
import com.sagor.blog.services.AuthService;
import com.sagor.blog.services.UserService;

@ApiController
@RequestMapping("/v1/api/auth")
public class AuthController {
	private final AuthService authService;
	private final UserService userService;

	public AuthController(AuthService authService, UserService userService) {
		this.authService = authService;
		this.userService = userService;
	}

	@PostMapping("/login")
	public Response login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
		return authService.login(loginDto);
	}

	@PostMapping("/register")
	public Response registerNewUser(@RequestBody UserDto userDto) {
		return userService.registerNewUser(userDto);
	}

}
