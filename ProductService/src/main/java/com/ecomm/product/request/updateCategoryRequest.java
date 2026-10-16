package com.ecomm.product.request;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class updateCategoryRequest {

	@NotBlank(message = "please add category name!")
	private String categoryName;
	
	private String about;
}
