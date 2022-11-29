package com.sagor.springcrudrestapiemailsecur.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Configuration  // @Configuration kora hoy jate amara Autowired korte pari
public class MyAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    // eikhane amra override korbo commence abong after propertiesSet method
    // commence method e jodi kono dhoroner exception chay sei exception ta amader
    // kon dhoroner error dibe seita amra eikhane handle korbo response object er maddome
    // ki error hoy seita Logger er maddhome jana jay

    private static final Logger logger = LogManager.getLogger(MyAuthenticationEntryPoint.class.getName());
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.addHeader("WWW-Authenticate", "Basic Realm="+getRealmName());
        logger.error("message "+authException.getMessage());
        response.setStatus(response.SC_UNAUTHORIZED);
        response.sendError(response.SC_UNAUTHORIZED, "Unauthorized Request");

    }

    // ei method e amra muluto jodi kono claim thake sei claim ta add korte hobe
    @Override
    public void afterPropertiesSet() {
        setRealmName("SpringRest");
        super.afterPropertiesSet();
    }
}
