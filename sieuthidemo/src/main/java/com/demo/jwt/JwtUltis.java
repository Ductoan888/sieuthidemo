package com.demo.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.demo.security.UserDetail;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUltis {
 private static final Logger logger = LoggerFactory.getLogger(JwtUltis.class);
 
 @Value("${jwtSecret}")
 private String jwSecret;
 @Value("${jwtExprirationMs}")
 private int jwtExprirationMs;
 @Value("${jwtCookieName}")
 private String jwtCookie;
 
 public String generateTokenFromUsername(String username) {
	 return Jwts.builder()
			 .setSubject(username)
			 .setExpiration(new Date((new Date()).getTime()+ jwtExprirationMs))
			 .setIssuedAt(new Date())
			 .signWith(SignatureAlgorithm.HS512, jwSecret)
			 .compact();
	         
}
 public String getJwtFromCookies(HttpServletRequest request) {
	 Cookie cookie = WebUtils.getCookie(request, jwtCookie);
	 if (cookie != null) {
		return cookie.getValue();
	}else {
		return null;
	}
 }
 public boolean validateToken(String token) {
	 try {
		Jwts.parser().setSigningKey(jwSecret).parseClaimsJws(token);
		return  true;	
		
	} catch (SignatureException e) {
		// TODO: handle exception
		logger.error("Invalid Jwt Signature");
	} catch (MalformedJwtException e) {
		// TODO: handle exception
		logger.error("Invalid jwt Malformed");
	} catch (ExpiredJwtException e) {
		// TODO: handle exception
		logger.error("Invalid jwt Expired");
	} catch (IllegalArgumentException e) {
		logger.error("invalid jwt IllegalArgument");
		// TODO: handle exception
	}
	return false;
 }
 public String getUsernameFromJwtToken(String authtoken) {
	 return Jwts.parser().setSigningKey(jwSecret).parseClaimsJws(authtoken).getBody().getSubject();
 }
 public ResponseCookie generateJwtCookie(UserDetail userDetail) {
	 String jwt = generateTokenFromUsername(userDetail.getUsername());
	 ResponseCookie cookie = ResponseCookie.from(jwtCookie , jwt).path("/api").maxAge(24*60*60).httpOnly(true).build();
	 return cookie;
 }
 public ResponseCookie getCleanJwtCookie() {
     ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
     return cookie;
   }
}
