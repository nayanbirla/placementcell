package com.placementcell.dto;

import java.util.List;

public class EmailInvalid {

	private List<String> invalidEmails;
	private List<String> emailAlreadyExist;
	public EmailInvalid(List<String> invalidEmails, List<String> emailAlreadyExist) {
		super();
		this.invalidEmails = invalidEmails;
		this.emailAlreadyExist = emailAlreadyExist;
	}
	public List<String> getInvalidEmails() {
		return invalidEmails;
	}
	public void setInvalidEmails(List<String> invalidEmails) {
		this.invalidEmails = invalidEmails;
	}
	public List<String> getEmailAlreadyExist() {
		return emailAlreadyExist;
	}
	public void setEmailAlreadyExist(List<String> emailAlreadyExist) {
		this.emailAlreadyExist = emailAlreadyExist;
	}
	
}
