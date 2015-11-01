package com.spock.modal;

public class Customer {
	private static String customername;

	public static String getCustomername() {
		return customername;
	}

	public static void setCustomername(String customername) {
		Customer.customername = customername;
	}
	
}
