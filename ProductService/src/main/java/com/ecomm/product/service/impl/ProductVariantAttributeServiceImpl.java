package com.ecomm.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecomm.product.dto.AttributeValueDto;
import com.ecomm.product.dto.ProductVariantAttributeDto;
import com.ecomm.product.dto.ProductVariantDto;
import com.ecomm.product.entity.AttributeValue;
import com.ecomm.product.entity.ProductVariant;
import com.ecomm.product.entity.ProductVariantAttribute;
import com.ecomm.product.exception.AppException;
import com.ecomm.product.repository.AttributeValueRepo;
import com.ecomm.product.repository.ProductVariantAttributeRepo;
import com.ecomm.product.repository.ProductVariantRepo;
import com.ecomm.product.request.AddProdVariantReq;
import com.ecomm.product.request.UpdateProdVariantReq;
import com.ecomm.product.request.UpdateStockRequest;
import com.ecomm.product.service.ProductVariantAttributeService;
import com.ecomm.product.service.ProductVariantService;
import com.netflix.discovery.converters.Auto;

@Service
public class ProductVariantAttributeServiceImpl implements ProductVariantAttributeService {
	
	@Autowired
	private ProductVariantAttributeRepo pvarepo;
	
	@Autowired
	private ProductVariantRepo pvrepo;
	
	@Autowired
	private AttributeValueRepo avrepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ProductVariantAttributeDto addProdVarAtt(Integer pvId, Integer avId) {
		ProductVariant pv = pvrepo.findById(pvId).orElseThrow(()->new AppException("Product variant not found!", HttpStatus.NOT_FOUND));
		AttributeValue av = avrepo.findById(avId).orElseThrow(()->new AppException("Attribute Value not found!", HttpStatus.NOT_FOUND));
		
		boolean exists = pvarepo.existsByVariant_VariantIdAndAttributeValue_AvId(pvId, avId);
		if(exists) {
			throw new AppException("Attribute value already added to this product variant!!", HttpStatus.CONFLICT);
		}
		
		ProductVariantAttribute pva = new ProductVariantAttribute();
		pva.setVariant(pv);
		pva.setAttributeValue(av);
		pva = pvarepo.save(pva);
		ProductVariantDto pvDto = mapper.map(pva.getVariant(), ProductVariantDto.class);
		AttributeValueDto avDto = mapper.map(pva.getAttributeValue(), AttributeValueDto.class);
		ProductVariantAttributeDto pvaDto = new ProductVariantAttributeDto();
		pvaDto.setVariant(pvDto);
		pvaDto.setAttributeValue(avDto);
		pvaDto.setPvalId(pva.getPvalId());
		return pvaDto;
	}

	@Override
	public void deleteProdVarAtt(Integer pvalId) {
		ProductVariantAttribute pva = pvarepo.findById(pvalId)
                .orElseThrow(() -> new AppException(
                        "Product variant attribute not found!",
                        HttpStatus.NOT_FOUND));

        pvarepo.delete(pva);
		
	}

	@Override
	public ProductVariantAttributeDto getById(Integer pvalId) {
		ProductVariantAttribute pva = pvarepo.findById(pvalId).orElseThrow(()->new AppException("Product varient attribute not found!", HttpStatus.NOT_FOUND));
		
		return mapper.map(pva, ProductVariantAttributeDto.class);
	}

	@Override
	public List<ProductVariantAttributeDto> getAll() {
		List<ProductVariantAttributeDto> list = pvarepo.findAll().stream()
				.map(pva->mapper.map(pva, ProductVariantAttributeDto.class))
				.collect(Collectors.toList());
		return list;
	}

	
}
