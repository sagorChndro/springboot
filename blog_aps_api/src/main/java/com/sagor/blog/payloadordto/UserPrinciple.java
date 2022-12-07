package com.sagor.blog.payloadordto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sagor.blog.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPrinciple implements UserDetails {

	private Long userId;
	private String email;
	private String name;
	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrinciple(Long userId, String email, String name, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.userId = userId;
		this.email = email;
		this.name = name;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrinciple create(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
		return new UserPrinciple(user.getUserId(), user.getName(), user.getEmail(), user.getPassword(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
