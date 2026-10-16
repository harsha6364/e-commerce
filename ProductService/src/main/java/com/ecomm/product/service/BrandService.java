package com.ecomm.product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.dto.BrandDto;
import com.ecomm.product.dto.ProductDto;
import com.ecomm.product.request.AddBrandRequest;
import com.ecomm.product.request.UpdateBrandRequest;

public interface BrandService {

	public BrandDto addBrand(AddBrandRequest request, MultipartFile logoImg);
	
	public void deleteBrand(Integer brandId);
	
	public BrandDto updateBrand(Integer brandId, UpdateBrandRequest request, MultipartFile image);
	
	public List<BrandDto> getAllBrands();
	
	public BrandDto getBrandById(Integer brandId);
}
