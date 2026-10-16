package com.ecomm.product.service;

import java.util.List;

import com.ecomm.product.dto.ProductVariantAttributeDto;

public interface ProductVariantAttributeService {

	public ProductVariantAttributeDto addProdVarAtt(Integer pvId, Integer avId);
	
	public void deleteProdVarAtt(Integer pvalId);
	
	public ProductVariantAttributeDto getById(Integer pvalId);
	
	public List<ProductVariantAttributeDto> getAll();
}
