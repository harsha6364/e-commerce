package com.ecomm.product.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeDto {

	private Integer attributeId;
	
	private String name;
	
	private LocalDateTime createdAt;
	
	
}
