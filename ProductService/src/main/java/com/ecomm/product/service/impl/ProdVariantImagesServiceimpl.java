package com.ecomm.product.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.dto.ProdVariantImageDto;
import com.ecomm.product.entity.ProdVariantImage;
import com.ecomm.product.entity.ProductVariant;
import com.ecomm.product.exception.AppException;
import com.ecomm.product.repository.ProdVariantImageRepo;
import com.ecomm.product.repository.ProductVariantRepo;
import com.ecomm.product.request.AddProdVarientImagesReq;
import com.ecomm.product.response.CloudinaryResponse;
import com.ecomm.product.service.CloudinaryService;
import com.ecomm.product.service.ProdVariantImageService;

import io.micrometer.core.ipc.http.HttpSender.Response;

@Service
public class ProdVariantImagesServiceimpl implements ProdVariantImageService{

	@Autowired
	private ProdVariantImageRepo varImgRepo;
	
	@Autowired
	private ProductVariantRepo prodVarRepo;
	
	@Autowired
	private CloudinaryService cloud;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ProdVariantImageDto addImages(AddProdVarientImagesReq request, MultipartFile images) {
		ProductVariant prodVar = prodVarRepo.findById(request.getProdVariantId())
				.orElseThrow(()-> new AppException("Product Variant not found!", HttpStatus.NOT_FOUND));
		
		if(images==null && images.isEmpty()) {
			throw new AppException("Images are equired!", HttpStatus.CONFLICT);
		}
		
		CloudinaryResponse response = cloud.upload(images);
		ProdVariantImage prodVarImg = new ProdVariantImage();
		prodVarImg.setImageUrl(response.getImgUrl());
		prodVarImg.setCloudinaryPublicId(response.getPublicId());
		prodVarImg.setProductVariant(prodVar);
		prodVarImg = varImgRepo.save(prodVarImg);
		return mapper.map(prodVarImg, ProdVariantImageDto.class);
	}

	@Override
	public ProdVariantImageDto updateImages(Integer imageId, MultipartFile images) {
		ProdVariantImage varImg  = varImgRepo.findById(imageId)
				.orElseThrow(()->new AppException("Product variant images not found!", HttpStatus.NOT_FOUND));
		
		if(images!=null && !images.isEmpty()) {
			if(varImg.getCloudinaryPublicId()!=null && varImg.getImageUrl() != null) {
				cloud.delete(varImg.getCloudinaryPublicId());
			}
			CloudinaryResponse response = cloud.upload(images);
			varImg.setCloudinaryPublicId(response.getPublicId());
			varImg.setImageUrl(response.getImgUrl());
			varImg = varImgRepo.save(varImg);
		}
		return mapper.map(varImg, ProdVariantImageDto.class);
	}

}
