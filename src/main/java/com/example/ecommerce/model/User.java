package com.example.ecommerce.model;

public class User {

	private int userId;
	private String userName;
	private String userCreated;
	private UserType userType;
	
	public User(int userId, String userName, String userCreated, UserType userType) {
		this.userId = userId;
		this.userName = userName;
		this.userCreated = userCreated;
		this.userType = userType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}
