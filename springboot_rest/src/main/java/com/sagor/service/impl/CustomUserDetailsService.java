package com.sagor.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sagor.dto.UserPrinciple;
import com.sagor.model.User;
import com.sagor.service.UserService;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	private final UserService userService;

	public CustomUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUsername(username);
		UserPrinciple userPrinciple = UserPrinciple.create(user);
		if (userPrinciple == null) {
			throw new UsernameNotFoundException("User name not found");
		}
		return userPrinciple;
	}

}
