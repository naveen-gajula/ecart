package com.example.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ecommerce.model.BillingRequest;
import com.example.ecommerce.service.BillingService;

@RestController
public class BillingController {

	@Autowired
	BillingService service;

	@PostMapping(value = "/bill")
	public ResponseEntity<Object> getNetBill(@RequestBody BillingRequest request) {
		try {
			if(request.getUserId() <= 0 || request.getAmount() <=0)
				return new ResponseEntity<Object>("incorrect data userId "+request.getUserId()+ " amount "+request.getAmount(),HttpStatus.BAD_REQUEST);
			return new ResponseEntity<Object>(service.getBill(request), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
