package com.ecomm.product.request;

import com.ecomm.product.enums.ProductStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddProductRequest {

	@NotBlank(message = "product name is required")
	private String productName;
	
	private String description;
	
	private ProductStatus status;
	
	@NotBlank(message = "Brand name is required")
	private String brandName;
	
	@NotBlank(message = "category name is required")
	private String categoryName;
}
