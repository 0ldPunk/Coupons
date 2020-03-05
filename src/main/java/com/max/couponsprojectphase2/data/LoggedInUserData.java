package com.max.couponsprojectphase2.data;

import com.max.couponsprojectphase2.enums.ClientType;

public class LoggedInUserData {
	private Integer token;
	private ClientType clientType;
	private Long userId;
	private Long companyId;
	
	public LoggedInUserData() {
		
	}
	
	public LoggedInUserData(ClientType clientType, Long userId) {
		super();
		this.clientType = clientType;
		this.userId = userId;
	}

	public Integer getToken() {
		return token;
	}

	public void setToken(Integer token) {
		this.token = token;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
}
