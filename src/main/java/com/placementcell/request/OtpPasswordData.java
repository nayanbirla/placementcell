package com.placementcell.request;

public class OtpPasswordData {

	private String token;
	private String otp;
	private String password;
	public OtpPasswordData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OtpPasswordData(String token, String otp, String password) {
		super();
		this.token = token;
		this.otp = otp;
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
