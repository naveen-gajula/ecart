package com.example.ecommerce.ecommercebilling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import com.example.ecommerce.controller.BillingController;
import com.example.ecommerce.model.BillingRequest;
import com.example.ecommerce.service.BillingService;


public class BillingControllerTest {
	
	@InjectMocks
	private BillingController controller;
	
	@Mock
	private BillingService service;
	
	private BillingRequest request;
	
	@BeforeEach
	void initService() {
        MockitoAnnotations.openMocks(this);
        request = new BillingRequest();
		request.setUserId(4);
		request.setAmount(600);
    }
	
	@Test
	public void TestBill() {
		//For final bill
		when(service.getBill(request)).thenReturn(56);
		ResponseEntity<Object> response = controller.getNetBill(request);
		assertEquals(200, response.getStatusCode().value());
		assertEquals(56, response.getBody());
	}
	
	@Test()
	public void TestBadRequest() {
		//For Bad Request
		request.setUserId(0);
		request.setAmount(0);
		ResponseEntity<Object> response = controller.getNetBill(request);
		assertEquals(400, response.getStatusCode().value());
	}
	
	@Test()
	public void TestInvalidUser() {
		//For Invalid UserId
		when(service.getBill(request)).thenThrow(new RuntimeException("Invalid UserId"));
		ResponseEntity<Object> response = controller.getNetBill(request);
		assertEquals(500, response.getStatusCode().value());
	}
}
