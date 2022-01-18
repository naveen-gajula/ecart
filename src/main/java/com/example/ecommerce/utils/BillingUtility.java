package com.example.ecommerce.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import com.example.ecommerce.model.User;

public class BillingUtility {

	public static final String STORE_EMPLOYEE_DISCOINT = "store.employee.discount";
	public static final String STORE_AFFILIATE_DISCOINT = "store.affiliate.discount";
	public static final String STORE_CUSTOMER_DISCOINT = "store.customer.discount";
	private static final SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);

	public static boolean isDiscountValid(User user) {
		boolean isDiscount = false;
		if (user.getUserCreated() == null || user.getUserCreated().isEmpty())
			return isDiscount;
		try {
			Date createdDate = formatter.parse(user.getUserCreated());
			long difference_In_Years = TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - createdDate.getTime())
					/ 365l;
			isDiscount = difference_In_Years >= 2 ? true : false;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return isDiscount;
	}

	public static int getDiscount(String discount) {
		try {
			Optional.of(discount);
			return Integer.valueOf(discount);
		} catch (NullPointerException e) {
			return 0;
		}
	}

	public static int getFinalAmount(int amount, int percentage) {
		int finalAmount = (int) (amount - (amount * percentage) / 100);
		return finalAmount - (amount / 100) * 5;
	}
}
