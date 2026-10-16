package com.ecomm.product.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAttributeRequest {
	
	@NotBlank(message = "Attribute name is required!")
	private String name;
}
