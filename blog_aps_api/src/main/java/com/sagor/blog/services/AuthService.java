package com.sagor.blog.services;

import org.springframework.stereotype.Service;

import com.sagor.blog.payloadordto.LoginDto;
import com.sagor.blog.payloadordto.Response;

@Service
public interface AuthService {

	Response login(LoginDto loginDto);

}
