package com.sagor.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sagor.blog.model.Role;
import com.sagor.blog.repository.RoleRepository;
import com.sagor.blog.utils.UrlConstraint;

@SpringBootApplication
public class BlogApsApiApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogApsApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("taposh"));
		System.out.println(passwordEncoder.encode("subinoy"));

		try {
			Role role = new Role();
			role.setRoleId(UrlConstraint.UserManagement.ADMIN_USER);
			role.setRoleName("ROLE_ADMIN");

			Role role1 = new Role();
			role1.setRoleId(UrlConstraint.UserManagement.NORMAL_USER);
			role1.setRoleName("ROLE_NORMAL");

			List<Role> roles = List.of(role, role1);
			List<Role> result = roleRepository.saveAll(roles);

			result.forEach(r -> {
				System.out.println(r.getRoleName());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
