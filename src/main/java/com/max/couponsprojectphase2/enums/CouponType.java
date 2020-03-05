package com.max.couponsprojectphase2.enums;

public enum CouponType {
	Fashion, Food, Electronics;

	public static boolean contains(CouponType type) {
		for (CouponType t : CouponType.values()) {
			if (t.name().equalsIgnoreCase(String.valueOf(type))) {
				return true;
			}
		}
		return false;
	}
}
