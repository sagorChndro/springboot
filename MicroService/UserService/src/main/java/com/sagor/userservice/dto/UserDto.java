package com.sagor.userservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private String userId;
	@Column(length = 20)
	@NotBlank(message = "Username mandatory")
	private String userName;
	@Email(message = "Email must be valid")
	private String email;
	@NotEmpty(message = "Password mandatory")
	@Size(min = 3, max = 10, message = "Password must be min of 3 character and max of 10 character")
	private String password;
	@NotBlank(message = "About must not be empty")
	private String about;

}
