package com.sagor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sagor.annotation.ApiController;
import com.sagor.model.User;
import com.sagor.service.UserService;

@ApiController
@RequestMapping("/userController")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/add")
	public User save(@RequestBody User user) {
		return userService.save(user);
	}

	@PutMapping("/update")
	public User update(@RequestBody User user) {
		return userService.update(user);
	}

	@GetMapping("/get/{id}")
	public User get(@PathVariable("id") Long id) {
		return userService.get(id);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		return userService.delete(id);
	}

	@GetMapping("/getAll")
	public List<User> getAll() {
		return userService.getAll();
	}

}
