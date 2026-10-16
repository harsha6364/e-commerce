package com.ecomm.product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.dto.ProductVariantDto;
import com.ecomm.product.request.AddProdVariantReq;
import com.ecomm.product.request.UpdateProdVariantReq;
import com.ecomm.product.request.UpdateStockRequest;

public interface ProductVariantService {

	public ProductVariantDto addProductVariant(AddProdVariantReq request, List<MultipartFile> images);
	
	public ProductVariantDto updateProductVariant(Integer prodVarId,UpdateProdVariantReq request,List<MultipartFile> images);
	
	public ProductVariantDto addStock(String productCode, UpdateStockRequest request);
	
	public ProductVariantDto getProdVariant(Integer pvId);
	
	public List<ProductVariantDto> getAllProdVariant();
	
	public void deleteProductVariant(Integer pvId);
}
