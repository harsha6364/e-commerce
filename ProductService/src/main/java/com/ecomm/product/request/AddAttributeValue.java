package com.ecomm.product.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAttributeValue {

	private String attributeName;
	
	private String value;
}
