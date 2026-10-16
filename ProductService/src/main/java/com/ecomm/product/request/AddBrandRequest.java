package com.ecomm.product.request;

import com.ecomm.product.enums.BrandStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddBrandRequest {

	private String brandName;
	
	private String about;
	
	private BrandStatus status;

	
}
