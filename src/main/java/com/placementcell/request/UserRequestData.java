package com.placementcell.request;

import com.placementcell.entities.Course;

public class UserRequestData {

	private int id;
	private String email;
	private String firstName;
	private String middleName;
	private String lastName;
	private String personalEmail;
	private String gender;
	private String contact;
	private String course;
	private String image;
	private double tenthPercentage;
	private double twelthPercentage;
	private double ug;
	private double pg;
	public UserRequestData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRequestData(int id,String email, String firstName, String middleName, String lastName, String personalEmail,
			String gender, String contact, String course, String image, double tenthPercentage, double twelthPercentage,
			double ug, double pg) {
		super();
		this.id=id;
		this.email = email;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.personalEmail = personalEmail;
		this.gender = gender;
		this.contact = contact;
		this.course = course;
		this.image = image;
		this.tenthPercentage = tenthPercentage;
		this.twelthPercentage = twelthPercentage;
		this.ug = ug;
		this.pg = pg;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getTenthPercentage() {
		return tenthPercentage;
	}
	public void setTenthPercentage(double tenthPercentage) {
		this.tenthPercentage = tenthPercentage;
	}
	public double getTwelthPercentage() {
		return twelthPercentage;
	}
	public void setTwelthPercentage(double twelthPercentage) {
		this.twelthPercentage = twelthPercentage;
	}
	public double getUg() {
		return ug;
	}
	public void setUg(double ug) {
		this.ug = ug;
	}
	public double getPg() {
		return pg;
	}
	public void setPg(double pg) {
		this.pg = pg;
	}
	@Override
	public String toString() {
		return "UserRequestData [email=" + email + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", personalEmail=" + personalEmail + ", gender=" + gender + ", contact="
				+ contact + ", course=" + course + ", image=" + image + ", tenthPercentage=" + tenthPercentage
				+ ", twelthPercentage=" + twelthPercentage + ", ug=" + ug + ", pg=" + pg + "]";
	}
	
	
}
