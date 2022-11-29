package com.sagor.springcrudrestapiemailsecur.service.impl;

import com.sagor.springcrudrestapiemailsecur.dto.LoginDto;
import com.sagor.springcrudrestapiemailsecur.dto.LoginResponseDto;
import com.sagor.springcrudrestapiemailsecur.dto.Response;
import com.sagor.springcrudrestapiemailsecur.filter.JwtTokenProvider;
import com.sagor.springcrudrestapiemailsecur.model.User;
import com.sagor.springcrudrestapiemailsecur.repository.UserRepository;
import com.sagor.springcrudrestapiemailsecur.service.AuthService;
import com.sagor.springcrudrestapiemailsecur.util.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("authService")
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Response login(LoginDto loginDto) {
        // amra mainly eikhane check korbo amader User ta ache kina
        // user ta ashbe amader UserRepository theke ei karone userRepository
        // k inject korte hobe
        // username ta ashbe amar LoginDto theke karon username ta Logindto te
        // username ta deya ache
        User user = userRepository.findByUsernameAndIsActiveTrue(loginDto.getUsername());
        if(user==null){
            return ResponseBuilder.getFailureResponse(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
        // r jodi null na hoy tahole amader token er maddhome eita k call korte hobe
        // token nite gele Authentication er object lagbe er jonno amader
        // AuthenticationManager k autowired kore nibo
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        if(authentication.isAuthenticated()){
            // user er jonno amader ekta responseDto(LoginResponseDto)
            // banate hobe eikhane amra login korbo kinto response kiser maddhome korbo?
            // amra response korbo ekta dto r maddhome jodi authenticated hoy tahole amader
            //  oi response ta k banate hobe eita korar por amader token use korbo
            // tai amader eikhane jwt filter namok ekta filter use korte hobe
            // ager filter r amra use korbo na arekta filter use korbo

            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setToken(jwtTokenProvider.generateToken(authentication));
            loginResponseDto.setUsername(user.getUsername());
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Logged In Successfully", loginResponseDto);

        }
        return ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST, "Invalid username or password");
    }
}
