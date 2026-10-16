package com.ecomm.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecomm.product.dto.AttributeValueDto;
import com.ecomm.product.entity.Attribute;
import com.ecomm.product.entity.AttributeValue;
import com.ecomm.product.exception.AppException;
import com.ecomm.product.repository.AttributeRepo;
import com.ecomm.product.repository.AttributeValueRepo;
import com.ecomm.product.request.AddAttributeValue;
import com.ecomm.product.service.AttributeValueService;

@Service
public class AttributeValueServiceImpl implements AttributeValueService {

	@Autowired
	private AttributeValueRepo avrepo;
	
	@Autowired
	private AttributeRepo arepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public AttributeValueDto addAttributeValue(AddAttributeValue request) {
		
		Attribute attribute = arepo.findByName(request.getAttributeName()).orElseThrow(()-> new AppException("Attribute not found!", HttpStatus.NOT_FOUND));
		
		AttributeValue av = avrepo.findByValue(request.getValue()).orElse(null);
		if(av != null) {
			throw new AppException("Attribute value already exists!", HttpStatus.CONFLICT);
		}
		AttributeValue attributeValue = new AttributeValue();
		mapper.map(request, attributeValue);
		attributeValue.setAttribute(attribute);
		attributeValue= avrepo.save(attributeValue);
		return mapper.map(attributeValue, AttributeValueDto.class);
	}

	@Override
	public AttributeValueDto updateAttributeValue(Integer avId,AddAttributeValue request) {
		AttributeValue av = avrepo.findById(avId).orElseThrow(()->new AppException("Attribute value not found!", HttpStatus.NOT_FOUND));
		
		Attribute attribute = arepo.findByName(request.getAttributeName()).orElseThrow(()->new AppException("Attribute not found!", HttpStatus.NOT_FOUND));
		
		mapper.map(request, av);
		av.setAttribute(attribute);
		av = avrepo.save(av);		
		return mapper.map(av, AttributeValueDto.class);
	}

	@Override
	public void deleteAttriVal(Integer avId) {
		AttributeValue av = avrepo.findById(avId).orElseThrow(()->new AppException("Attribute value not found!", HttpStatus.NOT_FOUND));
		avrepo.deleteById(avId);
	}

	@Override
	public AttributeValueDto getAttibuteValById(Integer avId) {
		AttributeValue av = avrepo.findById(avId).orElseThrow(()->new AppException("Attribute value not found!", HttpStatus.NOT_FOUND));
		return mapper.map(av, AttributeValueDto.class);
	}

	@Override
	public List<AttributeValueDto> getAllAttributeValues() {
		List<AttributeValueDto> list = avrepo.findAll().stream().map((av)->mapper.map(av, AttributeValueDto.class)).collect(Collectors.toList());
		return list;
	}

	

}
