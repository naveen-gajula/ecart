package com.example.ecommerce.repository;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import com.example.ecommerce.model.User;
import com.example.ecommerce.model.UserType;
import com.example.ecommerce.utils.BillingUtility;

@Repository
public class BillingRepositoryImpl implements BillingRepository {

	static List<User> usersList;

	@Autowired
	private Environment env;

	static {
		usersList = Arrays.asList(new User(1, "User1", "10-22-2021", UserType.STORE_EMPLOYEE),
				new User(2, "User2", "11-25-2021", UserType.STORE_AFFILIATE),
				new User(3, "User3", "12-15-2021", UserType.STORE_CUSTOMER),
				new User(4, "User4", "12-22-2019", UserType.STORE_CUSTOMER));
	}
	
	@Override
	public int getFinalBill(int userId, int amount) {
		User user = BillingRepositoryImpl.usersList.stream().filter(t -> t.getUserId() == userId).findFirst()
				.orElseThrow(() -> new RuntimeException("Invalid UserId"));
		
		switch (user.getUserType()) {
		case STORE_EMPLOYEE:
			amount = BillingUtility.getFinalAmount(amount, BillingUtility.getDiscount(env.getProperty(BillingUtility.STORE_EMPLOYEE_DISCOINT)));
			break;
		case STORE_AFFILIATE:
			amount = BillingUtility.getFinalAmount(amount, BillingUtility.getDiscount(env.getProperty(BillingUtility.STORE_AFFILIATE_DISCOINT)));
			break;
		case STORE_CUSTOMER:
			if (BillingUtility.isDiscountValid(user)) {
				amount = BillingUtility.getFinalAmount(amount, BillingUtility.getDiscount(env.getProperty(BillingUtility.STORE_CUSTOMER_DISCOINT)));
			} else {
				amount = BillingUtility.getFinalAmount(amount, 0);
			}
			break;
		default:
			break;
		}
		return amount;
	}
}
