package com.sagor.basicspringsecurity.service.impl;

import com.sagor.basicspringsecurity.dto.UserPrinciple;
import com.sagor.basicspringsecurity.model.User;
import com.sagor.basicspringsecurity.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.get(username);
        UserPrinciple userPrinciple = UserPrinciple.create(user);
        if(userPrinciple == null){
            throw new UsernameNotFoundException("User name not found");
        }
        return userPrinciple;
    }
}
