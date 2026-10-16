package com.dcl.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {

	@Value("${cloudinary.cloud-name}")
	private String cloudinary_name;
	
	@Value("${cloudinary.api-key}")
	private String api_key;
	
	@Value("${cloudinary.api-secret}")
	private String api_secret;
	
	@Bean
	public Cloudinary cloudinaryCreator() {
		Map<String, Object> config = new HashMap<>();
		config.put("cloud_name", cloudinary_name);
		config.put("api_key", api_key);
		config.put("api_secret", api_secret);
		Cloudinary cloud = new Cloudinary(config);
		return cloud;
	}
}
