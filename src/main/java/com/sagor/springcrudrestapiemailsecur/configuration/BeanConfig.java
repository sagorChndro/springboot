package com.sagor.springcrudrestapiemailsecur.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {
    @Bean
    public ModelMapper getModelMapper(){

        return new ModelMapper();
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // java MailSender name e ekta api ache jeta Autowired korte hobe
    // ei Autowired korte gele prothome amader oi Bean ta create korte hobe
    // r na hole properties file ei kaj korte hobe na hole mailsender ekta red alert dibe
}
