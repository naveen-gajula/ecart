package com.example.ecommerce.ecommercebilling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import com.example.ecommerce.repository.BillingRepository;
import com.example.ecommerce.repository.BillingRepositoryImpl;
import com.example.ecommerce.utils.BillingUtility;

public class BillingRepositoryTest {

	@InjectMocks
	private BillingRepository repository = new BillingRepositoryImpl();
	
	@Mock
	Environment env;
	
	@BeforeEach
	void initService() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void TestFinalBill() {
		//For Store Employee
		when(env.getProperty(BillingUtility.STORE_EMPLOYEE_DISCOINT)).thenReturn("30");
		assertEquals(1240, repository.getFinalBill(1, 1899));
		
		//For Store Affiliate
		when(env.getProperty(BillingUtility.STORE_AFFILIATE_DISCOINT)).thenReturn("10");
		assertEquals(1620, repository.getFinalBill(2, 1899));
		
		//For Store Customer < 2
		when(env.getProperty(BillingUtility.STORE_CUSTOMER_DISCOINT)).thenReturn("5");
		assertEquals(1809, repository.getFinalBill(3, 1899));
		
		//For Store Customer > 2
		when(env.getProperty(BillingUtility.STORE_CUSTOMER_DISCOINT)).thenReturn("5");
		assertEquals(1715, repository.getFinalBill(4, 1899));
		
		//For Store Customer > 2 and Discount missing in propertyFile
		when(env.getProperty(BillingUtility.STORE_CUSTOMER_DISCOINT)).thenReturn(null);
		assertEquals(1809, repository.getFinalBill(4, 1899));
		
		//For Invalid UserID
		Exception e = assertThrows(RuntimeException.class, () -> repository.getFinalBill(5, 1899));
		assertEquals("Invalid UserId", e.getMessage());
	}
}
