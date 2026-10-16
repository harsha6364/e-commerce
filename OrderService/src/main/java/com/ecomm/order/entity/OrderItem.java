package com.ecomm.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderItemId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id",nullable = false)
	private  Orders order;
	
	@Column(nullable = false)
	private 	Integer productId;
	
	@Column(nullable = false)
	private Integer variantId;
	
	@Column(nullable = false)
	private String productName;
	
	@Column(nullable = false)
	private String sku;
	
	
	private String variantDescription;
	
	@Column(nullable = false)
	private Double unitPrice;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private Double totalPrice;
}
