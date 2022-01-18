package com.example.ecommerce.model;

import java.io.Serializable;
import org.springframework.stereotype.Component;

@Component
public class BillingRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4644759341991164970L;

	int userId;
	int amount;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(" userId "+userId).append(" amount "+amount).toString();
	}
}
