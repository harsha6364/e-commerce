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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Api;
import com.ecomm.product.dto.ProductDto;
import com.ecomm.product.request.AddProductRequest;
import com.ecomm.product.request.UpdateProductRequest;
import com.ecomm.product.response.ApiResponse;
import com.ecomm.product.service.ProductService;


@RestController("/product")
public class ProductController {
	
	@Autowired
	private ProductService pservice;

	@PostMapping(path = "/addproduct")
	public ResponseEntity<?> addProduct(@RequestBody AddProductRequest request){
		ProductDto pdto = pservice.addProduct(request);
		return ResponseEntity.ok(new ApiResponse<>("Product added successfully!",pdto,HttpStatus.OK));
	}
	
	@PutMapping(path = "/updateproduct/{prodId}")
	public ResponseEntity<?> updateProduct(@PathVariable Integer prodId ,@RequestBody UpdateProductRequest request){
		ProductDto pdto = pservice.updateProduct(prodId, request);
		return ResponseEntity.ok(new ApiResponse<>("Product got updated successfully!", pdto, HttpStatus.OK));
	}
	
	@DeleteMapping(path = "/deleteproduct/{prodId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer prodId){
		pservice.deleteProduct(prodId);
		return ResponseEntity.ok(new ApiResponse<>("Product deleted successfully!", null, HttpStatus.OK));
	}
	
	@GetMapping(path = "/getproduct/{prodId}")
	public ResponseEntity<?> getProductById(@PathVariable Integer prodId){
		ProductDto pdto = pservice.getProductById(prodId);
		return ResponseEntity.ok(new ApiResponse<>("Product", pdto, HttpStatus.OK));
	}
	
	@GetMapping(path = "/getallproducts")
	public ResponseEntity<?> getAllProducts(){
		List<ProductDto> list = pservice.getAllProducts();
		return ResponseEntity.ok(new ApiResponse<>("All products", list, HttpStatus.OK));
	}
}
