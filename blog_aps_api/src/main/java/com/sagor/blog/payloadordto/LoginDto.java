package com.sagor.blog.payloadordto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginDto {
	@NotEmpty(message = "User name must be valid")
	private String name;
	@NotNull(message = "Password mandatory")
	private String password;

}
