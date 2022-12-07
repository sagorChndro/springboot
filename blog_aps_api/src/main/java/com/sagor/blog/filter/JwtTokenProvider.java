package com.sagor.blog.filter;

import java.util.Date;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import com.sagor.blog.payloadordto.UserPrinciple;
import com.sagor.blog.utils.DateUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class JwtTokenProvider {

	Long expireHour = Long.valueOf("5");
	private String secreteKey = "jwtTokenKey";

	public String generateToken(Authentication authentication) {
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		Date now = new Date();
		return Jwts.builder().setId(UUID.randomUUID().toString()).claim("Username", userPrinciple.getUsername())
				.setSubject(String.valueOf(userPrinciple.getUserId())).setIssuedAt(now)
				.setExpiration(DateUtils.getExpirationTime(expireHour)).signWith(SignatureAlgorithm.HS512, secreteKey)
				.compact();
	}

	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secreteKey).parseClaimsJws(token).getBody();
		return Long.valueOf(claims.getSubject());
	}

	public Boolean isValidate(String token) {
		try {
			Jwts.parser().setSigningKey(secreteKey).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
