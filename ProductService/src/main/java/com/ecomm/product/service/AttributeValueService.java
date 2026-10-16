package com.ecomm.product.service;

import java.util.List;

import com.ecomm.product.dto.AttributeValueDto;
import com.ecomm.product.request.AddAttributeValue;
import com.ecomm.product.request.UpdateAttributeValueReq;

public interface AttributeValueService {

	public AttributeValueDto addAttributeValue(AddAttributeValue request);
	
	public AttributeValueDto updateAttributeValue(Integer avId,AddAttributeValue request);
	
	public void deleteAttriVal(Integer avId);
	
	public AttributeValueDto getAttibuteValById(Integer avId);
	
	public List<AttributeValueDto> getAllAttributeValues();
}
