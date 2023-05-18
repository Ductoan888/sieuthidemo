package com.demo.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBlog {
   @NotNull(message = "tieu de rong")
   @NotEmpty(message = "tieu de rong")
   @Size( min = 5,max = 300 , message = "do dai 5-300")
	private String title;
	@NotEmpty(message = "mo ta rong")
	@NotNull(message = "mo ta rong")
	@Size(min = 5,message = "do dai mo ta phai tu 5 char" )
	private String description;
	@NotNull(message = "noi dung rong")
	@NotEmpty(message = "noi dung rong")
	@Size(min = 5, message = "noi dung tu 5 char")
	private String content;
	@NotNull(message = "image rong ")
	@NotEmpty(message = "image rong")
	private Long imageId;
	
	private String username;
	
	private Set<Long> tags = new HashSet<>();
}
