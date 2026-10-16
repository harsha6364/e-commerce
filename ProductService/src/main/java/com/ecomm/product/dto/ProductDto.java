package com.ecomm.product.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	
	private Integer productId;
	
	private String productName;
	
	private String description;
	
	private LocalDate createdAt;
	
	private BrandDto brand;
	
	private CategoryDto category;
	
}
