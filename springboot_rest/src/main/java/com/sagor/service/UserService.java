package com.sagor.service;

import java.util.List;

import com.sagor.model.User;

public interface UserService {

	User save(User user);

	User update(User user);

	User get(Long id);

	String delete(Long id);

	User getUsername(String username);

	List<User> getAll();

}
