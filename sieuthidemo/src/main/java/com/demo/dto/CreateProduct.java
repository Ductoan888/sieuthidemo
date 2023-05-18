package com.demo.dto;

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
public class CreateProduct {
	 @NotNull(message = "Tên sản phẩm rỗng")
	    @NotEmpty(message="Tên sản phẩm rỗng")
	    
	    @Size(min=5,max=50,message="Tên sản phẩm từ 3-50 ký tự")
	    private String name;

	    @NotNull(message = "Mô tả rỗng")
	    @NotEmpty(message="Mô tả rỗng")
	    @Size(min=5,max=1000,message="Mô tả sản phẩm từ 5-1000 ký tự")
	    private String description;

	    @NotNull(message = "Giá tiền rỗng")
	    @NotEmpty(message = "Giá tiền rỗng")
	    @Size(min=0,message="Giá tiền sản phẩm lớn hơn 0")
	    private long price;

	    @NotNull(message = "Số lượng sản phẩm")
	    @NotEmpty(message="Số lượng sản phẩm")
	    @Size(min=0,message="Số lượng sản phẩm từ 0")
	    private int quantity;

	    @NotNull(message = "Danh mục rỗng")
	    @NotEmpty(message = "Danh mục rỗng")
	    private long categoryId;

	    @NotNull(message="Ảnh sản phẩm rỗng")
	    private Set<Long> imageIds;
}
