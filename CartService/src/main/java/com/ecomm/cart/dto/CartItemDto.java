package com.ecomm.cart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {

	private Integer cartItemId;
	private Integer variantId;
	private Integer quantity;
}
