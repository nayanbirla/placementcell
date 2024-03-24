package com.placementcell.dto;

public class OtpTokenSenderResponse {

	private String token;
	private boolean status;

	public OtpTokenSenderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OtpTokenSenderResponse(String token, boolean status) {
		super();
		this.token = token;
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
