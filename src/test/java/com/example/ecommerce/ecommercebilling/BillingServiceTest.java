package com.example.ecommerce.ecommercebilling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.ecommerce.model.BillingRequest;
import com.example.ecommerce.repository.BillingRepository;
import com.example.ecommerce.repository.BillingRepositoryImpl;
import com.example.ecommerce.service.BillingService;

public class BillingServiceTest {

	@InjectMocks
	private BillingService service;
	
	@Mock
	private BillingRepository repository = new BillingRepositoryImpl();
	
	private BillingRequest request;
	
	@BeforeEach
	void initService() {
        MockitoAnnotations.openMocks(this);
        request = new BillingRequest();
		request.setUserId(4);
		request.setAmount(600);
    }
	
	@Test
	public void TestBillingService() {
		//For final bill
		when(repository.getFinalBill(anyInt(), anyInt())).thenReturn(56);
		int value = service.getBill(request);
		assertEquals(56, value);
		
		//For Invalid UserId
		when(repository.getFinalBill(anyInt(), anyInt())).thenThrow(new RuntimeException("Invalid UserId"));
		Exception e = assertThrows(RuntimeException.class, () -> service.getBill(request));
		assertEquals("Invalid UserId", e.getMessage());
	}
}
