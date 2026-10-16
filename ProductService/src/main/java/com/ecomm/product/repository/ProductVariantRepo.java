package com.ecomm.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.product.entity.ProductVariant;

@Repository
public interface ProductVariantRepo extends JpaRepository<ProductVariant, Integer>{

	public Optional<ProductVariant> findByProductCode(String ProductCode);
}
