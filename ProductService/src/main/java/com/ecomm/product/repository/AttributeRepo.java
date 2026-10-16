package com.ecomm.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.product.entity.Attribute;
import java.util.List;
import java.util.Optional;


@Repository
public interface AttributeRepo extends JpaRepository<Attribute, Integer>{

	Optional<Attribute> findByName(String name);
}
