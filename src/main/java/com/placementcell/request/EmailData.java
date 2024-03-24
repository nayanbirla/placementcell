package com.placementcell.request;

public class EmailData {

	private String email;
	
	public EmailData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailData(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "EmailData [email=" + email + "]";
	}
	
	
}
