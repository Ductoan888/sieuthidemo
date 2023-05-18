package com.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.CreateUserRequest;
import com.demo.dto.LoginRequest;
import com.demo.entity.User;
import com.demo.jwt.JwtUltis;
import com.demo.repository.RoleRepository;
import com.demo.repository.UserRepository;
import com.demo.response.MessageResponse;
import com.demo.response.UserInfoResponse;
import com.demo.security.UserDetail;
import com.demo.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private UserService userService;
	@Autowired
	private JwtUltis jwtUltis;
	@Autowired
	private AuthenticationManager authenticationManager;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody CreateUserRequest createUserRequest){
	User user = userService.register(createUserRequest);
	  return ResponseEntity.ok(new MessageResponse("user register success"));
	  
  }
  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
	  Authentication  authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
			  loginRequest.getUsername(), loginRequest.getPassword()));
	  SecurityContextHolder.getContext().setAuthentication(authentication);
	  UserDetail userDetail = (UserDetail) authentication.getPrincipal();
	  ResponseCookie cookie = jwtUltis.generateJwtCookie(userDetail);
	  List<String> roles = userDetail.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
	return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
			.body(new UserInfoResponse(userDetail.getId(),
					userDetail.getUsername(),
					userDetail.getEmail(),roles));
	
  }
  @PostMapping("/logout")
  public ResponseEntity<?> logoutUser(){
	  ResponseCookie cookie = jwtUltis.getCleanJwtCookie();
	  return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
			  .body(new MessageResponse("logout success"));
  }
}
