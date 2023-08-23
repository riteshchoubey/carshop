package com.azure.carshop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.azure.carshop.entities.Product;

@Service
public class ProductServiceimpl implements ProductService {

	List<Product> list;
	
	public ProductServiceimpl(){
		
		list=new ArrayList<>();
		//list.add(new Product("P001","Magnite",80000.00));
		//list.add(new Product("P002","Baleno",700000.00));
		
	}
	
	
	@Override
	public List<Product> getAllProducts() {
		
		return list;
	}

	@Override
	public String addProduct(String productId,String productName,Double price, byte[] productPhoto){
		list.add(new Product(productId,productName,price,productPhoto));
		return "Product Added Successfully";
	}

}
