package com.max.couponsprojectphase2.data;

public class PurchaseDataObject {
	private long couponId;
	private int amount;

	public PurchaseDataObject() {

	}

	public PurchaseDataObject(long couponId, int amount) {
		super();
		this.couponId = couponId;
		this.amount = amount;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "PurchaseDataObject [couponId=" + couponId + ", amount=" + amount + "]";
	}

}
