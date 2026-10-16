package com.ecomm.product.service;

import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.dto.ProdVariantImageDto;
import com.ecomm.product.request.AddProdVarientImagesReq;

public interface ProdVariantImageService {

	public ProdVariantImageDto addImages(AddProdVarientImagesReq request,MultipartFile images);
	
	public ProdVariantImageDto updateImages(Integer imageId,MultipartFile images);

}
