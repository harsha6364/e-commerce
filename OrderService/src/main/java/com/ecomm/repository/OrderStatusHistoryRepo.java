package com.ecomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.order.entity.OrderStatusHistory;

public interface OrderStatusHistoryRepo extends JpaRepository<Integer, OrderStatusHistory>{

}
