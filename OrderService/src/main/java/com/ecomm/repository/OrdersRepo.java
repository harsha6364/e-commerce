package com.ecomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.order.entity.Orders;

public interface OrdersRepo extends JpaRepository<Integer, Orders>{

}
