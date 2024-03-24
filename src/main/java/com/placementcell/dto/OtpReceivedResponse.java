package com.placementcell.dto;

public class OtpReceivedResponse {
	private boolean status;
	private String message;

	public OtpReceivedResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OtpReceivedResponse(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
