package com.ecomm.product.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariantDto {
	
	private Integer variantId;
	
	private ProductDto product;
	
	private String sku;
	
	private Double price;
	
	private Integer stock;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

}
