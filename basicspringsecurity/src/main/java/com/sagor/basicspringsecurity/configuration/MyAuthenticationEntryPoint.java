package com.sagor.basicspringsecurity.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Configuration
public class MyAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    public static final Logger logger = LogManager.getLogger(MyAuthenticationEntryPoint.class);
    @Override
    public void afterPropertiesSet() {
        setRealmName("SpringSecurity");
        super.afterPropertiesSet();
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.addHeader("WWW-Authenticate","Basic Realm="+ getRealmName());
        logger.error("message"+authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
