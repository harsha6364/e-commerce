package com.ecomm.product.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProdVariantReq {

	@Positive(message="Product price must be greater than 0")
    private Double price;
	
	
	@NotBlank(message = "Please Add Product Code")
    private String productCode;
}
