package com.sagor.springcrudrestapiemailsecur.configuration;

import com.sagor.springcrudrestapiemailsecur.filter.CustomFilter;
import com.sagor.springcrudrestapiemailsecur.filter.JwtAuthenticationFilter;
import com.sagor.springcrudrestapiemailsecur.service.impl.CustomUserDetailsService;
import com.sagor.springcrudrestapiemailsecur.util.UrlConstraint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // amra jeheto eikhane Api security provide korbo er jonno amader
    // ekta method override korlei hobe but eikhane amra duita method nibo

    // eikhane CustomUserDetails service k Autowired kore nite hobe
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    private final CustomFilter customFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder, MyAuthenticationEntryPoint myAuthenticationEntryPoint, CustomFilter customFilter, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.myAuthenticationEntryPoint = myAuthenticationEntryPoint;
        this.customFilter = customFilter;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // eikhane amder authentication er maddhome database ta chinay dite hobe
        // mane shey kar maddome authentication ta korbe


        // eikhane userDetailsService ta emon ekta class nibe jeita extend korbe
        // UserDetails name er jonno ekta class create(CustomUserDetailsService) kore
        // UserDetailsService k implements korbe (serviceImpl package)
        // eikhane passwordEncoder er jonno ekta bean declaration lagbe eita amra
        // BeanConfig Class e korbo
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }
    // ei method ta j amar authentication manager bean hobe setar jonno eitate
    // @Bean annotation dite hobe
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // er pore eikhane jei http request gula ashbe sei request gular kichu permission
    // er bepar thake oi permission amra ei method theke enable disable kore nibo

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // cors and csrf jate attack korte na pare ei jonno eita disable kore dite hoy
        // amder jodi kono exception handle kora lage ei jonno exceptionHandling use kora
        // jodi amader kono exception khay tahole
        // authenticationEntryPoint ExceptionHandlingConfigurer kore
        // ExceptionHandlingConfigurer<HttpSecurity> authenticationEntryPoint er maddhome kore
        // AuthenticationEntryPoint er ekta object er maddhome er jonno alda class create korte
        // hobe (config package) e eita extends korbe AuthenticationEntryPoint k
        String allPrefix = "/*";
        http.cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(myAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(UrlConstraint.AuthManagement.ROOT+allPrefix)
                .permitAll()
                .anyRequest()
                .authenticated();

        // ei authentication ta control korar age amader eikhane ekta filter add korte
        // hobe
        // http ta k filter koranor jonno addFilterBefore use korte hobe ja ekti
        // class k filter er maddome ney tai ekti class create(Custom Filter) korte hobe
        // addFilterBefore hocce ei request ta ashar age shey ei filter ra ke shey call
        // korbe. er por amra filter ta add korbo tai CustomFilter k autowired korte hobe
        // customfilter er sathe amader arekta parameter dite hobe seita holo class type
        //http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        //custom filter er jonno uporer class line ta execute hobe
        // jwt toekn neyar jonno nicher line execute hobe
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
