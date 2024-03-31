package com.placementcell.request;

public class RoleChangingRequest {

	private String email;
	private String role;

	public RoleChangingRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleChangingRequest(String email, String role) {
		super();
		this.email = email;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
