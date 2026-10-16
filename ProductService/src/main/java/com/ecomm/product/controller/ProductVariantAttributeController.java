package com.ecomm.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.product.dto.ProductVariantAttributeDto;
import com.ecomm.product.request.AddProductVariantAttribute;
import com.ecomm.product.response.ApiResponse;
import com.ecomm.product.service.ProductVariantAttributeService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/productVariantAttribute")
public class ProductVariantAttributeController {
	
	@Autowired
	private ProductVariantAttributeService pvaService;

	@PostMapping(path = "/addProdVarAttribute")
	public ResponseEntity<?> addProdVarAttribute(@RequestBody AddProductVariantAttribute request){
		ProductVariantAttributeDto pvaDto = pvaService.addProdVarAtt(request.getPvId(), request.getAvId());
		return ResponseEntity.ok(new ApiResponse<>("Attribute value set successfully to product variant!",pvaDto,HttpStatus.OK));
	}
	
	@DeleteMapping(path = "/deleteProdVarAttibute")
	public ResponseEntity<?> deleteProdVarAttribute(@RequestParam Integer pvalId){
		pvaService.deleteProdVarAtt(pvalId);
		return ResponseEntity.ok(new ApiResponse<>("Product variant attribute deleted successfully!", null, HttpStatus.OK));
	}
	
	@GetMapping(path = "/getProdVarAttribute/{pvalId}")
	public ResponseEntity<?> getProdVarAttribute(@PathVariable Integer pvalId){
		ProductVariantAttributeDto pvaDto = pvaService.getById(pvalId);
		return ResponseEntity.ok(new ApiResponse<>("Product Variant Attribute!", pvaDto, HttpStatus.OK));
	}
	
	@GetMapping(path = "/getAllProdVarAttribute")
	public ResponseEntity<?> getAllProdVarAttribute(){
		List<ProductVariantAttributeDto> list= pvaService.getAll();
		return ResponseEntity.ok(new ApiResponse<>("All Product Variant Attribute!", list, HttpStatus.OK));
	}
}
