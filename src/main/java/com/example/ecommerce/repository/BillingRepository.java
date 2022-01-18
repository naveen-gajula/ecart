package com.example.ecommerce.repository;

public interface BillingRepository {

	int getFinalBill(int userId, int amount);
}
