package com.azure.carshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azure.carshop.entities.Product;

public interface cardetailsDao extends JpaRepository<Product, String> {

}
