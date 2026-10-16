package com.ecomm.product.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.dto.BrandDto;
import com.ecomm.product.entity.Brand;
import com.ecomm.product.exception.AppException;
import com.ecomm.product.repository.BrandRepo;
import com.ecomm.product.request.AddBrandRequest;
import com.ecomm.product.request.UpdateBrandRequest;
import com.ecomm.product.response.CloudinaryResponse;
import com.ecomm.product.service.BrandService;
import com.ecomm.product.service.CloudinaryService;
import com.netflix.discovery.converters.Auto;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private BrandRepo brepo;
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CloudinaryService cloudservice;
	
	@Override
	public BrandDto addBrand(AddBrandRequest request, MultipartFile logoImg) {
		Brand alreadyExists = brepo.findByBrandName(request.getBrandName()).orElse(null);
		if(alreadyExists !=null) {
			throw new AppException("Brand already exists!", HttpStatus.CONFLICT);
		}
		
		if(logoImg == null || logoImg.isEmpty()) {
			throw new AppException("Brand image is required!", HttpStatus.BAD_REQUEST);
		}
		
		Brand brand = new Brand();
		brand = mapper.map(request, Brand.class);
		CloudinaryResponse cloud = cloudservice.upload(logoImg);
		brand.setLogoUrl(cloud.getImgUrl());
		brand.setLogoPublicId(cloud.getPublicId());
		brand =brepo.save(brand);
		return mapper.map(brand, BrandDto.class);
	}

	@Override
	public void deleteBrand(Integer brandId) {
		Brand existingBrand = brepo.findById(brandId).orElseThrow(()->new AppException("Brand not exists!", HttpStatus.NOT_FOUND)); 
		if(existingBrand.getLogoPublicId() != null) {
			cloudservice.delete(existingBrand.getLogoPublicId());
		}
		brepo.delete(existingBrand);
		
	}

	@Override
	public BrandDto updateBrand(Integer brandId, UpdateBrandRequest request, MultipartFile image) {
		Brand brand = brepo.findById(brandId).orElseThrow(()-> new AppException("Brand not found with entered Id!", HttpStatus.NOT_FOUND));
		
		Brand ifExists = brepo.findByBrandName(request.getBrandName()).orElse(null);
		
		if(ifExists != null && !ifExists.getBrandId().equals(brandId)) {
			throw new AppException("Brand already exixsts!", HttpStatus.CONFLICT);
		}
		
		if(image != null && !image.isEmpty()) {
			if(brand.getLogoPublicId() != null && brand.getLogoUrl() != null) {
				cloudservice.delete(brand.getLogoPublicId());
			}
			CloudinaryResponse response = cloudservice.upload(image);
			brand.setLogoPublicId(response.getPublicId());
			brand.setLogoUrl(response.getImgUrl());
			mapper.map(request, brand);
			brand = brepo.save(brand);
			
		}
		
		return mapper.map(brand, BrandDto.class);
	}

	@Override
	public List<BrandDto> getAllBrands() {
		
		return brepo.findAll()
				.stream()
				.map((b)->mapper.map(b, BrandDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public BrandDto getBrandById(Integer brandId) {
		Brand brand = brepo.findById(brandId).orElseThrow(()->new AppException("Brand not found!", HttpStatus.NOT_FOUND));
		return mapper.map(brand, BrandDto.class);
	}

}
