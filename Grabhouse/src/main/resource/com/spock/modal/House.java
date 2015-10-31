package com.spock.modal;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.spock.exceptions.NoValidKeyFoundException;

public class House {
	private static String lock_id;
	private static String lockState = "locked";
	private static String[] keyAttr;
	public static Map<String, String[]> lockKeys;
	private static Boolean isHouseRequest; 

	public House() {
		// TODO Auto-generated constructor stub
	}

	static {
		lock_id = "lock01";
		setRequestState(false);
		lockKeys = new HashMap<String, String[]>();
		for (int i = 1; i <= 10; i++) {
			keyAttr = new String[3];
			keyAttr[0] = "false";
			keyAttr[1] = new Date().toLocaleString();
			keyAttr[2] = "0";
			lockKeys.put("ABC" + i, keyAttr);

		}
	}

	public String getLockKey() throws NoValidKeyFoundException {
		String returnKey = "";
		for (String string : lockKeys.keySet()) {
			if (lockKeys.get(string)[0].equals("false")) {
				lockKeys.get(string)[2] = "1";
				lockKeys.get(string)[1] = new Date().toLocaleString();
				returnKey = string;
				break;
			}
		}
		if (returnKey.equals("")) {
			throw new NoValidKeyFoundException("No key Found");
		}
		return returnKey;
	}

	public String getlockState() {
		return this.lockState;
	}

	public void setlockState(String state) {
		this.lockState = state;
	}

	public static Boolean getRequestState() {
		return isHouseRequest;
	}

	public static void setRequestState(Boolean isHouseRequest) {
		House.isHouseRequest = isHouseRequest;
	}
}
