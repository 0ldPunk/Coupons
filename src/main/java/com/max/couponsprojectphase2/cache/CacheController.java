package com.max.couponsprojectphase2.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;

@Controller
public class CacheController implements ICacheController{

	private Map<String, Object> cacheMap;
	
	public CacheController() {
		this.cacheMap = new HashMap<>();
	}
	
	@Override
	public Object get(String key) {
		return this.cacheMap.get(key);
	}

	@Override
	public void put(String key, Object value) {
		this.cacheMap.put(key, value);
	}

}
