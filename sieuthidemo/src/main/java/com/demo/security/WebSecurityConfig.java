package com.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.demo.jwt.JwtEntryPoint;
import com.demo.jwt.JwtTokenFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
  @Autowired
  private UserDetailService detailService;
  @Autowired
  private JwtEntryPoint jwtEntryPoint;
  @Bean
  public JwtTokenFilter jwtTokenFilter() {
	  return new JwtTokenFilter();
  }
  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
	  DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	  authenticationProvider.setUserDetailsService(detailService);
	  authenticationProvider.setPasswordEncoder(passwordEncoder());
	return authenticationProvider;
  }
  @Bean
  public PasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
  }
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	  return configuration.getAuthenticationManager();
  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
	httpSecurity.cors().and().csrf().disable()
	.exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
	.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	.and().authorizeHttpRequests().requestMatchers("/api/auth/**").permitAll()
	.requestMatchers("/**").permitAll()
	.anyRequest().authenticated();
	httpSecurity.authenticationProvider(daoAuthenticationProvider());
	httpSecurity.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	
	  return httpSecurity.build();
	  
  }
}
