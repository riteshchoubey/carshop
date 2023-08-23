package com.azure.carshop.entities;

public class Product {

	private String productId;
	private String productName;
	private Double price;
	private byte[] productPhoto;
	
	public Product(String productId, String productName, Double price,byte[] productPhoto) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.productPhoto = productPhoto;
	}
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public byte[] getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(byte[] productPhoto) {
		this.productPhoto = productPhoto;
	}
	
	
	
}
