package com.ecomm.product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.dto.CategoryDto;
import com.ecomm.product.request.AddCategoryRequest;
import com.ecomm.product.request.updateCategoryRequest;

public interface categoryService {

	public CategoryDto addCategory(AddCategoryRequest request, MultipartFile image);
	
	public CategoryDto updateCategory(Integer categoryID, updateCategoryRequest request, MultipartFile image);
	
	public void deleteCategory(Integer categoryId);
	
	public List<CategoryDto> getAllCategories();
	
	public CategoryDto getCategoryById(Integer categoryId);
}
