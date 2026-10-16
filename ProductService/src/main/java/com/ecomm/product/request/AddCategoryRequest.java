package com.ecomm.product.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryRequest {

	private String categoryName;
	
	private String about;
	
	private String ParentCategoryName;
}
