package com.sagor.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sagor.model.User;
import com.sagor.repository.UserRepository;
import com.sagor.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
	}

	@Override
	public User get(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent() && userOptional != null) {
			return userOptional.get();
		}
		return null;
	}

	@Override
	public String delete(Long id) {
		userRepository.deleteById(id);
		return "Deleted successfully";

	}

	@Override
	public List<User> getAll() {
		List<User> user = userRepository.findAll();
		return user;
	}

	@Override
	public User getUsername(String username) {
		return userRepository.findByUsernameIsActiveTrue(username);
	}

}
