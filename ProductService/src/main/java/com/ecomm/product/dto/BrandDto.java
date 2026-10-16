package com.ecomm.product.dto;

import java.time.LocalDateTime;

import com.ecomm.product.enums.BrandStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {

	private Integer brandId;
	
	private String brandName;
	
	private String about;
	
	private String logoUrl;
	
	private LocalDateTime createdAt;
	
	
}
