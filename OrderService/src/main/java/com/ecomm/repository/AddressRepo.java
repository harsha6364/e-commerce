package com.ecomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.order.entity.Address;

public interface AddressRepo extends JpaRepository<Integer, Address>{

}
