package com.ecomm.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.cart.entity.Cart;

@Repository
public interface CartRepo extends JpaRepository<Integer, Cart>{
	
	Optional<Cart> findByUserId(Integer userId);

}
