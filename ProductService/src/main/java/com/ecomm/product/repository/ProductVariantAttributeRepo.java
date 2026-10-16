package com.ecomm.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.product.entity.ProductVariantAttribute;

@Repository
public interface ProductVariantAttributeRepo extends JpaRepository<ProductVariantAttribute, Integer>{

	  boolean existsByVariant_VariantIdAndAttributeValue_AvId(
	            Integer pvID,
	            Integer avId
	    );
}