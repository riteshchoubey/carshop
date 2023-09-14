package com.azure.carshop.services;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;

import org.springframework.stereotype.Service;

import com.azure.carshop.config.AzureStorageConfig;
import com.azure.carshop.dao.cardetailsDao;
import com.azure.carshop.entities.Product;
import com.azure.carshop.modal.productModal;
import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

import redis.clients.jedis.Jedis;


@Service
public class ProductServiceimpl implements ProductService {
	
	@Autowired
	private cardetailsDao cardetailsDao; 
	
	@Autowired
    private AzureStorageConfig azureStorageConfig;
	
	 


	
	@Override
    public String uploadPhoto(String photoName, byte[] photoData) {
		
		System.out.println(azureStorageConfig.getAccountKey());
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(azureStorageConfig.getAccountKey())
                .buildClient();

        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient("carphotos");

        BlobClient blobClient = containerClient.getBlobClient(photoName);

        try (InputStream dataStream = new ByteArrayInputStream(photoData)) {
            blobClient.upload(dataStream, photoData.length, true);
        } catch (Exception e) {
            e.getMessage();
        }

        return blobClient.getBlobUrl();
    }

	@Override
    public byte[] getPhoto(String photoName) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(azureStorageConfig.getAccountKey()) // Or use accountName and accountKey
                .buildClient();

        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient("carphotos");

        BlobClient blobClient = containerClient.getBlobClient(photoName);
        
        BinaryData photoData = blobClient.downloadContent();
        
        byte[] byteArray = photoData.toBytes();     

        return byteArray;
    }
	
	@Override
	//@Cacheable("productCache")
	public List<productModal> getAllProducts() {
		
		Jedis jedis = new Jedis("carshop.redis.cache.windows.net", 6379);
	    jedis.auth("SegFqYC0GFl5TFISEEwLc5WynRyEKk03UAzCaJtotxY="); // Replace with your access key

	    // Define a key for caching the product list
	    String cacheKey = "allProducts";

	    // Try to retrieve the product list from the cache
	    String cachedData = jedis.get(cacheKey);

	    List<productModal> productModals = new ArrayList<>();

	    if (cachedData != null) {
	        // Data found in cache, deserialize and return it
	        productModals = deserializeProductList(cachedData);
	    } else {		
		List<Product> list= cardetailsDao.findAll();
		 
		
		if(!list.isEmpty()) {			
			for(Product a:list) {
				productModal modal=new productModal();
				modal.setProductId(a.getProductId());
				modal.setProductName(a.getProductName());
				modal.setPrice(a.getPrice());
				modal.setProductPhoto(getPhoto(a.getProductId()));
				productModals.add(modal);
			}
			jedis.set(cacheKey, serializeProductList(productModals));
		}else {
            productModals=null;
        }
	    }
	    jedis.close();
		
		return productModals;
		
	    
	}

	@Override
	public String addProduct(String productId,String productName,Double price){
		
		  // Create a Jedis client
	    Jedis jedis = new Jedis("carshop.redis.cache.windows.net", 6379);
	    jedis.auth("SegFqYC0GFl5TFISEEwLc5WynRyEKk03UAzCaJtotxY="); // Replace with your access key
		Product product= new Product(productId,productName,price);
		cardetailsDao.save(product);
		 // Update the cache by removing the cached product list (if it exists)
	    String cacheKey = "allProducts";
	    jedis.del(cacheKey);

	    // Close the Jedis client
	    jedis.close();
		
		return "Product Added Successfully";
	}
	
	

	private String serializeProductList(List<productModal> productModals) {
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	        return objectMapper.writeValueAsString(productModals);
	    } catch (JsonProcessingException e) {
	        // Handle the exception (e.g., log it or throw a custom exception)
	        e.printStackTrace();
	        return null;
	    }
	}

	private List<productModal> deserializeProductList(String serializedData) {
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	        return objectMapper.readValue(serializedData, new TypeReference<List<productModal>>() {});
	    } catch (JsonProcessingException e) {
	        // Handle the exception (e.g., log it or throw a custom exception)
	        e.printStackTrace();
	        return null;
	    }
	}


}
