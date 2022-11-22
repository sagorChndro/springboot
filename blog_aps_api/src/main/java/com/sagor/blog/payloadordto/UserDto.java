package com.sagor.blog.payloadordto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	private Long userId;
	@NotBlank(message = "Username is mandatory")
	private String name;
	@Email(message = "Email must be valid")
	private String email;
	@NotBlank(message = "Password is mandatory")
	@Size(min = 3, max = 10, message = "Password must be min of 3 character and max of 10 character")
	private String password;
	private String about;
}
