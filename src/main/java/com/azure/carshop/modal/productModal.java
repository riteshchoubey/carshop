package com.azure.carshop.modal;

public class productModal {
	private String productId;
	private String productName;
	private Double price;
	private byte[] productPhoto;
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
