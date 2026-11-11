package com.ecomm.cart.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {

	
	private Integer cartId;
    private Integer userId;
    private List<CartItemDto> items;
}
