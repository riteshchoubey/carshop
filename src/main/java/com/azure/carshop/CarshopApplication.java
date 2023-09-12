package com.azure.carshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;






@SpringBootApplication
@EnableCaching
public class CarshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarshopApplication.class, args);	
	}
	
    }



	


