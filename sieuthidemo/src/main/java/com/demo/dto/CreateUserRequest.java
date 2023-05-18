package com.demo.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
   
	private String username;
	
	private String email;
	
	private String password;
	
	private Set<String> role;
}
