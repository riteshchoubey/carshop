package com.azure.carshop.services;

import java.util.List;


import com.azure.carshop.modal.productModal;

public interface ProductService {
	
	
	public List<productModal> getAllProducts();

	public String addProduct(String productId,String productName,Double price);

	public String uploadPhoto(String photoName, byte[] photoData);

	public byte[] getPhoto(String photoName);

}
