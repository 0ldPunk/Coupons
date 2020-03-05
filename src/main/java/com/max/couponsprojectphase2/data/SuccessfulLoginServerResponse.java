package com.max.couponsprojectphase2.data;

import com.max.couponsprojectphase2.enums.ClientType;

public class SuccessfulLoginServerResponse {

	private ClientType clientType;
	private int token;

	public SuccessfulLoginServerResponse() {
		
	}
	
	public SuccessfulLoginServerResponse(ClientType clientType, int token) {
		this.clientType = clientType;
		this.token = token;
	}

	public ClientType getUserType() {
		return clientType;
	}

	public void setUserType(ClientType clientType) {
		this.clientType = clientType;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}
}
