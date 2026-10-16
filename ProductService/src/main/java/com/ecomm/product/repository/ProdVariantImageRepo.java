package com.ecomm.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.product.entity.ProdVariantImage;
import com.ecomm.product.entity.ProductVariant;

import java.util.List;


@Repository
public interface ProdVariantImageRepo extends JpaRepository<ProdVariantImage, Integer>{

	List<ProdVariantImage> findByProductVariant(ProductVariant productVariant);
}
