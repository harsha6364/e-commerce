package com.ecomm.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.product.dto.AttributeDto;
import com.ecomm.product.request.AddAttributeRequest;
import com.ecomm.product.request.UpdateAttributeRequest;
import com.ecomm.product.response.ApiResponse;
import com.ecomm.product.service.AttributeService;

@RestController("/attribute")
public class AttributeController {
	
	@Autowired
	private AttributeService aservice;

	@PostMapping(path = "/addAttribute")
	public ResponseEntity<?> addAttribute(AddAttributeRequest request){
		AttributeDto adto = aservice.addAttribute(request);
		return ResponseEntity.ok(new ApiResponse<>("Attribute added successfully!",adto,HttpStatus.OK));
	}
	
	@PutMapping(path = "/updateAttribute")
	public ResponseEntity<?> updateAttribute(Integer attributeId,UpdateAttributeRequest request){
		AttributeDto adto = aservice.updateAttribute(attributeId, request);
		return ResponseEntity.ok(new ApiResponse<>("Attribute update successfully!", adto, HttpStatus.OK));
	}
	
	@DeleteMapping(path = "/deleteAttribute")
	public ResponseEntity<?> deleteAttribute(Integer attributeId){
		aservice.deleteAttribute(attributeId);
		return ResponseEntity.ok(new ApiResponse<>("Attribute got deleted successfully!", null, HttpStatus.OK));
	}
	
	@GetMapping(path = "/getAttribute/{attributeId}")
	public ResponseEntity<?> getAttributeById(@PathVariable Integer attributeId){
		AttributeDto adto = aservice.getAttributeById(attributeId);
		return ResponseEntity.ok(new ApiResponse<>("Specific attribute", adto, HttpStatus.OK));
	}
	
	@GetMapping(path = "/getAllAttributes")
	public ResponseEntity<?> getAllAttributes(){
		List<AttributeDto> list = aservice.getAllAttributes();
		return ResponseEntity.ok(new ApiResponse<>("All Attributes", list, HttpStatus.OK));
	}
}
