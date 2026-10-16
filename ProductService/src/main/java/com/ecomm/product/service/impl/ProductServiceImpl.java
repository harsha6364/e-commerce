package com.ecomm.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.dto.ProductDto;
import com.ecomm.product.entity.Brand;
import com.ecomm.product.entity.Category;
import com.ecomm.product.entity.Product;
import com.ecomm.product.exception.AppException;
import com.ecomm.product.repository.BrandRepo;
import com.ecomm.product.repository.CategoryRepo;
import com.ecomm.product.repository.ProductRepo;
import com.ecomm.product.request.AddProductRequest;
import com.ecomm.product.request.UpdateProductRequest;
import com.ecomm.product.service.ProductService;
import com.netflix.discovery.converters.Auto;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepo prepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private BrandRepo brepo;
	
	@Autowired
	private CategoryRepo crepo;

	@Override
	public ProductDto addProduct(AddProductRequest request) {
		Product product = prepo.findByProductName(request.getProductName()).orElse(null);
		
		if(product != null) {
			throw new AppException("Product already exists!", HttpStatus.CONFLICT);
		}
		
		Brand brand = brepo.findByBrandName(request.getBrandName()).orElseThrow(()-> new AppException("Brand not found!", HttpStatus.NOT_FOUND));
		
		Category category = crepo.findByCategoryName(request.getCategoryName()).orElseThrow(()->new AppException("Category not found", HttpStatus.NOT_FOUND));
		
		
		Product p = mapper.map(request, Product.class);
		p.setBrand(brand);
		p.setCategory(category);
		p = prepo.save(p);
		
		return mapper.map(p , ProductDto.class);
	}

	@Override
	public void deleteProduct(Integer productId) {
		Product product = prepo.findById(productId).orElse(null);
		if(product == null) {
			throw new AppException("Product not found", HttpStatus.NOT_FOUND);
		}
		prepo.deleteById(productId);

	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<ProductDto> list = prepo.findAll()
				.stream()
				.map((p)->mapper.map(p, ProductDto.class))
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		Product product = prepo.findById(productId).orElse(null);
		if(product == null) {
			throw new AppException("Product not found", HttpStatus.NOT_FOUND);
		}
		return mapper.map(product, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(Integer productId,UpdateProductRequest request) {
		Product product = prepo.findByProductName(request.getProductName()).orElse(null); 
		
		Product ifExists = prepo.findById(productId).orElse(null);
		
		if(ifExists !=null && ifExists.getProductId().equals(productId)){
			throw new AppException("Product already exists!", HttpStatus.CONFLICT);
		}
		mapper.map(request, product);
		product = prepo.save(product);
		
		return mapper.map(product, ProductDto.class);
	}


}
