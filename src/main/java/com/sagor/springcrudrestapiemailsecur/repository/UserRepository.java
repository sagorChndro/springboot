package com.sagor.springcrudrestapiemailsecur.repository;

import com.sagor.springcrudrestapiemailsecur.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // means amra oi user gula kei tulbo jeigula active obosthay ache
    User findByUsernameAndIsActiveTrue(String username);
    User findByIdAndIsActiveTrue(Long id);
}
