package com.ecomm.product.service;

import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.response.CloudinaryResponse;

public interface CloudinaryService {
	
	CloudinaryResponse upload(MultipartFile image);
	
	void delete(String publicId);
}
