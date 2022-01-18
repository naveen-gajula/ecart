package com.example.ecommerce.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.ecommerce"})
public class EcommerceBillingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceBillingApplication.class, args);
	}

}
