package com.ecomm.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.dto.ProductVariantDto;
import com.ecomm.product.request.AddProdVariantReq;
import com.ecomm.product.request.UpdateProdVariantReq;
import com.ecomm.product.response.ApiResponse;
import com.ecomm.product.service.ProductVariantService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController("/productVariant")
public class ProductVariantController {
	
	@Autowired
	private ProductVariantService pvService;

	@PostMapping(
	        path = "/addProdVariant",
	        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
	)
	public ResponseEntity<?> addProductVariant(

	        @RequestParam Integer productId,

	        @RequestParam Double price,

	        @RequestParam String productCode,

	        @RequestParam String sku,

	        @RequestParam(required = false) Integer stock,

	        @RequestParam("images") List<MultipartFile> images) {

	    AddProdVariantReq request = new AddProdVariantReq();

	    request.setProductId(productId);
	    request.setPrice(price);
	    request.setProductCode(productCode);
	    request.setSku(sku);
	    request.setStock(stock);

	    ProductVariantDto pvDto =
	            pvService.addProductVariant(request, images);

	    return ResponseEntity.ok(
	            new ApiResponse<>(
	                    "Product variant added successfully!",
	                    pvDto,
	                    HttpStatus.OK
	            )
	    );
	}
	
	@PutMapping(path = "/updateProdVariant/{pvId}")
	public ResponseEntity<?> updateProductVariant(@PathVariable Integer pvId,@RequestBody UpdateProdVariantReq request,@RequestPart List<MultipartFile> images){
		ProductVariantDto pvDto = pvService.updateProductVariant(pvId, request,images);
		return ResponseEntity.ok(new ApiResponse<>("Product variant updated successfully!",pvDto,HttpStatus.OK));
	}
	
	@DeleteMapping(path = "/deleteProdVariant/{pvId}")
	public ResponseEntity<?> deleteProductVariant(@PathVariable Integer pvId){
		pvService.deleteProductVariant(pvId);
		return ResponseEntity.ok(new ApiResponse<>("Product Variant got deleted successfully!",null,HttpStatus.OK));
	}
	
	@GetMapping(path = "/getProdVariant/{pvId}")
	public ResponseEntity<?> getProductVariant(@PathVariable Integer pvId){
		ProductVariantDto pvDto = pvService.getProdVariant(pvId);
		return ResponseEntity.ok(new ApiResponse<>("Product Variant",pvDto,HttpStatus.OK));
	}
	
	@GetMapping(path = "/getAllProdVariant")
	public ResponseEntity<?> getAllProductVariant(){
		List<ProductVariantDto> pvList= pvService.getAllProdVariant();
		return ResponseEntity.ok(new ApiResponse<>("All Product Variants",pvList,HttpStatus.OK));
	}
}
