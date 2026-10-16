package com.ecomm.product.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(
	    uniqueConstraints = {
	        @UniqueConstraint(
	            columnNames = {"product_variant_id", "attribute_value_id"}
	        )
	    }
	)
public class ProductVariantAttribute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pvalId;
	
	@ManyToOne
	@JoinColumn(name = "product_variant_id",nullable = false)
	private ProductVariant variant;
	
	@ManyToOne
	@JoinColumn(name = "attribute_value_id",nullable = false)
	private AttributeValue attributeValue;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;
}
