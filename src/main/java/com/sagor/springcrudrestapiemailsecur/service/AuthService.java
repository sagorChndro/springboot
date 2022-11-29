package com.sagor.springcrudrestapiemailsecur.service;

import com.sagor.springcrudrestapiemailsecur.dto.LoginDto;
import com.sagor.springcrudrestapiemailsecur.dto.Response;

public interface AuthService {
    Response login(LoginDto loginDto);
}
