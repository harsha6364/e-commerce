package com.ecomm.product.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProdVarientImagesReq {

	private Integer prodVariantId;
	
	private Integer dispayOrder;
}
