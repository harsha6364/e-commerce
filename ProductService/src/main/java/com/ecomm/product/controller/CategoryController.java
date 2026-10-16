package com.ecomm.product.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.ecomm.product.dto.CategoryDto;
import com.ecomm.product.entity.Category;
import com.ecomm.product.request.AddCategoryRequest;
import com.ecomm.product.request.updateCategoryRequest;
import com.ecomm.product.response.ApiResponse;
import com.ecomm.product.service.categoryService;
import com.netflix.discovery.converters.Auto;

@RestController("/category")
public class CategoryController {

	@Autowired
	private categoryService cservice;
	
	@PostMapping(path = "/addcategory", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addCategory(
			@RequestParam String categoryName,
			@RequestParam String about,
			@RequestParam(required = false) String parentCategoryName,
			@RequestPart("image") MultipartFile image){
		AddCategoryRequest request = new AddCategoryRequest();
		request.setCategoryName(categoryName);
		request.setAbout(about);
		request.setParentCategoryName(parentCategoryName);
		CategoryDto cdto = cservice.addCategory(request, image);
		return ResponseEntity.ok(new ApiResponse<>("Category added successfully!",cdto,HttpStatus.OK));
	}
	
	@PutMapping(path = "/updateCategory/{categoryId}", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updateCategory(
			@PathVariable Integer categoryId,
			@RequestParam String categoryName,
			@RequestParam String about,
			@RequestPart("image") MultipartFile image
			){
		updateCategoryRequest request = new updateCategoryRequest();
		request.setCategoryName(categoryName);
		request.setAbout(about);
		CategoryDto cdto= cservice.updateCategory(categoryId, request, image); 
		return ResponseEntity.ok(new ApiResponse<>("Category updated successfully!", cdto, HttpStatus.OK));
	}
	
	@DeleteMapping(path = "/deletecategory/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId){
		cservice.deleteCategory(categoryId);
		return ResponseEntity.ok(new ApiResponse<>("Category deleted successfully!", null, HttpStatus.OK));
	}
	
	@GetMapping(path = "/getAllCategories")
	public ResponseEntity<?> getAllCategories(){
		List<CategoryDto> list = cservice.getAllCategories();
		return ResponseEntity.ok(new ApiResponse<>("All categories", list, HttpStatus.OK));
	}
	
	@GetMapping(path = "/getCategoryById/{categoryId}")
	public ResponseEntity<?> getCategoryById(@PathVariable Integer categoryId){
		CategoryDto cdto = cservice.getCategoryById(categoryId);
		return ResponseEntity.ok(new ApiResponse<>("Specific category", cdto, HttpStatus.OK));
	}
	
	
}
