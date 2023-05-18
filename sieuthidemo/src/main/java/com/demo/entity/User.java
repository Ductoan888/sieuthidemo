package com.demo.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username" , unique = true)
	private String username;
	@Column(name="email" , unique = true)
	private String email;
	
	private String firstname;
	
	private String lastname;
	
	private String password;
	
	private String country;
	
	private String state;
	
	private String address;
	
	private String phone;
	@Column(name = "verification_code",length = 32 )
	private String verificationCode;
	
	private boolean enabled;
	
	 @ManyToMany(fetch = FetchType.LAZY)
	 @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name="users_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
	 private Set<Role> roles = new HashSet<>();
}
