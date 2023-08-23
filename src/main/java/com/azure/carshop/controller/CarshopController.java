package com.azure.carshop.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azure.carshop.entities.Product;
import com.azure.carshop.services.ProductService;

@RestController
@RequestMapping("/carshop")
@CrossOrigin
public class CarshopController {
	
	@Autowired
	private ProductService productService;

	
	 @PostMapping("/adddetails")
	    public ResponseEntity<String> addProduct(@RequestParam String productId,
	            @RequestParam String productName,
	            @RequestParam Double price,
	            @RequestParam("photo") MultipartFile photo) {
	        try {
	        		byte[] productPhoto = photo.getBytes();
	            String message = productService.addProduct(productId,productName,price,productPhoto);
	            return new ResponseEntity<>(message, HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PostMapping("/cardetails")
	    public ResponseEntity<List<Product>> getAllProducts() {
	        List<Product> products = productService.getAllProducts();
	        return new ResponseEntity<>(products, HttpStatus.OK);
	    	
	    }
	    
	    @GetMapping("/")
	    public String showIndexPage() {
	        return "index"; // Return the name of the HTML file (without extension)
	    }
	}
	


