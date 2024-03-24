package com.placementcell.dto;

public class UserInfoDetailsList {

	private String email;
	private String firstName;
	private String middleName;
	private String lastName;
	private String personalEmail;
	private String gender;
	private String contact;
	private String course;

	public UserInfoDetailsList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserInfoDetailsList(String email, String firstName, String middleName, String lastName, String personalEmail,
			String gender, String contact, String course) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.personalEmail = personalEmail;
		this.gender = gender;
		this.contact = contact;
		this.course = course;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

}
