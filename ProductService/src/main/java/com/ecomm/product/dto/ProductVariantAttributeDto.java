package com.ecomm.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariantAttributeDto {

	private Integer pvalId;
	
	private ProductVariantDto variant;
	
	private AttributeValueDto attributeValue;
}
