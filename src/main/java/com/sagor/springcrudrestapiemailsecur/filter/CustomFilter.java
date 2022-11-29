package com.sagor.springcrudrestapiemailsecur.filter;

import com.sagor.springcrudrestapiemailsecur.model.User;
import com.sagor.springcrudrestapiemailsecur.service.UserService;
import com.sagor.springcrudrestapiemailsecur.service.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.Enumeration;

// amra jehetto Http niye kaj kortechi so http request er jonno popular filter hocche
// OncePerRequestFilter tai eita extends korbo
@Configuration
public class CustomFilter extends OncePerRequestFilter {
    // ei methode amder muloto jetia korte hobe seita holo amra jei authorization ta
    // dibo mane jei username password ta dibo sei username password
    //  ta amra eikhan theke dhorbo


    // request theke kibhabe amra username password ta pabo seitar jonno amra eikhane
    // ekta User Types jeheto eigula user theke ashbe private method create korbo

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    public CustomFilter(UserService userService, PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    private User getUser(HttpServletRequest request){
        // request theke amader prothome header gula k alada korte hobe
        // Request theke header gula pawar way hocche??? getHeaderNames() er maddhome
        // amra header er nam gula pabo eita Enumeration<String> return kore tai eita pete
        // gele Enumeration<String> nite hobe
        String authString= null;
        // eita ek dhoroner ekta list dhorte pari
        Enumeration<String> headerNamesEnumeration= request.getHeaderNames();
        // Enumeration theke value receive korte gele amader while loop use korte hobe
        // jodi kono Enumeration thake tahole amra loop er vitore dukbo
        while(headerNamesEnumeration.hasMoreElements()){
            // loop er vitore jokhn dhukbe tokhn amra headerKey provide korbo
            // header key ta pawar way hocche amader j enumeration object ta ache seita
            // dot nextElements().(headerNamesEnumeration.nextElement())
            String headerKey = headerNamesEnumeration.nextElement();
            if(headerKey.equalsIgnoreCase("Authorization")){
                authString = request.getHeader(headerKey);
                break;
            }
        }
        // er por authString theke username ta ke aladar korar jonno ekta mechanism jante hobe
        if (authString != null && !authString.equals("")){
            String[] authParts = authString.split("\\s+");
            // eitar maddhome parts korle amra duita part pabo ekta hocche username
            // arekta hocche password
            // authInfo hocche amar dui number index er moddhe thakbe . dui num index mane [1]
            String authInfo = authParts[1];
            byte[] bytes = DatatypeConverter.parseBase64Binary(authInfo);// amra jei authInfo ta pailam seita amra byte e convert kore dilam
            // username password alada korar way
            String decodeAuth = new String(bytes);
            String usernameAndPassword[] = decodeAuth.split(":");
            // jeheto amra username password ta pailam sheheto amra UserService ta
            // Inject korbo tai eita k Configuartion annotation dite hobe
            if(usernameAndPassword[0] != null && usernameAndPassword[1] !=null){
                // tahole amra user er object ta create kore nibo
                User user = userService.get(usernameAndPassword[0]);
                // er por amader password er sathe match korate hobe er jonno
                // amader PasswordEncoder autowired kore nite hobe
                // eikhane match() method ta duita parameter ney ekta hobe userend theke ashbe
                // plane text oita holo usernameAndPassword[1] arekta holo encoded password
                // jeita amr database theke ashbe oita holo user.getPassword
                if(passwordEncoder.matches(usernameAndPassword[1], user.getPassword())){
                    return user;
                }
                return null;
            }
        }
        return null;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // ei filter er moddhe amader moloto jeta korte hobe seita holo security contex
        // context folder er moddhe amader authentication ta k dhukate hobe eita korar
        // jonno amader UserDetails er ekta instance amader create kore nite hobe
        try{
            // amra userDetails ta create korte pari customUserDetails er maddome tai
            // amra eita k inject kore nibo
            User user = getUser(request);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());// loadUserByUsername e ekta user name dite hoy seita pabo amra upore User er kach theke er jonno User er object create korte hobe
            // er por authentication korte hobe, authentication ta pabo amra
            // UserPasswordAuthenticationToken theke
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // securitycontextholder er maddhome amra bujhte parbo k login korche ba
            // kar maddhome request ta ashche oi track gula rakha lage seigula amra pabo
            // securitycontextholder theke
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            // jodi kono exception khay tahole amra logger er maddhome korte pari
        }
        // proborti execution ta kore er jonno amader filterChain ta use korte hobe
        filterChain.doFilter(request, response);

    }
}
