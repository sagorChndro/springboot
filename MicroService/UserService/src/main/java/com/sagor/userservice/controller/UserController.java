package com.sagor.userservice.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sagor.userservice.annotation.ApiController;
import com.sagor.userservice.dto.Response;
import com.sagor.userservice.dto.UserDto;
import com.sagor.userservice.services.UserService;
import com.sagor.userservice.utils.ResponseBuilder;
import com.sagor.userservice.utils.UrlConstant;

@ApiController
@RequestMapping(UrlConstant.UserManagemant.ROOT)
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(UrlConstant.UserManagemant.CREATE_USER)
	public Response createUser(@Valid @RequestBody UserDto userDto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean binding error");
		}
		return userService.saveUser(userDto);
	}

	@PutMapping(UrlConstant.UserManagemant.UPDATE_USER)
	public Response updateUser(@Valid @RequestBody UserDto userDto, @PathVariable String userId, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean binding error");
		}
		return userService.updateUser(userDto, userId);
	}

	@GetMapping(UrlConstant.UserManagemant.GET_USER)
	public Response getUser(@PathVariable String userId) {
		return userService.getUser(userId);
	}

	@DeleteMapping(UrlConstant.UserManagemant.DELETE_USER)
	public Response deleteUser(@PathVariable String userId) {
		return userService.deleteUser(userId);
	}

	@GetMapping(UrlConstant.UserManagemant.GET_ALL)
	public Response getAll() {
		return userService.getAllUsers();
	}

}
