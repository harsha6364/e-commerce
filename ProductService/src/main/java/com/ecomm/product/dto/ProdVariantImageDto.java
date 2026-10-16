package com.ecomm.product.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdVariantImageDto {
	
	private Integer imageId;
	
	private ProductVariantDto productVariant;
	
	private String imageUrl;
	
	private String cloudinaryPublicId;
	
	private Integer displayOrder;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

}
