package com.placementcell.dto;

public class TokenResponse {

	private String token;
	private boolean status;
	private String role;

	public TokenResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TokenResponse(String token, boolean status, String role) {
		super();
		this.token = token;
		this.status = status;
		this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
	
}
