package com.sagor.basicspringsecurity.service;

import com.sagor.basicspringsecurity.model.User;

import java.util.List;

public interface UserService {
    User save(User user);
    User update(User user);
    User get(String username);
    User get(Long id);
    String delete(Long id);
    List<User> getAll();
}
