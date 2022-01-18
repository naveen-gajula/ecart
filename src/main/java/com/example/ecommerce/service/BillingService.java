package com.example.ecommerce.service;

import org.springframework.stereotype.Service;
import com.example.ecommerce.model.BillingRequest;
import com.example.ecommerce.repository.BillingRepository;

@Service
public class BillingService {
	
private BillingRepository repository;

	public BillingService(BillingRepository repository) {
		this.repository = repository;
	}

	public int getBill(BillingRequest request) {
		return repository.getFinalBill(request.getUserId(), request.getAmount());
	}
}
