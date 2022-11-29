package com.sagor.basicspringsecurity.service;

import com.sagor.basicspringsecurity.model.Role;
import com.sagor.basicspringsecurity.model.User;
import com.sagor.basicspringsecurity.repository.RoleRepository;
import com.sagor.basicspringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
public class DbInit {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    @Value("${login.username}")
    private String username;
    @Value("${login.password}")
    private String password;

    public DbInit(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }
    @PostConstruct
    public void DbInit(){
        String roleName="ROLE_ADMIN";
        int roleExistCount = roleRepository.countByName(roleName);
        Role role = null;
        if(roleExistCount == 1){
            role = roleRepository.findByName(roleName);
        }else{
            role = new Role();
            role.setName(roleName);
            role = roleRepository.save(role);
        }

        User user = userRepository.findByUsernameAndIsActiveTrue(username);
        if(username == null){
            user = new User();
            user.setEmail("abc@ab.com");
            user.setName(username);
            user.setPassword(password);
        }
        user.setRoles(Arrays.asList(role));
        user= userRepository.save(user);
    }
}
