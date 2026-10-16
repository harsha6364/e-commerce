package com.dcl.service.impl;

import java.io.IOException;
import java.util.Map;

import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dcl.exception.AppException;
import com.dcl.response.CloudinaryResponse;
import com.dcl.service.CloudinaryService;

@Service
public class CloudinaryServiceImpl implements CloudinaryService{

	@Autowired
	private Cloudinary cloudinary;
	
	@Override
	public CloudinaryResponse uploadImage(MultipartFile image) {
		CloudinaryResponse cloudinaryResponse = null;
		
		if(image != null && !image.isEmpty()) {
			try {
				Map<?, ?> cloudResponse = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
				String publicId =(String) cloudResponse.get("public_id");
				String imageUrl =(String) cloudResponse.get("secure_url");
				cloudinaryResponse = new CloudinaryResponse(imageUrl, publicId);
				
			} catch (IOException e) {
				throw new AppException("Failed to upload image!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else {
			throw new AppException("Image not found!", HttpStatus.NOT_FOUND);
		}
		return cloudinaryResponse;
	}

	@Override
	public void deleteImage(String publicId) {
		try {
			Map<?, ?> result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
			
			if(!result.get("result").equals("ok")) {
				throw new AppException("Failed to delete image", HttpStatus.EXPECTATION_FAILED);
			}
		} catch (IOException e) {
			throw new AppException("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	
}
