package com.azure.carshop.services;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
	@Cacheable(value = "productCache")
	public List<productModal> getAllProducts() {
		
		List<Product> list= cardetailsDao.findAll();
		List<productModal> productModals= new ArrayList<>();
		
		if(!list.isEmpty()) {			
			for(Product a:list) {
				productModal modal=new productModal();
				modal.setProductId(a.getProductId());
				modal.setProductName(a.getProductName());
				modal.setPrice(a.getPrice());
				modal.setProductPhoto(getPhoto(a.getProductId()));
				productModals.add(modal);
			}
			
		}
		
		return productModals;
		
		
	}

	@Override
	public String addProduct(String productId,String productName,Double price){
		Product product= new Product(productId,productName,price);
		cardetailsDao.save(product);
		return "Product Added Successfully";
	}

}
