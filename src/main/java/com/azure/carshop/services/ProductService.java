package com.azure.carshop.services;

import java.util.List;

import com.azure.carshop.entities.Product;

public interface ProductService {
 public List<Product> getAllProducts();

public String addProduct(String productId,String productName,Double price,byte[] productPhoto);
}
