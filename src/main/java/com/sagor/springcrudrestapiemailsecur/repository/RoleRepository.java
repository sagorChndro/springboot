package com.sagor.springcrudrestapiemailsecur.repository;

import com.sagor.springcrudrestapiemailsecur.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    int countByName(String name);

    Role findByName(String roleName);
}
