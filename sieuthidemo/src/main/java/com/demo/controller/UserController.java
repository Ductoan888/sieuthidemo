package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.updateProfileRequest;
import com.demo.entity.User;
import com.demo.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
   @Autowired
   private UserService userService;
   @GetMapping("/")
   public ResponseEntity<User> getUser(@RequestParam("username") String username){
	  User user = userService.getUserByUsername(username);
	  return ResponseEntity.ok(user);
	   
	   
   }
   @PutMapping("/update")
   public ResponseEntity<User> updateUser(@RequestBody updateProfileRequest updateProfileRequest){
	   User user = userService.updateUser(updateProfileRequest);
	return ResponseEntity.ok(user);
   }
}
