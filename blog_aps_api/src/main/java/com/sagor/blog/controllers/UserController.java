package com.sagor.blog.controllers;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sagor.blog.annotations.ApiController;
import com.sagor.blog.annotations.ValidateData;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.payloadordto.UserDto;
import com.sagor.blog.services.UserService;
import com.sagor.blog.utils.UrlConstraint;

@ApiController
@RequestMapping(UrlConstraint.UserManagement.USER_ROOT)
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(UrlConstraint.UserManagement.CREATE_USER)
	@ValidateData
	public Response createUser(@Valid @RequestBody UserDto userDto, BindingResult result) {
//		if (result.hasErrors()) {
//			return ResponseBuilder.getFailureResponse(result, "Bean binding error");
//		}
		return userService.createUser(userDto);
	}

	@PutMapping(UrlConstraint.UserManagement.UPDATE_USER)
	@ValidateData
	public Response updateUser(@PathVariable("userId") Long userId, @Valid @RequestBody UserDto userDto,
			BindingResult result) {
//		if (result.hasErrors()) {
//			return ResponseBuilder.getFailureResponse(result, "Bean binding error");
//		}
		return userService.updateUser(userDto, userId);
	}

	@GetMapping(UrlConstraint.UserManagement.GET_USER)
	public Response getUser(@PathVariable("userId") Long userId) {
		return userService.getUser(userId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(UrlConstraint.UserManagement.DELETE_USER)
	public Response delete(@PathVariable("userId") Long userId) {
		return userService.delete(userId);
	}

	@GetMapping(UrlConstraint.UserManagement.GET_ALL_USER)
	public Response getAll() {
		return userService.getAllUsers();
	}

}
