package com.ecomm.product.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeValueDto {
	
	private Integer avId;
	
	private String value;
	
	private LocalDateTime createdAt;
	
	private AttributeDto attribute;

}
