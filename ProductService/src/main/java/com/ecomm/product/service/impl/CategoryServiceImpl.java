package com.ecomm.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.dto.CategoryDto;
import com.ecomm.product.entity.Category;
import com.ecomm.product.exception.AppException;
import com.ecomm.product.repository.CategoryRepo;
import com.ecomm.product.request.AddCategoryRequest;
import com.ecomm.product.request.updateCategoryRequest;
import com.ecomm.product.response.CloudinaryResponse;
import com.ecomm.product.service.CloudinaryService;
import com.ecomm.product.service.categoryService;

@Service
public class CategoryServiceImpl implements categoryService {
	
	@Autowired
	private CategoryRepo crepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CloudinaryService cloud;
	

	@Override
	public CategoryDto addCategory(AddCategoryRequest request, MultipartFile image) {
		Category alreadyExists = crepo.findByCategoryName(request.getCategoryName())
				.orElse(null);
		
		if(alreadyExists != null) {
			throw new AppException("Category already exists with the given name", HttpStatus.CONFLICT);
		}
		
		Category parentCategory = null;
		if(request.getParentCategoryName() != null) {
			parentCategory = crepo.findByCategoryName(request.getParentCategoryName())
					.orElseThrow(()-> new AppException("Parent category not found!", HttpStatus.NOT_FOUND));
		}
		
		Category category = new Category();
		category = mapper.map(request, Category.class);
		category.setParentCategory(parentCategory);
		if(image == null && image.isEmpty()) {
			throw new AppException("Category image is required!", HttpStatus.CONFLICT);
		}
		CloudinaryResponse response = cloud.upload(image);
		category.setImageUrl(response.getImgUrl());
		category.setPublicId(response.getPublicId());
		category = crepo.save(category);
		return mapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(Integer categoryId,updateCategoryRequest request, MultipartFile image) {
		Category category = crepo.findById(categoryId).orElse(null);
		
		Category ifExists = crepo.findByCategoryName(request.getCategoryName()).orElse(null);
		
		if(ifExists != null && ifExists.getCategoryId().equals(categoryId)) {
			throw new AppException("category already exists!", HttpStatus.CONFLICT);
		}
		
		if(image != null && !image.isEmpty()) {

			if(category.getImageUrl() != null && category.getPublicId() != null) {
				cloud.delete(category.getPublicId());
			}
			CloudinaryResponse response = cloud.upload(image);
			category.setImageUrl(response.getImgUrl());
			category.setPublicId(response.getPublicId());
		}
		mapper.map(request, category);
		category  = crepo.save(category);
		return mapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = crepo.findById(categoryId).orElse(null);
		if(category == null) {
			throw  new AppException("Category not found!", HttpStatus.NOT_FOUND);
		}
		crepo.deleteById(categoryId);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<CategoryDto> list = crepo.findAll().stream()
				.map((c)->mapper.map(c, CategoryDto.class))
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = crepo.findById(categoryId).orElse(null);
		if(category == null) {
			throw new AppException("Category not found!", HttpStatus.NOT_FOUND);
		}
		return mapper.map(category, CategoryDto.class);
	}

}
