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

import com.ecomm.product.dto.AttributeValueDto;
import com.ecomm.product.request.AddAttributeRequest;
import com.ecomm.product.request.AddAttributeValue;
import com.ecomm.product.response.ApiResponse;
import com.ecomm.product.service.AttributeValueService;

@RestController("/attributeValue")
public class AttributeValueController {
	
	@Autowired
	private AttributeValueService avservice;

	@PostMapping(path = "/addAttributeValue")
	public ResponseEntity<?> addAttributeValue(AddAttributeValue request){
		AttributeValueDto avdto = avservice.addAttributeValue(request);
		return ResponseEntity.ok(new ApiResponse<>("Attribute value added successfully!",avdto,HttpStatus.OK));
	}
	
	@PutMapping(path = "/updateAttributeValue/{avId}")
	public ResponseEntity<?> updateAttributeValue(@PathVariable Integer avId,AddAttributeValue request){
		AttributeValueDto avdto = avservice.updateAttributeValue(avId, request);
		return ResponseEntity.ok(new ApiResponse<>("Attribute value updated successfully!", avdto, HttpStatus.OK));
	}
	
	
	@DeleteMapping(path = "/deleteAttributeValue/{avId}")
	public ResponseEntity<?> deleteAttributeValue(@PathVariable Integer avId){
		avservice.deleteAttriVal(avId);
		return ResponseEntity.ok(new ApiResponse<>("Attribute value got deleted successfully!", null, HttpStatus.OK));
	}
	
	@GetMapping(path = "/getAttributeValue/{avId}")
	public ResponseEntity<?> getAttributeValueById(@PathVariable Integer avId){
		AttributeValueDto avdto = avservice.getAttibuteValById(avId);
		return ResponseEntity.ok(new ApiResponse<>("Attribute value", avdto, HttpStatus.OK));
	}
	
	@GetMapping(path = "/getAllAttributeValues")
	public ResponseEntity<?> getAllAttributeValues(){
		List<AttributeValueDto> list = avservice.getAllAttributeValues();
		return ResponseEntity.ok(new ApiResponse<>("All attribute values!", list, HttpStatus.OK));
	}
}
