package com.ecomm.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.cart.entity.CartItem;

@Repository
public interface CartItemRepo extends JpaRepository<Integer, CartItem>{

}
