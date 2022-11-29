package com.sagor.springcrudrestapiemailsecur.service.impl;

import com.sagor.springcrudrestapiemailsecur.dto.UserPrinciple;
import com.sagor.springcrudrestapiemailsecur.model.User;
import com.sagor.springcrudrestapiemailsecur.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    // ei method ta ekta UserDetails k return kore er jonno
    // amader arekta class (dto package e) lagbe jeita UserDetails ke implements korbe

    // ei method e prothome amra User class er object ta nibo and oi User theke amra
    // UserPrinciple er ekta object create korbo jeita amra UserPrinciple class er
    // create method e bolchi r UserPrinciple hocche ekta UserDetails cause eita
    // UserDetails k implements koreche
    // r tai amra eikhne return korbo UserPrinciple k r eitar jonno amader UserRepository
    // te kicho method create kore ashte hobe

    // eikhane amra UserService ta ke Autowired korbo
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.get(username);
        UserPrinciple userPrinciple = UserPrinciple.create(user);
        if(userPrinciple == null){
            throw new UsernameNotFoundException("User name is not found");
        }
        return userPrinciple;
    }
}
