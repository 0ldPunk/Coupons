package com.max.couponsprojectphase2.cache;

public interface ICacheController {
	public Object get(String key);
	public void put(String key, Object value);
}
