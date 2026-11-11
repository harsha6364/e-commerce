package com.ecomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.order.entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<Integer, OrderItem>{

}
