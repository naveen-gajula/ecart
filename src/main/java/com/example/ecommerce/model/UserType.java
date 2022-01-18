package com.example.ecommerce.model;

public enum UserType {

	STORE_EMPLOYEE("store_person"),
	STORE_AFFILIATE("store_affiliate"),
	STORE_CUSTOMER("store_customer");
	
	private String user;

	UserType(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}
}
