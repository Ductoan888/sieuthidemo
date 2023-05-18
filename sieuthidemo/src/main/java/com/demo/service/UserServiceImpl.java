package com.demo.service;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.CreateUserRequest;
import com.demo.dto.updateProfileRequest;
import com.demo.entity.ERole;
import com.demo.entity.Role;
import com.demo.entity.User;
import com.demo.exception.NotFoundException;
import com.demo.repository.RoleRepository;
import com.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
 @Autowired
 private UserRepository userRepository;
 @Autowired
 private RoleRepository roleRepository;
@Override
public User register(CreateUserRequest createUserRequest) {
	User user = new User();
	user.setUsername(createUserRequest.getUsername());
	user.setEmail(createUserRequest.getEmail());
	user.setPassword(createUserRequest.getPassword());
	Set<String> strRoles = createUserRequest.getRole();
	Set<Role> roles = new HashSet<>();
	if(strRoles ==  null) {
		Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
		roles.add(userRole);
	} else {
		strRoles.forEach(role -> {
			switch(role) {
			case "admin":
				Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(()-> new RuntimeException("Error:Role Admin not found"));
				roles.add(adminRole);
				break;
			case "mod":
				Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(()-> new RuntimeException("Error:Role Mod is not found"));
				roles.add(modRole);
				break;
			default:
			case "user":
				Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(()->new RuntimeException("Error:Role is not found"));
				roles.add(userRole);
				break;
			}
		});
	}
	user.setRoles(roles);
    return userRepository.save(user);
}

@Override
public User updateUser(updateProfileRequest updateProfileRequest) {
	User user = userRepository.findByUsername(updateProfileRequest.getUsername()).get();
	user.setFirstname(updateProfileRequest.getFirstname());
	user.setLastname(updateProfileRequest.getLastname());
    user.setEmail(updateProfileRequest.getEmail());
    user.setCountry(updateProfileRequest.getCountry());
    user.setPhone(updateProfileRequest.getPhone());
    user.setState(updateProfileRequest.getState());
    user.setAddress(updateProfileRequest.getAddress());
	return user;
	
}

@Override
public User getUserByUsername(String username) {
	User user = userRepository.findByUsername(username).orElseThrow(()->new NotFoundException("Not Found User"));
	return user;
}
}
