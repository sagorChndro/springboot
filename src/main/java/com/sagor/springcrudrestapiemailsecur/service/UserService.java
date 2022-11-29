package com.sagor.springcrudrestapiemailsecur.service;

import com.sagor.springcrudrestapiemailsecur.model.User;

import java.util.List;

public interface UserService {
    User create(User user);
    User update(User user);
    User get(Long id);
    User get(String username);
    void delete(Long id);
    List<User> getAll();
}
