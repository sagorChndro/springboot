package com.sagor.springcrudrestapiemailsecur.service.impl;

import com.sagor.springcrudrestapiemailsecur.model.User;
import com.sagor.springcrudrestapiemailsecur.repository.UserRepository;
import com.sagor.springcrudrestapiemailsecur.service.UserService;
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
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent() && userOptional != null){
            return userOptional.get();
        }
        return null;
    }

    @Override
    public User get(String username) {
        return userRepository.findByUsernameAndIsActiveTrue(username);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
