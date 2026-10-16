package com.ecomm.product.service;

import java.util.List;

import com.ecomm.product.dto.AttributeDto;
import com.ecomm.product.request.AddAttributeRequest;
import com.ecomm.product.request.UpdateAttributeRequest;

public interface AttributeService {

	public AttributeDto addAttribute(AddAttributeRequest request);
	
	public AttributeDto updateAttribute(Integer attributeId, UpdateAttributeRequest request);
	
	public void deleteAttribute(Integer attributeId);
	
	public AttributeDto getAttributeById(Integer attributeId);
	
	public List<AttributeDto> getAllAttributes(); 
}
