package com.sagor.course_restapi.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class EmailConstraint {
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private String port;
    @Value("${spring.mail.tls}")
    private String isTls;
    @Value("${spring.mail.isAuth}")
    private String isAuth;

    private Properties getPropertiesInstance(){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.tls", isTls);
        properties.put("mail.smtp.isAuth", isAuth);
        return properties;
    }


    public Session getSessionInstance(){
        return Session.getInstance(getPropertiesInstance(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }



}
