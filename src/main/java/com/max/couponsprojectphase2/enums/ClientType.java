package com.max.couponsprojectphase2.enums;

public enum ClientType {
	ADMIN, CUSTOMER, COMPANY;

	public static boolean contains(ClientType type) {
		for (ClientType t : ClientType.values()) {
			if (t.name().equalsIgnoreCase(String.valueOf(type))) {
				return true;
			}
		}
		return false;
	}
}
