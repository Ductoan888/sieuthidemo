package com.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategory {
 @NotNull(message = "ten danh muc rong")
 @NotEmpty(message = "khong dc de rong")
 @Size(min = 5,max = 50,message = "khong du do dai")
	private String name;
}
