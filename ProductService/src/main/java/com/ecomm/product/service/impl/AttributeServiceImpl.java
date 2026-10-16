package com.ecomm.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecomm.product.dto.AttributeDto;
import com.ecomm.product.entity.Attribute;
import com.ecomm.product.exception.AppException;
import com.ecomm.product.repository.AttributeRepo;
import com.ecomm.product.request.AddAttributeRequest;
import com.ecomm.product.request.UpdateAttributeRequest;
import com.ecomm.product.service.AttributeService;

@Service
public class AttributeServiceImpl implements AttributeService{
	
	@Autowired
	private AttributeRepo arepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public AttributeDto addAttribute(AddAttributeRequest request) {
		Attribute attribute = arepo.findByName(request.getName()).orElse(null);
		if(attribute != null) {
			throw new AppException("Attribute already exists!", HttpStatus.OK);
		}
		attribute = new Attribute();
		mapper.map(request, attribute);
		attribute = arepo.save(attribute);
		return mapper.map(attribute, AttributeDto.class);
	}

	@Override
	public AttributeDto updateAttribute(Integer attributeId ,UpdateAttributeRequest request) {
		Attribute ifExists = arepo.findById(attributeId)
				.orElseThrow(()->new AppException("Attribute not found!", HttpStatus.NOT_FOUND));
		mapper.map(request, ifExists);
		ifExists = arepo.save(ifExists);
		return mapper.map(ifExists, AttributeDto.class);
	}

	@Override
	public void deleteAttribute(Integer attributeId) {
		Attribute ifExists = arepo.findById(attributeId).orElseThrow(()->new AppException("Attribute not found!", HttpStatus.NOT_FOUND));
		arepo.delete(ifExists);
		
	}

	@Override
	public AttributeDto getAttributeById(Integer attributeId) {
		Attribute attribute = arepo.findById(attributeId).orElseThrow(()->new AppException("Attribute not found!", HttpStatus.NOT_FOUND));
		return mapper.map(attribute, AttributeDto.class);
	}

	@Override
	public List<AttributeDto> getAllAttributes() {
		List<AttributeDto> list = arepo.findAll()
				.stream()
				.map((a)->mapper.map(a, AttributeDto.class))
				.collect(Collectors.toList());
		return list;
	}

	
}
