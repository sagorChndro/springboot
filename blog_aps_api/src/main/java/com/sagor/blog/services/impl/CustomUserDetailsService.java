package com.sagor.blog.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sagor.blog.model.User;
import com.sagor.blog.payloadordto.UserPrinciple;
import com.sagor.blog.services.UserService;

@Service
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
			throw new UsernameNotFoundException("Username not found");
		}
		return userPrinciple;
	}

}
