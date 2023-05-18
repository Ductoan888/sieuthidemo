package com.demo.service;

import com.demo.dto.CreateUserRequest;
import com.demo.dto.updateProfileRequest;
import com.demo.entity.User;

public interface UserService {
  
	User register(CreateUserRequest createUserRequest);
	User updateUser(updateProfileRequest updateProfileRequest);
	User getUserByUsername(String username);
	
	
}
