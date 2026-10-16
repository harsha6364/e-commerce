package com.ecomm.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.dto.BrandDto;
import com.ecomm.product.enums.BrandStatus;
import com.ecomm.product.request.AddBrandRequest;
import com.ecomm.product.request.UpdateBrandRequest;
import com.ecomm.product.response.ApiResponse;
import com.ecomm.product.response.CloudinaryResponse;
import com.ecomm.product.service.BrandService;
import com.ecomm.product.service.CloudinaryService;
import com.sun.net.httpserver.HttpsConfigurator;

import jakarta.validation.Valid;

@RestController("/brand")
public class BrandController {

	@Autowired
	private BrandService bservice;
	
	@Autowired
	private CloudinaryService cloud;
	
	@PostMapping(path = "/addbrand",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addBrand(
			@RequestParam String brandName,
			@RequestParam String about,
			@RequestParam BrandStatus status,
			@RequestPart("image") MultipartFile image){
		
		AddBrandRequest request = new AddBrandRequest();
		request.setBrandName(brandName);
		request.setAbout(about);
		request.setStatus(status);
		BrandDto bdto =  bservice.addBrand(request, image);
		
		return ResponseEntity.ok(new ApiResponse<>("Brand added successfully!",bdto,HttpStatus.OK));
		
	}
	
	@DeleteMapping(path = "/deletebrand/{brandId}")
	public ResponseEntity<?> deleteBrand(@PathVariable Integer brandId){
		bservice.deleteBrand(brandId);
		return ResponseEntity.ok(new ApiResponse<>("Brand got deleted!", null, HttpStatus.OK));
	}
	
	
	@PutMapping(path = "/updatebrand/{brandId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updateBrand(
			@PathVariable Integer brandId,
			@Valid @RequestParam String brandName,
			@RequestPart MultipartFile image){
		
		UpdateBrandRequest request = new UpdateBrandRequest();
		request.setBrandName(brandName);
		BrandDto bdto = bservice.updateBrand(brandId, request, image);
		
		return ResponseEntity.ok(new ApiResponse<>("Brand updated successfully!", bdto, HttpStatus.OK));
	}
	
	@GetMapping(path = "/getall")
	public ResponseEntity<?> getAllBrands(){
		List<BrandDto> blist = bservice.getAllBrands();
		return ResponseEntity.ok(new ApiResponse<>("All brands!", blist, HttpStatus.OK));
	}
	
	@GetMapping(path = "/getbyId/{brandId}")
	public ResponseEntity<?> getBrandById(@PathVariable Integer brandId){
		BrandDto bdto = bservice.getBrandById(brandId);
		return ResponseEntity.ok(new ApiResponse<>("Specific brand!", bdto, HttpStatus.OK));
	}
}
