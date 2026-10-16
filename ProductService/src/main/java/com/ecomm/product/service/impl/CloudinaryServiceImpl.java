package com.ecomm.product.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ecomm.product.exception.AppException;
import com.ecomm.product.response.CloudinaryResponse;
import com.ecomm.product.service.CloudinaryService;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
	
	@Autowired
	private Cloudinary cloud;

	@Override
	public CloudinaryResponse upload(MultipartFile image) {
		CloudinaryResponse response = null;
		try {
			if(image != null && !image.isEmpty()) {
				Map<?, ?> cloudresult = cloud.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
				String publicId = cloudresult.get("public_id").toString();
				String imageUrl = cloudresult.get("secure_url").toString();
				response = new CloudinaryResponse(imageUrl,publicId);
			}
			
		} catch (Exception e) {
		    e.printStackTrace();
		    throw new AppException(
		        "Failed to upload image: " + e.getMessage(),
		        HttpStatus.INTERNAL_SERVER_ERROR
		    );
		}
		return response;
	}

	@Override
	public void delete(String publicId) {
		try {
			Map<?, ?> deleteresult = cloud.uploader().destroy(publicId, ObjectUtils.emptyMap());
			String result = deleteresult.get("result").toString();
			if(!result.equalsIgnoreCase("ok")) {
				throw new AppException("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new AppException("An error occured failed to delete!", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
