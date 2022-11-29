package com.sagor.springcrudrestapiemailsecur.controller;

import com.sagor.springcrudrestapiemailsecur.annotations.ApiController;
import com.sagor.springcrudrestapiemailsecur.dto.LoginDto;
import com.sagor.springcrudrestapiemailsecur.dto.Response;
import com.sagor.springcrudrestapiemailsecur.service.AuthService;
import com.sagor.springcrudrestapiemailsecur.util.UrlConstraint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ApiController
@RequestMapping(UrlConstraint.AuthManagement.ROOT)
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(UrlConstraint.AuthManagement.LOGIN)
    public Response authLogin(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response){
        return authService.login(loginDto);
    }
}
