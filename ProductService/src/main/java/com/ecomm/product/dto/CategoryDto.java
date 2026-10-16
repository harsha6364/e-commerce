package com.ecomm.product.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

	private Integer categoryId;
	
	private String categoryName;
	
	private String about;
	
	private String imageUrl;
	
	private LocalDateTime createdAt;
}
