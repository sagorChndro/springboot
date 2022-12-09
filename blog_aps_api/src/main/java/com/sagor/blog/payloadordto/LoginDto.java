package com.sagor.blog.payloadordto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginDto {
	@NotEmpty(message = "User is mandatory")
	private String name;
	@NotNull(message = "Password is mandatory")
	private String password;

}
