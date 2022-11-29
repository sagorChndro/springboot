package com.sagor.springcrudrestapiemailsecur.filter;

import com.sagor.springcrudrestapiemailsecur.model.User;
import com.sagor.springcrudrestapiemailsecur.repository.UserRepository;
import com.sagor.springcrudrestapiemailsecur.service.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // prothomoto authorization er jonno amader ekta key declare kore ashte hobe
    // amra jani jwt authorization ta obossoi ekta header er maddome ashe
    // header er moddhe authorization e amra jwt token ta use korbo
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService customUserDetailsService, UserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.customUserDetailsService = customUserDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // ekhn amra jwtRequest er maddhome token nobo
        // if er moddhe amader tokenProvider er maddhome token ta k validate korte hobe
        // er jonno amra ekta TokenProvider class create korbo filter package er modhhei
        //
        try{
            String jwt = getJwtFormRequest(request);
            if(StringUtils.hasText(jwt) && jwtTokenProvider.isValidate(jwt)) {
              Long userId = jwtTokenProvider.getUserIdFromToken(jwt);
                User user = userRepository.findByIdAndIsActiveTrue(userId);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
                // er por authentication korte hobe, authentication ta pabo amra
                // UserPasswordAuthenticationToken theke
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // securitycontextholder er maddhome amra bujhte parbo k login korche ba
                // kar maddhome request ta ashche oi track gula rakha lage seigula amra pabo
                // securitycontextholder theke
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){

        }
        // proborti execution ta kore er jonno amader filterChain ta use korte hobe
        filterChain.doFilter(request, response);

    }

    // prothomoto authorization er jonno amader ekta key declare kore ashte hobe
    // amra jani jwt authorization ta obossoi ekta header er maddome ashe
    // header er moddhe authorization e amra jwt token ta use korbo

    private String getJwtFormRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
