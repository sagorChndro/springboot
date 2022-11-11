package com.sagor.service;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.sagor.model.Role;
import com.sagor.model.User;
import com.sagor.repository.RoleRepository;
import com.sagor.repository.UserRepository;

@Configuration
public class DbInit {
	@Value("${login.username}")
	private String username;
	@Value("${login.password}")
	private String password;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public DbInit(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@PostConstruct
	public void init() {
		String roleName = "ADMIN";
		int roleExistCount = roleRepository.countByName(roleName);
		Role role = null;
		if (roleExistCount == 1) {
			role = roleRepository.findByName(roleName);
		} else {
			role = new Role();
			role.setName(roleName);
			roleRepository.save(role);
		}
		User user = userRepository.findByUsernameIsActiveTrue(username);
		if (user == null) {
			user = new User();
			user.setName(username);
			user.setEmail("abc@ab.com");
			user.setPassword(password);
		}
		user.setRoles(Arrays.asList(role));
		user = userRepository.save(user);

	}

}
