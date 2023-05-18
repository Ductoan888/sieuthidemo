package com.demo.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.security.UserDetail;
import com.demo.security.UserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenFilter extends OncePerRequestFilter{
    @Autowired
    private JwtUltis jwtUltis;
    @Autowired
    private UserDetailService userDetailService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String jwt = parseJwt(request);
			if (jwt != null && jwtUltis.validateToken(jwt)) {
				String username = jwtUltis.getUsernameFromJwtToken(jwt);
				
				UserDetails userDetail = userDetailService.loadUserByUsername(username);
				
				UsernamePasswordAuthenticationToken authenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetail, null , userDetail.getAuthorities() );
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("cannot set user authentication");
		}
		filterChain.doFilter(request, response);
	}
	private String parseJwt(HttpServletRequest request) {
		String jwt = jwtUltis.getJwtFromCookies(request);
		return jwt;
	}

}
