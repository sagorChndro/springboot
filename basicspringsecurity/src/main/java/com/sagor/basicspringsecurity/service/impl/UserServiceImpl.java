package com.sagor.basicspringsecurity.service.impl;

import com.sagor.basicspringsecurity.model.User;
import com.sagor.basicspringsecurity.repository.UserRepository;
import com.sagor.basicspringsecurity.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public User get(String username) {
        return userRepository.findByUsernameAndIsActiveTrue(username);
    }

    @Override
    public User get(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    @Override
    public String delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            delete(id);
        }
        return "Username is not found";
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
