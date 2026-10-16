package com.ecomm.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.dto.ProductVariantDto;
import com.ecomm.product.entity.ProdVariantImage;
import com.ecomm.product.entity.Product;
import com.ecomm.product.entity.ProductVariant;
import com.ecomm.product.exception.AppException;
import com.ecomm.product.repository.ProdVariantImageRepo;
import com.ecomm.product.repository.ProductRepo;
import com.ecomm.product.repository.ProductVariantRepo;
import com.ecomm.product.request.AddProdVariantReq;
import com.ecomm.product.request.UpdateProdVariantReq;
import com.ecomm.product.request.UpdateStockRequest;
import com.ecomm.product.response.CloudinaryResponse;
import com.ecomm.product.service.CloudinaryService;
import com.ecomm.product.service.ProdVariantImageService;
import com.ecomm.product.service.ProductVariantService;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {
	
	@Autowired
	private ProductRepo prepo;
	
	@Autowired
	private ProductVariantRepo pvrepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CloudinaryService cloud;
	
	@Autowired
	private ProdVariantImageRepo pvirepo;

	@Override
	public ProductVariantDto addProductVariant(AddProdVariantReq request,List<MultipartFile> images) {

		Product product = prepo.findById(request.getProductId())
				.orElseThrow(()->new AppException("Product not found!", HttpStatus.NOT_FOUND));
		
		ProductVariant prodVar = pvrepo.findByProductCode(request.getProductCode()).orElse(null);
		if(prodVar != null) {
			throw new AppException("Product variant already exists!", HttpStatus.CONFLICT);
		}
		
		if (images == null || images.isEmpty()) {
	        throw new AppException(
	                "At least one image is required!",
	                HttpStatus.BAD_REQUEST);
	    }

	    for (MultipartFile image : images) {
	        if (image == null || image.isEmpty()) {
	            throw new AppException(
	                    "Image is required!",
	                    HttpStatus.BAD_REQUEST);
	        }
	    }
		
		ProductVariant productVariant = new ProductVariant();

		productVariant.setProduct(product);
		productVariant.setProductCode(request.getProductCode());
		productVariant.setSku(request.getSku());
		productVariant.setPrice(request.getPrice());
		productVariant.setStock(request.getStock());
		productVariant = pvrepo.save(productVariant);
		
		int displayOrder = 1;

		for (MultipartFile image : images) {

		    CloudinaryResponse response = cloud.upload(image);

		    ProdVariantImage pvi = new ProdVariantImage();

		    pvi.setCloudinaryPublicId(response.getPublicId());
		    pvi.setImageUrl(response.getImgUrl());
		    pvi.setProductVariant(productVariant);
		    pvi.setDisplayOrder(displayOrder++);

		    pvirepo.save(pvi);
		}
		
		return mapper.map(productVariant, ProductVariantDto.class);
	}

	@Override
	public ProductVariantDto updateProductVariant(Integer prodVarId, UpdateProdVariantReq request, List<MultipartFile> images) {
		ProductVariant prodVar = pvrepo.findById(prodVarId).orElse(null);
		if(prodVar == null) {
			throw new AppException("Product variant not found!", HttpStatus.NOT_FOUND);
		}
		
		if(request.getProductCode() != null && !request.getProductCode().equals(prodVar.getProductCode())) {
			ProductVariant alreadyExists = pvrepo.findByProductCode(request.getProductCode()).orElse(null);
			if(alreadyExists != null) {
				throw new AppException("Product Code already exists!", HttpStatus.CONFLICT);
			}
		}
		
		mapper.map(request, prodVar);
		if(images != null && !images.isEmpty()) {
			List<ProdVariantImage> prodVarImg = pvirepo.findByProductVariant(prodVar);
			for(ProdVariantImage pi: prodVarImg) {
				if(pi.getCloudinaryPublicId()!=null) {
					cloud.delete(pi.getCloudinaryPublicId());
					pvirepo.delete(pi);
				}
			}
			
			for(MultipartFile image: images) {
				if(image == null || image.isEmpty()) {
					throw new AppException("Variant image is required!", HttpStatus.CONFLICT);
				}
				CloudinaryResponse response = cloud.upload(image);
				ProdVariantImage pvi =  new ProdVariantImage();
				pvi.setCloudinaryPublicId(response.getPublicId());
				pvi.setImageUrl(response.getImgUrl());
				pvi.setProductVariant(prodVar);
				pvirepo.save(pvi);
			}
		}
		prodVar = pvrepo.save(prodVar);
		return mapper.map(prodVar, ProductVariantDto.class);
	}

	@Override
	public ProductVariantDto addStock(String productCode, UpdateStockRequest request) {
		ProductVariant prodVar = pvrepo.findByProductCode(productCode).orElseThrow(()->new AppException("Product variant not found!", HttpStatus.NOT_FOUND));
		if(request.getStocks() == null || request.getStocks() <= 0) {
			throw new AppException("Stock must be greater than zero", HttpStatus.BAD_REQUEST);
		}
		Integer currentStock = prodVar.getStock() + request.getStocks();
		prodVar.setStock(currentStock);
		prodVar = pvrepo.save(prodVar);
		
		return mapper.map(prodVar, ProductVariantDto.class);
	}

	@Override
	public ProductVariantDto getProdVariant(Integer pvId) {
		ProductVariant pv = pvrepo.findById(pvId)
				.orElseThrow(()->new AppException("Product Variant not found!", HttpStatus.NOT_FOUND));
		
		return mapper.map(pv, ProductVariantDto.class);
	}

	@Override
	public List<ProductVariantDto> getAllProdVariant() {
		List<ProductVariantDto> list = pvrepo.findAll().stream()
				.map(pv->mapper.map(pv, ProductVariantDto.class))
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public void deleteProductVariant(Integer pvId) {
		ProductVariant pv = pvrepo.findById(pvId).orElseThrow(()->new AppException("Product Variant not found!", HttpStatus.NOT_FOUND));
		if(pv.getProdVariantImages()!=null) {
			for(ProdVariantImage pvi:pv.getProdVariantImages()) {
				if(pvi.getCloudinaryPublicId()!=null) {
					cloud.delete(pvi.getCloudinaryPublicId());
				}
			}
		}
		pvrepo.deleteById(pvId);
	}
	
	
	
	
	
	

}
