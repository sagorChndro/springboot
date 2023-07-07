package com.sagor.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

	private String jwt;
	private boolean isAuth;

}
