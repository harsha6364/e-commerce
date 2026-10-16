package com.ecomm.product.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProdVariantReq {
	
	@NotBlank(message = "Product Id is required")
	private Integer productId;

	@NotNull(message = "Please add price for this product variant")
	@Positive(message = "Price should be greater than 0")
	private Double price;
	
	@NotBlank(message = "please enter product code")
	private String productCode;
	
	@NotBlank(message = "sku is required")
	private String sku;
	
	@Min(value = 1,message = "stock must be atleast 1")
	private Integer stock;

}
