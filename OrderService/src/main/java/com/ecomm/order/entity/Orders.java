package com.ecomm.order.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	
	@Column(nullable = false, unique = true)
	private String orderNumber;
	
	@Column(nullable = false)
	private Integer userId;
	
	@Column(nullable = false)
	private String orderStatus;
	
	@Column(nullable = false)
	private Double subTotal;
	
	@Column(nullable = false)
	private Double gstAmount;
	
	@Column(nullable = false)
	private Double totalAmount;
	
	@Column(nullable = false)
	private String currency;
	
	@Column(nullable = false)
	private String paymentStatus;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "order",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<OrderItem> orderItems;
	
	@OneToMany(
		    mappedBy = "order",
		    cascade = CascadeType.ALL,
		    orphanRemoval = true,
		    fetch = FetchType.LAZY
		)
		private List<Address> addresses;
	
	@OneToMany(
		    mappedBy = "order",
		    cascade = CascadeType.ALL,
		    orphanRemoval = true,
		    fetch = FetchType.LAZY
		)
		private List<OrderStatusHistory> statusHistory;
	
}
