package com.sagor.springcrudrestapiemailsecur.filter;

import com.sagor.springcrudrestapiemailsecur.dto.UserPrinciple;
import com.sagor.springcrudrestapiemailsecur.util.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.UUID;

@Configuration
public class JwtTokenProvider {
    // amra jokhn token generate korbo tokhn token er ekta secrete kay thakbe ei secrete
    // key ta amra chaile code e bole dite pari othoba amra application property theke
    // niye ashte pari abong amader token er ekta expiration time bole dite hoy seita
    // kotokkhn porjonto amar expire thakbe

    private String secreteKey = "SpringBootRestApi";
    Long expireHour = Long.valueOf("5");
    public String generateToken(Authentication authentication){
        // eikhnae authentication theke amader UserPrinciple ta ke ber kore nibo
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        // er por amra jeheto amra token er expiration hishab korbo er jonno amra date
        // hisab korbo
        Date now = new Date();
        // er por amader arekta method lagbe calculate expireTime namok
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .claim("username", userPrinciple.getUsername())
                //.claim("role", userPrinciple.getAuthorities().stream().map(grantedAuthority -> ))
                .setSubject(String.valueOf(userPrinciple.getId()))
                .setIssuedAt(now).setExpiration(DateUtils.getExpirationTime(expireHour))
                .signWith(SignatureAlgorithm.ES512, secreteKey)
                .compact();

    }

    // eikhane j username ta claim korchilam seita amra token er maddhome jebhabe pabo
    // tar way hocche
    public Long getUserIdFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secreteKey).parseClaimsJws(token).getBody();
       // obossoi String type hote hobejodi username retrun korte hoy=
        // return (String) claims.get("username");
        return Long.valueOf(claims.getSubject());
    }

    // erpor amra arekta method create korbo karon amader jei token ta ache seita validate
    // kina
    public Boolean isValidate(String token){
        try {
            // mane hocche amar token ta jodi shey parse korte pare tahole shey token
            //  ta validate ache r jodi na pare tahole shey token ta validate noy
            Jwts.parser().setSigningKey(secreteKey).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
