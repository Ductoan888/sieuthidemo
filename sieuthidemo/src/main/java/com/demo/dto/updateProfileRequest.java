package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class updateProfileRequest {
	  private String username;

	    private String firstname;

	    private String lastname;

	    private String email;

	    private String country;

	    private String state;

	    private String address;

	    private String phone;
}
