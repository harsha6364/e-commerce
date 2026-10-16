package com.ecomm.product.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class ProductVariant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer variantId;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(unique = true)
	private String productCode;
	
	@Column(unique = true, nullable = false)
	private String sku;
	
	private Double price;
	
	private Integer stock;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@PreUpdate
	public void setupdatedAt() {
		this.updatedAt=LocalDateTime.now();
	}
	
	@OneToMany(mappedBy = "productVariant",
			cascade = CascadeType.ALL,
		    orphanRemoval = true)
	private List<ProdVariantImage> prodVariantImages;
	
	
	@OneToMany(mappedBy = "variant",
			cascade = CascadeType.ALL,
		    orphanRemoval = true)
	private List<ProductVariantAttribute> variantAttributes;
}
