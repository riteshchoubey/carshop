package com.azure.carshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.azure.carshop.entities.Product;

public interface cardetailsDao extends JpaRepository<Product, String> {
	
	@Query("FROM Product WHERE productId = :productId")
	public Product findByProductId(@Param("productId") String productId);

}
