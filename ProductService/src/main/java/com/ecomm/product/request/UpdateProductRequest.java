package com.ecomm.product.request;

import com.ecomm.product.enums.ProductStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {

	private String productName;
	
	private String description;
	
	private ProductStatus status;
}
