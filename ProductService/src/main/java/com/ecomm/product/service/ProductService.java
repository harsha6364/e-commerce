package com.ecomm.product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.dto.ProductDto;
import com.ecomm.product.request.AddProductRequest;
import com.ecomm.product.request.UpdateProductRequest;

public interface ProductService {

	public ProductDto addProduct(AddProductRequest request);
	
	public ProductDto updateProduct(Integer productId,UpdateProductRequest request);
	
	public void deleteProduct(Integer productId);
	
	public List<ProductDto> getAllProducts();
	
	public ProductDto getProductById(Integer productId);
}
