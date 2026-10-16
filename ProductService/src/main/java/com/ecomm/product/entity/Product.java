package com.ecomm.product.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ecomm.product.enums.ProductStatus;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	
	@Column(nullable = false)
	private String productName;
	
	private String description;
	
	private ProductStatus status;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@PreUpdate
	public void setUpdatedAt() {
		this.updatedAt=LocalDateTime.now();
	}
	
	@ManyToOne
	@JoinColumn(name = "brand_id",nullable = false)
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "category_id",nullable = false)
	private Category category;
	
	
	
	@OneToMany(mappedBy = "product",
			 cascade = CascadeType.ALL,
			    orphanRemoval = true)
	private List<ProductVariant> productVariants;
}
